package br.com.poc.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.ConnectionFactoryContextWrapper;
import org.springframework.stereotype.Controller;

@Controller
public class RabbitMQController {

    public static final Logger LOG = LoggerFactory.getLogger(RabbitMQController.class);

    public static final String ROUTING_KEY_CONSUMER_1 = "routing.key-one";
    public static final String ROUTING_KEY_CONSUMER_2 = "routing.key-two";

    private RabbitTemplate rabbitTemplate;
    private ConnectionFactoryContextWrapper contextWrapper;

    public RabbitMQController(RabbitTemplate rabbitTemplate, ConnectionFactoryContextWrapper contextWrapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.contextWrapper = contextWrapper;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("app-one-queue"),
            exchange = @Exchange("app-one-topic"),
            key = ROUTING_KEY_CONSUMER_1
    ))
    void consumer1(String message) {
        LOG.info("RABBIT_1: " + message);
    }


    @RabbitListener(containerFactory = "broker2", bindings = @QueueBinding(
            value = @Queue("app-two-queue"),
            exchange = @Exchange("app-two-topic"),
            key = ROUTING_KEY_CONSUMER_2
    ))
    void consumer2(String message) {
        LOG.info("RABBIT_2: " + message);
    }

}
