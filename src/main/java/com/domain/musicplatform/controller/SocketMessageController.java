package com.domain.musicplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.HashMap;
import java.util.Map;

@Controller
@EnableWebSocketMessageBroker
public class SocketMessageController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private final Map<String, String> playbackSessions = new HashMap<>();
    @MessageMapping("/play")
    public void play(@Payload String message, @Header("sessionID") String sessionId) {
        synchronized (playbackSessions) {
            String previousSessionId = playbackSessions.get(message);
            if (previousSessionId != null) {
                messagingTemplate.convertAndSend("/queue/reply-" + previousSessionId,
                        "paused");
                messagingTemplate.convertAndSend("/queue/reply-" + sessionId,
                        "paused message");
                playbackSessions.remove(message);
            }
            playbackSessions.put(message, sessionId);
        }
        messagingTemplate.convertAndSend("/queue/reply-" + sessionId,
                "played");
    }
    @MessageMapping("/pause")
    public void pause(@Payload String message, @Header("sessionID") String sessionId) {
        playbackSessions.remove(message);
    }
}
