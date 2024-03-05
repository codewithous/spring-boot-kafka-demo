package com.ousmane.sangary.producers;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.ousmane.sangary.handler.WikimediaChangeHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;


@Service
public class WikiProducer {


    private static final Logger LOGGER= LoggerFactory.getLogger(WikiProducer.class);



    @Value("${spring.kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public WikiProducer(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }


    // Send real time stream data to the topic
    public void sendMessage() throws InterruptedException {


        // Read real time stream data from wikimedia and add to the topic
        EventHandler eventHandler=new WikimediaChangeHandler(kafkaTemplate, topicName);
        String STREAM_URL = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder=new EventSource.Builder(eventHandler, URI.create(STREAM_URL));
        EventSource eventSource = builder.build();
        eventSource.start();


        TimeUnit.SECONDS.sleep(10);

    }
}
