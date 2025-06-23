package com.job.board.chat.service.model;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String content;
    private Long conversationId;
}
