package com.example.king.controller.api;

import com.example.king.exception.UserCantPerformAction;
import com.example.king.model.Table;
import com.example.king.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/play")
public class PlayController {
    @Autowired
    private PlayService playService;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/ready/{username}")
    public void createChess(@PathVariable String username,@RequestBody Table table) throws UserCantPerformAction {
        playService.ready(username, table);
    }


}
