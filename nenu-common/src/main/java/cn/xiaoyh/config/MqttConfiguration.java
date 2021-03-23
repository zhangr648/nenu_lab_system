package cn.xiaoyh.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;

import java.util.Optional;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({MqttProperties.class})
public class MqttConfiguration {

    public static final String CHANNEL_NAME = "mqttChannel";

    @Bean
    public MessageChannel mqttChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    @ConditionalOnProperty({"spring.mqtt.url"})
    public IntegrationFlow mqttInbound(MqttProperties properties) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = factory.getConnectionOptions();

        Optional.ofNullable(properties.getUsername()).ifPresent(options::setUserName);
        Optional.ofNullable(properties.getPassword()).map(String::toCharArray).ifPresent(options::setPassword);

        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(properties.getUrl(),
                properties.getClientId(), factory, properties.getTopic());
        Optional.ofNullable(properties.getCompletionTimeout()).ifPresent(adapter::setCompletionTimeout);

        return IntegrationFlows.from(adapter)
                .channel(CHANNEL_NAME)
                .get();
    }
}
