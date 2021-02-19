package cn.xiaoyh.dao;


import cn.xiaoyh.pojo.SensorEntity;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class InfluxDBDao {

    private final InfluxDB influxDB;

    public InfluxDBDao(InfluxDB influxDB) {
        this.influxDB = influxDB;
    }

    public boolean insertPoint(SensorEntity data) {
        try {
            influxDB.setDatabase("test");
            Point point = Point.measurement("test")
                    .tag("college", data.getCollege())
                    .tag("lab", data.getLab())
                    .tag("sensor", data.getSensor())
                    .addField("value", data.getValue())
                    .addField("unit", data.getUnit())
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .build();
            influxDB.write(point);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
