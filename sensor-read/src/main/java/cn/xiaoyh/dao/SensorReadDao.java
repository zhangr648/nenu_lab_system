package cn.xiaoyh.dao;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.querybuilder.SelectQueryImpl;
import org.springframework.stereotype.Repository;

import static cn.xiaoyh.utils.SensorUtils.*;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.*;

@Repository
public class SensorReadDao {

    private final InfluxDB influxDB;

    public SensorReadDao(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    public QueryResult queryLastMinute(String measurement, int minute) {
        minute = Math.min(MAX_MINUTE_NUMS, minute);

        Query query = queryRawMeasurement(measurement).where(gt(KEY_TIME, subTime(minute, TIME_UNIT_MINUTE)));
        return influxDB.query(query);
    }

    public QueryResult queryLastHour(String measurement, int hour) {
        hour = Math.min(MAX_HOUR_NUMS, hour);

        Query query = queryRawMeasurement(measurement).where(gt(KEY_TIME, subTime(hour, TIME_UNIT_HOUR)));
        return influxDB.query(query);
    }

    private SelectQueryImpl queryRawMeasurement(String measurement) {
        measurement = getMeasurementWithRP(RP_ONE_MONTH, measurement);
        return select().from(null, measurement);
    }

    private SelectQueryImpl queryCQMeasurement(String measurement) {
        measurement = getMeasurementWithRP(RP_ONE_YEAR, measurement);
        return select().from(null, measurement);
    }
}
