package com.websocket.chat.config;

import com.websocket.chat.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class Producer { // producer

    private final KafkaTemplate<String, ChatMessage> kafkaTemplate;
    private final SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        if (ChatMessage.MessageType.ENTER.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");

        System.out.println("message 1 = " + message);
        kafkaTemplate.send("kafkaTest1",message);
        System.out.println("message 2 = " + message);
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}
