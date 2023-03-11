package com.example.king.controller.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {


    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) throws Exception {
        System.out.println(event.getUser());
//        String token = getToken(event);
        logger.info("Received a new web socket connection");
    }

    private static String getToken(SessionConnectedEvent event) throws Exception {
        MessageHeaderAccessor headerAccessor =
                MessageHeaderAccessor.getAccessor(event.getMessage().getHeaders(), MessageHeaderAccessor.class);
        if (headerAccessor != null) {
            StompHeaderAccessor  stompHeaderAccessor = MessageHeaderAccessor.getAccessor(
                    (Message<?>) Objects.requireNonNull(headerAccessor.getHeader("simpConnectMessage")),
                    StompHeaderAccessor.class);
            if (stompHeaderAccessor != null) {

                    return Objects.requireNonNull(stompHeaderAccessor.getNativeHeader("token")).get(0);
            }else throw new Exception("Stomp header is null");
        }else throw new Exception("MessageHeaderAccessor is null");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if (username != null) {
            logger.info("User Disconnected : " + username);

        }
    }
}
