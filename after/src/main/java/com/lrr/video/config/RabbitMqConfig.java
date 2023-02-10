package com.lrr.video.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    @Bean
    public DirectExchange directExchange(){
        //参数设置与原生的创建交换机一致，可以参考
        return new DirectExchange("BarrageExchange");
    }
    @Bean
    public Queue directQueue(){
        //参数设置与原生的创建队列一致，可以参考
        return new Queue("BarrageQueue");
    }

    /**
     * 配置一个队列的交换机绑定
     * @param directQueue 需要绑定的队列对象，参数名必须要为某个队列的Bean名称，这样就会进行自动注入
     * @param directExchange 需要绑定的交换机
     * @return
     */
    @Bean
    public Binding directBinding(Queue directQueue, DirectExchange directExchange){
        //第三个参数为routekey
        return BindingBuilder.bind(directQueue).to(directExchange).with("message");
    }
}
