package com.job.board.chat.service.controller;
import com.job.board.chat.service.service.JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

    private final JwtService jwtService;

    public ChatController(JwtService jwtService) {
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

        Long companyId = jwtService.extractCompanyId(token);

        model.addAttribute("conversationId", conversationId);
        model.addAttribute("companyId", companyId);

        return "/chat/index";
    }
}

