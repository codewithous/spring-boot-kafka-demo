package com.ousmane.sangary.handler;


import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;


public class WikimediaChangeHandler implements EventHandler {



    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangeHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic;

    public WikimediaChangeHandler(KafkaTemplate<String, String> kafkaTemplate, String topic){
        this.kafkaTemplate=kafkaTemplate;
        this.topic = topic;
    }


    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
        LOGGER.info(String.format("event data -> %s ", messageEvent.getData()));
        kafkaTemplate.send(topic, messageEvent.getData());
    }


    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
