package cn.xiaoyh.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.influx")
public class XInfluxDbProperties {
    private String url;
    private String user;
    private String password;
    private String database;
    private String retentionPolicy = "autogen";
    private Batch batch = new Batch();

    @Data
    public static class Batch {
        private boolean enable = false;
        private int actions = 1000;
        private int flushDuration = 1000;
        private int jitterDuration = 0;
    }
}
