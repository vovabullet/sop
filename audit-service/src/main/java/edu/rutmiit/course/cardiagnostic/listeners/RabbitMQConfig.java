package edu.rutmiit.course.cardiagnostic.listeners;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
    public static final String TOPIC_EXCHANGE = "car-events-topic";
    public static final String FANOUT_EXCHANGE = "car-events-fanout";
    @Bean public TopicExchange topicExchange() { return new TopicExchange(TOPIC_EXCHANGE); }
    @Bean public FanoutExchange fanoutExchange() { return new FanoutExchange(FANOUT_EXCHANGE); }
    @Bean public Queue carCreatedQueue() { return new Queue("car-audit-queue"); }
    @Bean public Binding bindingCreated(Queue carCreatedQueue, TopicExchange topicExchange) { return BindingBuilder.bind(carCreatedQueue).to(topicExchange).with("car.created"); }
    @Bean public Queue carRatingQueue() { return new Queue("car-rating-audit-queue"); }
    @Bean public Binding bindingRating(Queue carRatingQueue, FanoutExchange fanoutExchange) { return BindingBuilder.bind(carRatingQueue).to(fanoutExchange); }
    @Bean public Jackson2JsonMessageConverter messageConverter() { return new Jackson2JsonMessageConverter(); }
}
