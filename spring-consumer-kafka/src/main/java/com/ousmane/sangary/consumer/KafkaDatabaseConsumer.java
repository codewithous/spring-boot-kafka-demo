package com.ousmane.sangary.consumer;


import com.ousmane.sangary.customerdb.WikimediaData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaDatabaseConsumer {


    public List<WikimediaData> dataList;


    @KafkaListener(
            topics = "wiki_recent_change",
            groupId = "ousGroup")
    public void consume(String eventMessage){
        log.info(String.format("event message received -> %s ", eventMessage));


        WikimediaData data=WikimediaData.builder()
                .wikiEventData(eventMessage)
                .build();
        dataList.add(data);

    }


    public void getAllData(){
        dataList.forEach(System.out::println);
    }
}
