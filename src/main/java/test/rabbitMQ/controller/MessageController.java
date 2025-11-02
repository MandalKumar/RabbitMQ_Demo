package test.rabbitMQ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.rabbitMQ.dto.User;
import test.rabbitMQ.publisher.RabbitMQJsonProducer;
import test.rabbitMQ.publisher.RabbitMqProducer;

@RestController
@RequestMapping("/api/v1/")
public class MessageController {
    private RabbitMqProducer producer;
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    public MessageController(RabbitMqProducer producer, RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.producer = producer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent to RabbiMQ........");
    }

    @PostMapping("/publishJson")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user) {
        rabbitMQJsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message sent to RabbiMQ........");
    }
}
