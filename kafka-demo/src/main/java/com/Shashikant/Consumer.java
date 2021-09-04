package com.Shashikant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        ConsumerListener c = new ConsumerListener();
        Thread thread = new Thread(c);
        thread.start();
    }
    public static void consumer() throws IOException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.Shashikant.serializer.UserDeserializer");
        properties.put("group.id", "test-group");


        List topics = new ArrayList();
        topics.add("user");



        try (KafkaConsumer<String, User_File> kafkaConsumer = new KafkaConsumer(properties)) {
            kafkaConsumer.subscribe(topics);
            while (true) {
                ConsumerRecords<String, User_File> messages = kafkaConsumer.poll(1000);
                for (ConsumerRecord<String, User_File> message : messages) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonStr = objectMapper.writeValueAsString(message.value());
                    FileWriter myWriter = new FileWriter("MessageReceived.txt",true);
                    myWriter.write(jsonStr+"\n");
                    myWriter.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ConsumerListener implements Runnable {


    @Override
    public void run() {
        try {
            Consumer.consumer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}