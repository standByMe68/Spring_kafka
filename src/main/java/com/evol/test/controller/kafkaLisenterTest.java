package com.evol.test.controller;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;


public class kafkaLisenterTest {


    //@KafkaListener(topics = "study",containerFactory = "kafkaListenerContainerFactory")
    public void kafka(List<ConsumerRecord<String,String>> stream){

        for (ConsumerRecord<String, String> stringStringConsumerRecord : stream) {

            System.out.println(stringStringConsumerRecord.value());

        }

    }


}
