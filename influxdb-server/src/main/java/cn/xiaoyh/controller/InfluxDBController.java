package cn.xiaoyh.controller;

import cn.xiaoyh.dao.InfluxDBDao;
import cn.xiaoyh.pojo.Result;
import cn.xiaoyh.pojo.SensorEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfluxDBController {

    private final InfluxDBDao influxDBDao;

    public InfluxDBController(InfluxDBDao influxDBDao) {
        this.influxDBDao = influxDBDao;
    }

    @PostMapping("/")
    public Result test(SensorEntity data) {
        if (influxDBDao.insertPoint(data)) return Result.OK;

        return Result.ERROR;
    }
}
