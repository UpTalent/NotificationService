package io.github.uptalent.notification.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageQueueConfig {
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.queue.verify}")
    private String verifyAccountQueue;
    @Value("${rabbitmq.queue.change.password}")
    private String changePasswordQueue;
    @Value("${rabbitmq.routing-key.verify}")
    private String verifyAccountRoutingKey;
    @Value("${rabbitmq.routing-key.change.password}")
    private String changePasswordRoutingKey;
    @Value("${rabbitmq.queue.restore-account}")
    private String restoreAccountQueue;
    @Value("${rabbitmq.routing-key.restore-account}")
    private String restoreAccountRoutingKey;

    @Bean
    public Queue queueVerify() {
        return new Queue(verifyAccountQueue);
    }

    @Bean
    public Queue queueChangePassword() {
        return new Queue(changePasswordQueue);
    }

    @Bean
    public Queue restoreAccountQueue() {
        return new Queue(restoreAccountQueue);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding bindingVerify() {
        return BindingBuilder
                .bind(queueVerify())
                .to(exchange())
                .with(verifyAccountRoutingKey);
    }

    @Bean
    public Binding bindingChangePassword() {
        return BindingBuilder
                .bind(queueVerify())
                .to(exchange())
                .with(changePasswordRoutingKey);
    }

    @Bean
    public Binding restoreAccountBinding() {
        return BindingBuilder
                .bind(restoreAccountQueue())
                .to(exchange())
                .with(restoreAccountRoutingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }
}
