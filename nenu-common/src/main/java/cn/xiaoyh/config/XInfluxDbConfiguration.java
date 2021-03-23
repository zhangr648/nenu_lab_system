package cn.xiaoyh.config;

import okhttp3.OkHttpClient;
import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.impl.InfluxDBImpl;
import org.influxdb.impl.InfluxDBMapper;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.influx.InfluxDbOkHttpClientBuilderProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnClass({InfluxDB.class})
@EnableConfigurationProperties({XInfluxDbProperties.class})
public class XInfluxDbConfiguration {

    @Bean
    @Primary
    @ConditionalOnProperty({"spring.influx.url", "spring.influx.database"})
    public InfluxDB influxDb(XInfluxDbProperties properties, ObjectProvider<InfluxDbOkHttpClientBuilderProvider> builder) {
        InfluxDB influxDB = new InfluxDBImpl(properties.getUrl(), properties.getUser(), properties.getPassword(), determineBuilder(builder.getIfAvailable()))
                .setDatabase(properties.getDatabase())
                .setRetentionPolicy(properties.getRetentionPolicy());
        XInfluxDbProperties.Batch batch = properties.getBatch();
        if (batch.isEnable()) {
            influxDB.enableBatch(BatchOptions.DEFAULTS
                    .actions(batch.getActions())
                    .flushDuration(batch.getFlushDuration())
                    .jitterDuration(batch.getJitterDuration())
            );
        }
        return influxDB;
    }

    @Bean
    @ConditionalOnBean({InfluxDB.class})
    public InfluxDBMapper influxDBMapper(InfluxDB influxDB) {
        return new InfluxDBMapper(influxDB);
    }

    private static OkHttpClient.Builder determineBuilder(InfluxDbOkHttpClientBuilderProvider builder) {
        return builder != null ? builder.get() : new OkHttpClient.Builder();
    }
}
