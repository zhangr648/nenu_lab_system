package cn.xiaoyh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.mqtt")
public class MqttProperties {
    private String url;
    private String username;
    private String password;
    private String clientId = "testClient";
    private String topic = "testTopic";
    private Integer completionTimeout;
}
