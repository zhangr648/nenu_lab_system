package cn.xiaoyh.dao;

import cn.xiaoyh.pojo.SensorEntity;
import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.stereotype.Repository;

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
            // e.printStackTrace();
            return false;
        }
        return true;
    }
}
