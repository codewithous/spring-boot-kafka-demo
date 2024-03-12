package com.ousmane.sangary.producer;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducer {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    public Map<String, Object> producerConfig(){
        Map<String, Object> producers=new HashMap<>();
        producers.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        producers.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producers.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return producers;
    }



    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(
            ProducerFactory<String, String> factory){
        return new KafkaTemplate<>(factory);
    }

}
