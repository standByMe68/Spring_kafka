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
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

@Component
public class ConsumerController implements ApplicationListener<ContextRefreshedEvent>{
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


        KafkaConsumerConfig consumerConfig = new KafkaConsumerConfig();

        KafkaConsumer kafkaConsumer = consumerConfig.getJavaConsumerConnector();

        kafkaConsumer.subscribe(Arrays.asList("calc"));

        while (true) {

            ConsumerRecords<String,String> records = kafkaConsumer.poll(Duration.ZERO);

            for (ConsumerRecord<String, String> consumerRecords : records) {
                System.out.println("当前消息分区是:"+ consumerRecords.partition()+" 当前传入值是:"+consumerRecords.value());
            }
        }
    }

    public static void main(String[] args) {

    }
}
