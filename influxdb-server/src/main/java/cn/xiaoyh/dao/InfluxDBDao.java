package cn.xiaoyh.dao;

import cn.xiaoyh.pojo.SensorEntity;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class InfluxDBDao {

    private final InfluxDBMapper influxDBMapper;

    public InfluxDBDao(InfluxDB influxDB) {
        this.influxDBMapper = new InfluxDBMapper(influxDB);
    }

    public boolean insertPoint(SensorEntity data) {
        try {
            influxDBMapper.save(data);
        } catch (Exception e) {
            log.error("插入InfluxDB出现异常", e);
            return false;
        }
        return true;
    }
}
