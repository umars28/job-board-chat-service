package com.job.board.chat.service.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    private final String SECRET_KEY = "MySuperSecretKey1234567890";

    private final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public boolean isValid(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long extractConversationId(String token) {
        return JWT.decode(token).getClaim("conversationId").asLong();
    }

    public String extractRole(String token) {
        return JWT.decode(token).getClaim("role").asString();
    }

    public String extractChatWith(String token) {
        return JWT.decode(token).getClaim("chatWith").asString();
    }
}

