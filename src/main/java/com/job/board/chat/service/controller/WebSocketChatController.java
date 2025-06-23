package com.job.board.chat.service.controller;

import com.job.board.chat.service.entity.Message;
import com.job.board.chat.service.model.ChatMessage;
import com.job.board.chat.service.repository.MessageRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

    private SimpMessagingTemplate messagingTemplate;
    private final MessageRepository messageRepository;

    public WebSocketChatController(MessageRepository messageRepository, SimpMessagingTemplate messagingTemplate) {
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(ChatMessage message) {
        Message entity = new Message();
        entity.setConversationId(message.getConversationId());
        entity.setSender(message.getSender());
        entity.setContent(message.getContent());
        messageRepository.save(entity);

        messagingTemplate.convertAndSend(
                "/topic/conversation." + message.getConversationId(),
                message
        );
    }


}

