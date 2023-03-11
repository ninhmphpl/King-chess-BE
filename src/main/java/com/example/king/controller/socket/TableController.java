package com.example.king.controller.socket;

import com.example.king.handler.HandlerException;
import com.example.king.model.Player;
import com.example.king.model.Table;
import com.example.king.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TableController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private TableService tableService;

    @Autowired
    private HandlerException handlerException;

    @MessageMapping("/chat-user")
    public void sendReply(@Payload String sendTo, SimpMessageHeaderAccessor headerAccessor){
        String username = headerAccessor.getUser().getName();
        messagingTemplate.convertAndSendToUser(sendTo, "/topic/received", "Hello "+ username);
    }

    @MessageMapping("/table.display")
    @SendTo("/topic/public")
    public List<Table> tableDisplay(SimpMessageHeaderAccessor headerAccessor) {
        return tableService.getTableList();
    }

    @MessageMapping("/table.join")
    @SendTo("/topic/public")
    public Object joinTable(@Payload Integer tableId, SimpMessageHeaderAccessor headerAccessor) {
        return handlerException.handler(()->tableService.joinTable(headerAccessor,tableId));
    }

}