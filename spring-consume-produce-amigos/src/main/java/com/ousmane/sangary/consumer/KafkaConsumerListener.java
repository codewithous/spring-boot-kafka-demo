package com.ousmane.sangary.consumer;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

    @KafkaListener(
            groupId = "ousAmigo",
            topics = "wiki_recent_change"
    )
    void listener(String data){
        System.out.println("Listener received: "+ data + " Love from Ous :))");
    }
}
