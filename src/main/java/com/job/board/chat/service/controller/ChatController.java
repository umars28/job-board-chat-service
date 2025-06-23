package com.job.board.chat.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    private final com.job.board.chat.service.service.JwtService jwtService;

    public ChatController(com.job.board.chat.service.service.JwtService jwtService) {
        this.jwtService = jwtService;
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

        model.addAttribute("conversationId", conversationId);
        model.addAttribute("role", role);

        return "/chat/index";
    }
}
