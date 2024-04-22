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
            // If email is already exist, it turns the session
            String previousSessionId = playbackSessions.get(message);

            // To send stop message to the previous session's music player of client
            if (previousSessionId != null) {
                messagingTemplate.convertAndSend("/queue/reply-" + previousSessionId,
                        "paused");
                // Send message about other client's music player to new client
                messagingTemplate.convertAndSend("/queue/reply-" + sessionId,
                        "paused message");
                playbackSessions.remove(message); // Other client is deleting to put new client
                // We can delete it because no need to it anymore.
            }
            playbackSessions.put(message, sessionId);
        }
        // It is not necessary but as you see you can send new message, it's going to turn anyway
        messagingTemplate.convertAndSend("/queue/reply-" + sessionId,
                "played");
    }
    // If client stops to listen, we should remove it from playback
    @MessageMapping("/pause")
    public void pause(@Payload String message, @Header("sessionID") String sessionId) {
        playbackSessions.remove(message);
    }
}
