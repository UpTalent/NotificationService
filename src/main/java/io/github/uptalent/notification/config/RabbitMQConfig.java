package io.github.uptalent.notification.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.queue.verify}")
    private String queueVerify;
    @Value("${rabbitmq.queue.change.password}")
    private String queueChangePassword;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routing-key.verify}")
    private String routingKeyVerify;
    @Value("${rabbitmq.routing-key.change.password}")
    private String routingKeyChangePassword;

    @Bean
    public Queue queueVerify() {
        return new Queue(queueVerify);
    }

    @Bean
    public Queue queueChangePassword() {
        return new Queue(queueChangePassword);
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
                .with(routingKeyVerify);
    }

    @Bean
    public Binding bindingChangePassword() {
        return BindingBuilder
                .bind(queueVerify())
                .to(exchange())
                .with(routingKeyChangePassword);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
