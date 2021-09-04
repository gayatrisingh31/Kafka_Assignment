package com.Shashikant.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Shashikant.User_File;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class UserSerializer implements Serializer<User_File> {

    public void configure(Map<String, ?> map, boolean b) {
    }


    public byte[] serialize(String s, User_File o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(o).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }


    public void close() {
    }
}