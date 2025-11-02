package test.rabbitMQ.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.rabbitMQ.dto.User;

@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitMq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitMq.jsonRoutingKey.name}")
    private String routingKeyName;
    private static final Logger LOGGR = LoggerFactory.getLogger(RabbitMQJsonProducer.class);
    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User message) {
        LOGGR.info("Json  message send : {}", message.toString());
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }
}
