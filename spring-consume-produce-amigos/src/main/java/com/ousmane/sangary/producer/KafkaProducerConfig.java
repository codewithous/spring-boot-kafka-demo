package com.ousmane.sangary.producer;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
public class KafkaProducerConfig {


    @Bean
    public NewTopic ousTopic(){
    return TopicBuilder.name("wiki_recent_change")
            .build();
    }
}
