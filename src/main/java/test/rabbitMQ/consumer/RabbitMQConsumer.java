package test.rabbitMQ.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import test.rabbitMQ.dto.User;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGR = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = ("${rabbitMq.queue.name}"))
    public void consume(String message) {

        LOGGR.info("Received message : {} ", message);
    }

    @RabbitListener(queues = ("${rabbitMq.jsonQueue.name}"))
    public void consumeForJsonObject(User message) {

        LOGGR.info("Received Json Object : {} ", message);
    }
}

