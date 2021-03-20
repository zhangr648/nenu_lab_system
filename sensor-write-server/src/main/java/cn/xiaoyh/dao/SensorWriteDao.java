package cn.xiaoyh.dao;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Repository
public class SensorWriteDao {

    private final InfluxDB influxDB;

    public SensorWriteDao(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    public void insertSensorPoint(String measurement, Map<String, Object> sensorMap, Long time) {
        Point.Builder builder = Point.measurement(measurement);
        if (sensorMap != null) builder.fields(sensorMap);
        if (time != null) builder.time(time, TimeUnit.MILLISECONDS);
        Point point = builder.build();
        influxDB.write(point);
    }
}
