package cn.online.pay.service.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.SimpleMessageFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Slf4j
@Configuration
@EnableRabbit
public class MqConfig {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    RabbitListenerContainerFactory<SimpleMessageListenerContainer> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter(new Jackson2JsonMessageConverter());
        factory.setMessageConverter(messageConverter);
        return factory;
    }

    @Bean
    AmqpTemplate amqpTemplate() {
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback((message) -> {
            System.out.println("消息发送失败");
            log.info(
                    "消息发送失败, exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}",
                    message.getExchange(),
                    message.getRoutingKey(),
                    message.getReplyCode(),
                    message.getReplyText(),
                    message.getMessage().toString()
            );
        });
        return rabbitTemplate;
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        CustomExchange delayExchange = new CustomExchange("delay.pay", "x-delayed-message", true, false,
                new HashMap<>(1) {{
                    put("x-delayed-type", "direct");
                }}
        );
        rabbitAdmin.declareExchange(delayExchange);
        Queue orderQueue = new Queue("delay.pay.order", true, false, false);
        rabbitAdmin.declareQueue(orderQueue);

        rabbitAdmin.declareBinding(new Binding(
                orderQueue.getName(),
                Binding.DestinationType.QUEUE,
                delayExchange.getName(),
                "order",
                new HashMap<>(1) {{
                    put("x-delay", 1000 * 60 * 10);
                }}
        ));
        return rabbitAdmin;
    }
}
