package cn.xiaoyh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

@SpringBootApplication
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
    }

    @Bean
    public IntegrationFlow mqttHandler() {
        return IntegrationFlows.from("mqttInputChannel")
                .handle(message -> System.out.println(message.getPayload()))
                .get();
    }
}
