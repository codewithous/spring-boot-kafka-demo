package com.ousmane.sangary;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
public class SpringKafkaAmigos {

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaAmigos.class);
    }



    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> template){
        return args -> {
            for (int i=0; i <= 10; i++){
                template.send("wiki_recent_change", "Hello kafka :). It is your hommie man " + i);
            }
        };
    }
}