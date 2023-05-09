package com.websocket.chat.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

@Slf4j
public class ChatMessageDeSerializer implements Deserializer<ChatMessage> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public ChatMessage deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        ChatMessage user = null;
        try {
            user = mapper.readValue(new String(arg1, "UTF-8"), ChatMessage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public void close() {
    }
}