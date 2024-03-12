package com.ousmane.sangary.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/messages")
public class PublishMessageToQueueController {


    @Autowired private KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping
    public void publish(@RequestBody MessageRequest data){
        kafkaTemplate.send("wiki_recent_change", data.getMessage());
    }
}
