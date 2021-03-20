package cn.xiaoyh.service;

import cn.xiaoyh.dao.SensorReadDao;
import cn.xiaoyh.pojo.SensorSeries;
import org.influxdb.dto.QueryResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cn.xiaoyh.utils.SensorUtils.KEY_TIME;

@Service
public class SensorReadService {

    private final SensorReadDao sensorReadDao;

    public SensorReadService(SensorReadDao sensorReadDao) {
        this.sensorReadDao = sensorReadDao;
    }

    public List<SensorSeries> queryLastByMinute(String lab, int minute) {
        return parseQueryResult(sensorReadDao.queryLastMinute(lab, minute));
    }

    public List<SensorSeries> queryLastByHour(String lab, int hour) {
        return parseQueryResult(sensorReadDao.queryLastHour(lab, hour));
    }

    private List<SensorSeries> parseQueryResult(QueryResult queryResult) {
        return Optional.ofNullable(queryResult)
                .map(QueryResult::getResults)
                .filter(results -> !results.isEmpty())
                .map(results -> results.get(0))
                .map(QueryResult.Result::getSeries)
                .filter(series -> !series.isEmpty())
                .map(series -> series.get(0))
                .filter(series -> series.getColumns() != null && series.getValues() != null)
                .map(series -> makeSensorSeries(series.getColumns(), series.getValues()))
                .orElse(null);
    }

    private List<SensorSeries> makeSensorSeries(List<String> columns, List<List<Object>> values) {
        List<SensorSeries> res = new ArrayList<>();

        int timeIndex = -1, size = columns.size();
        for (int i = 0; i < size; i++) {
            String column = columns.get(i);
            if (KEY_TIME.equals(column)) {
                timeIndex = i;
            } else {
                res.add(new SensorSeries(column, new ArrayList<>()));
            }
        }
        if (timeIndex == -1) {  // 没有时间列不继续操作
            throw new IllegalArgumentException("response no time column");
        }

        for (List<Object> row : values) {
            Object time;
            if (row == null || row.size() != size || !((time = row.get(timeIndex)) instanceof String)) {
                // 跳过无效行（虽然大概率不会有）
                continue;
            }

            for (int i = 0; i < size; i++) {
                if (i == timeIndex) continue;

                Object value = row.get(i);
                SensorSeries sensorSeries = res.get(i < timeIndex ? i : i - 1);
                sensorSeries.getPoints().add(new SensorSeries.Point((String) time, value));
            }
        }

        return res;
    }
}
