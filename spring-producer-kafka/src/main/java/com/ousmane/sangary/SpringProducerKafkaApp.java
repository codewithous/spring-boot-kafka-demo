package com.ousmane.sangary;


import com.ousmane.sangary.handler.WikimediaChangeHandler;
import com.ousmane.sangary.producers.WikiProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringProducerKafkaApp implements CommandLineRunner {


    @Autowired private WikiProducer producer;



    public static void main(String[] args) {
        SpringApplication.run(SpringProducerKafkaApp.class);
    }

    @Override
    public void run(String... args) throws Exception {
        producer.sendMessage();
    }
}