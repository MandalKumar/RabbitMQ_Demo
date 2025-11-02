package test.rabbitMQ.config;

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
    @Value("${rabbitMq.queue.name}")
    private String queueName;

    @Value("${rabbitMq.jsonQueue.name}")
    private String jnsonQueue;
    @Value("${rabbitMq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitMq.routingKey.name}")
    private String routingKeyName;

    @Value("${rabbitMq.jsonRoutingKey.name}")
    private String jsonRoutingKeyName;

    //spring bean for rabbitMQ Queue
    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    //This bean for queue is use store for json messages
    @Bean
    public Queue jsonQueue() {
        return new Queue(jnsonQueue);
    }

    //Spring bean for RabbitMq Exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    //Binding between Queue and Exchange using routingKey
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(routingKeyName);
    }

    //Binding between  json Queue and Exchange using JsonRoutingKey
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(jsonRoutingKeyName);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    //ConnectionFactory
    //RabbitTemplate
    //RabbitAdmin
}
