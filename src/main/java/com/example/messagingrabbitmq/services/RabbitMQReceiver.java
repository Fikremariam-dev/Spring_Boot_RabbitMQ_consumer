package com.example.messagingrabbitmq.services;

import com.example.messagingrabbitmq.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import org.springframework.amqp.support.AmqpHeaders;
import com.rabbitmq.client.Channel;

import java.io.IOException;


@Service
public class RabbitMQReceiver implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }

    @RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}", ackMode = "MANUAL")
    public void receivedMessage(User user, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
            throws IOException {

        logger.info("User Details Received is.. " + user);
        channel.basicAck(tag, false);


    }

}
