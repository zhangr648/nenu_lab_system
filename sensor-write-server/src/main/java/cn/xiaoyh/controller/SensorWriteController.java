package cn.xiaoyh.controller;

import cn.xiaoyh.dao.SensorWriteDao;
import cn.xiaoyh.pojo.Result;
import cn.xiaoyh.pojo.SensorWriteReq;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.*;

@RestController
public class SensorWriteController {

    private final SensorWriteDao sensorWriteDao;

    public SensorWriteController(SensorWriteDao sensorWriteDao) {
        this.sensorWriteDao = sensorWriteDao;
    }

    // 该微服务只有一个控制器方法，因此匹配所有路由
    @PostMapping("/**")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name="execution.timeout.enabled", value="true"),
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="500"),
            },
            fallbackMethod = "hystrixInsertSensorPoint"
    )
    public Result insertSensorPoint(@RequestBody SensorWriteReq req) {
        sensorWriteDao.insertSensorPoint(req.getLab(), req.getSensors(), req.getTime());
        return Result.OK;
    }

    @SuppressWarnings("unused")
    private Result hystrixInsertSensorPoint(@RequestBody SensorWriteReq req) {
        return Result.ERROR;
    }
}
