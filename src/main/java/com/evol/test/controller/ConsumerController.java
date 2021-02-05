package com.evol.test.controller;

import com.evol.test.config.KafkaConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@Component
public class ConsumerController implements ApplicationListener<ContextRefreshedEvent>{

    private static final Logger log = LoggerFactory.getLogger(ConsumerController.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        KafkaConsumerConfig consumerConfig = new KafkaConsumerConfig();

        KafkaConsumer kafkaConsumer = consumerConfig.getJavaConsumerConnector();

        kafkaConsumer.subscribe(Arrays.asList("calc"));

        while (true) {

            ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ZERO);

            List<ConsumerRecord<String, String>> recordsList = new ArrayList<>();
            for (ConsumerRecord<String, String> consumerRecords : records) {
                recordsList.add(consumerRecords);
                log.info("当前消息分区是:"+ consumerRecords.partition()+" 当前传入值是:"+consumerRecords.value());
            }
            if(recordsList != null && recordsList.size()>0){
                System.out.println(recordsList.size());
            }
        }
    }

}
