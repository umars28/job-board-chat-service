package com.job.board.chat.service.controller;

import com.job.board.chat.service.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public com.job.board.chat.service.model.ChatMessage sendMessage(ChatMessage message) {
        return message;
    }
}

