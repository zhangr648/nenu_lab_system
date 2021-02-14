package cn.xiaoyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TestProvider {

    public static void main(String[] args) {
        SpringApplication.run(TestProvider.class, args);
    }
}
