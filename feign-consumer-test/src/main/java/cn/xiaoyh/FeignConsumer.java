package cn.xiaoyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignConsumer {

    public static void main(String[] args) {
        SpringApplication.run(FeignConsumer.class, args);
    }
}
