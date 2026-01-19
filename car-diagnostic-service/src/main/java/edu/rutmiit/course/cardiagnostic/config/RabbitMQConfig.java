package edu.rutmiit.course.cardiagnostic.config;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org. springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    public static final String TOPIC_EXCHANGE = "car-events-topic";
    public static final String FANOUT_EXCHANGE = "car-events-fanout";
    @Bean public TopicExchange topicExchange() { return new TopicExchange(TOPIC_EXCHANGE); }
    @Bean public FanoutExchange fanoutExchange() { return new FanoutExchange(FANOUT_EXCHANGE); }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
