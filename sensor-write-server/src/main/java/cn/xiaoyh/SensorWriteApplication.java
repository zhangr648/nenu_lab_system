package cn.xiaoyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class SensorWriteApplication {
    public static void main(String[] args) {
        SpringApplication.run(SensorWriteApplication.class, args);
    }
}
