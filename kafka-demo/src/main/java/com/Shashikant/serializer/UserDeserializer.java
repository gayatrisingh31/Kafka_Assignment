package com.Shashikant.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.Shashikant.User_File;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserDeserializer implements Deserializer<User_File> {
    @Override public void close() {
    }
    @Override public void configure(Map<String, ?> arg0, boolean arg1) {
    }
    @Override
    public User_File deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        User_File user = null;
        try {
            user = mapper.readValue(arg1, User_File.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}