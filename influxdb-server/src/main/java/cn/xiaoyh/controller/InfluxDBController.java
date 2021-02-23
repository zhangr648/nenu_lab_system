package cn.xiaoyh.controller;

import cn.xiaoyh.dao.InfluxDBDao;
import cn.xiaoyh.pojo.Result;
import cn.xiaoyh.pojo.SensorEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class InfluxDBController {

    private final InfluxDBDao influxDBDao;

    public InfluxDBController(InfluxDBDao influxDBDao) {
        this.influxDBDao = influxDBDao;
    }

    @GetMapping("/**")
    public Result getTest(HttpServletRequest request) {
        return Result.success(request.getRequestURL());
    }

    @PostMapping("/")
    public Result test(@RequestBody SensorEntity data) {
        if (influxDBDao.insertPoint(data)) return Result.OK;

        return Result.ERROR;
    }
}
