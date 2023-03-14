package com.example.king.controller.socket;

import com.example.king.exception.UserCantPerformAction;
import com.example.king.model.Table;
import com.example.king.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class PlaySocketController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private PlayService playService;
    @MessageMapping("/ready")
    public void ready(@Payload Table table, SimpMessageHeaderAccessor accessor) throws UserCantPerformAction {
        playService.ready(table, accessor);

    }
}
