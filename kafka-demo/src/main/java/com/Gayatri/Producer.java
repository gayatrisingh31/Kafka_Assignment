package com.Gayatri;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer {
    public static void main(String[] args) throws JsonProcessingException {

        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.Gayatri.serializer.UserSerializer");
        User_File std = new User_File();
        std.setId(1);
        std.setName("Gayatri singh");
        std.setCourse("BTech.");

        try (KafkaProducer<String, User_File> producer = new KafkaProducer(properties)) {
            producer.send(new ProducerRecord<String, User_File>("user", std));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}