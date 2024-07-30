package com.example.order_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Queue almatyOrderQueue() {
        return new Queue("almatyOrder");
    }

    @Bean
    public Queue notificationsCostumerQueue() {
        return new Queue("notificationsCostumer");
    }

    @Bean
    public Queue notificationsCourierQueue() {
        return new Queue("notificationsCourier");
    }

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange("${mq.order.topic.exchange}");
    }

    @Bean
    public TopicExchange fanoutExchange() {
        return new TopicExchange("${mq.order.fanout.exchange}");
    }
}
