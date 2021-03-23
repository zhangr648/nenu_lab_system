package cn.xiaoyh;

import cn.xiaoyh.pojo.SensorWriteReq;
import cn.xiaoyh.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@EnableCircuitBreaker
@SpringBootApplication
public class SensorWriteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SensorWriteApplication.class, args);
    }

    @Autowired
    private InfluxDB influxDB;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> insertSensorPoint((String) message.getPayload());
    }

    public void insertSensorPoint(String payload) {
        Optional.ofNullable(JsonUtils.deserialize(payload, SensorWriteReq.class))
                .ifPresent(req -> {
                    if (req.getLab() == null) {
                        throw new IllegalArgumentException("measurement cannot be null");
                    }

                    Point.Builder builder = Point.measurement(req.getLab());

                    Optional.ofNullable(req.getSensors())
                            .ifPresent(builder::fields);
                    Optional.ofNullable(req.getTime())
                            .ifPresent(time -> builder.time(time, TimeUnit.MILLISECONDS));

                    Point point = builder.build();
                    influxDB.write(point);
                });
    }
}
