package com.job.board.chat.service.controller;

import com.job.board.chat.service.entity.Message;
import com.job.board.chat.service.repository.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ChatController {

    private final com.job.board.chat.service.service.JwtService jwtService;
    private final MessageRepository messageRepository;

    public ChatController(com.job.board.chat.service.service.JwtService jwtService, MessageRepository messageRepository) {
        this.jwtService = jwtService;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/chat")
    public String chatPage(
            @RequestParam("conversationId") Long conversationId,
            @RequestParam("token") String token,
            Model model
    ) {
        if (!jwtService.isValid(token)) {
            throw new RuntimeException("Invalid token");
        }

        String role = jwtService.extractRole(token);
        List<Message> messages = messageRepository.findByConversationIdOrderByTimestampAsc(conversationId);

        model.addAttribute("conversationId", conversationId);
        model.addAttribute("role", role);
        model.addAttribute("messages", messages);

        return "/chat/index";
    }

}
