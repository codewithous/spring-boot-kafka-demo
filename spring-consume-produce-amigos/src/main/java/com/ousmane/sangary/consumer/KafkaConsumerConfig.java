package com.ousmane.sangary.consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {


    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String boostrapServer;


    public Map<String, Object> consumerFactoryConfig(){
        Map<String, Object> consumers=new HashMap<>();
        consumers.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServer);
        consumers.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumers.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return consumers;
    }


    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerFactoryConfig());
    }


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> factory(
            ConsumerFactory<String, String> consumerFactory){
        ConcurrentKafkaListenerContainerFactory<String, String> container=new
                ConcurrentKafkaListenerContainerFactory<>();
        container.setConsumerFactory(consumerFactory);
        return container;
    }
}
