package com.example.order_service.publisher;

import com.example.order_service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPublisher {
    private final RabbitTemplate rabbitTemplate;

   @Value("${mq.order.topic.exchange}")
    private String topicExchange;

    @Value("${mq.order.fanout.exchange}")
    private String fanoutExchange;

    public void publish(OrderDto orderDto, String region) {
        String key = "order." + region;
        rabbitTemplate.convertAndSend(topicExchange, key, orderDto);
    }

    public void update(OrderDto orderDto, String status) {
        orderDto.setStatus(status);
        rabbitTemplate.convertAndSend(fanoutExchange,"", orderDto);
    }
}
