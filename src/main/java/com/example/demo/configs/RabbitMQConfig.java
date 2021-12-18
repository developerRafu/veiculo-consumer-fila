package com.example.demo.configs;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("veiculo-exchange");
    }

    @Bean
    Queue updateQueue(){
        return new Queue("veiculo-fila");
    }

}
