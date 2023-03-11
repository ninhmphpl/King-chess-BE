package com.example.king.controller.api;

import com.example.king.handler.HandlerException;
import com.example.king.model.Table;
import com.example.king.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;
    @Autowired
    private HandlerException handlerException;
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @GetMapping
    public ResponseEntity<?> findAllTable(){
        return new ResponseEntity<>(tableService.getTableList(), HttpStatus.OK);
    }
    @DeleteMapping("/{username}")
    public Object create(@PathVariable String username){
        Object object = tableService.leave(username);
        messagingTemplate.convertAndSend("/topic/table", object);
        return object;
    }
}
