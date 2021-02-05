package com.evol.test.config;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import lombok.Data;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

@Data
public class KafkaConsumerConfig {

    private KafkaConsumer javaConsumerConnector;

    public KafkaConsumerConfig(){
        Properties properties = new Properties();
        properties.put("zookeeper.session.timeout.ms", "50000");
        properties.put("zookeeper.connection.timeout.ms", "100000");
        properties.put("bootstrap.servers", "192.168.2.152:9092,192.168.2.153:9092,192.168.2.154:9092");
        properties.put("rebalance.backoff.ms", "2000");
        properties.put("rebalance.max.retries", "10");
        properties.put("group.id", "client_" + 1);
        properties.put("enable.auto.commit", "true");
        properties.put("fetch.min.bytes",1024000);
        properties.put("fetch.max.wait.ms", 1000);
        properties.put("max.poll.records", 1000);
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.javaConsumerConnector = new KafkaConsumer(properties);


    }


}
