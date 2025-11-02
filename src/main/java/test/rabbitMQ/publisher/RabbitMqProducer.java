package test.rabbitMQ.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer {
    @Value("${rabbitMq.exchange.name}")
    private String exchangeName;
    @Value("${rabbitMq.routingKey.name}")
    private String routingKeyName;
    private static final Logger LOGGR = LoggerFactory.getLogger(RabbitMqProducer.class);
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        LOGGR.info("message : {}", message);
        rabbitTemplate.convertAndSend(exchangeName, routingKeyName, message);
    }


}
