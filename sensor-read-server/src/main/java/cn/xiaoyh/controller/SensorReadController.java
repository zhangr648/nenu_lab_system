package cn.xiaoyh.controller;

import cn.xiaoyh.pojo.Result;
import cn.xiaoyh.service.SensorReadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorReadController {

    private final SensorReadService sensorReadService;

    public SensorReadController(SensorReadService sensorReadService) {
        this.sensorReadService = sensorReadService;
    }

    @GetMapping("/last/lab/{lab}/minute/{minute}")
    public Result realQueryByLastMinute(@PathVariable("lab") String lab, @PathVariable("minute") int minute) {
        return Result.success(sensorReadService.queryLastByMinute(lab, minute));
    }

    @GetMapping("/last/lab/{lab}/hour/{hour}")
    public Result realQueryByLastHour(@PathVariable("lab") String lab, @PathVariable("hour") int hour) {
        return Result.success(sensorReadService.queryLastByHour(lab, hour));
    }
}
