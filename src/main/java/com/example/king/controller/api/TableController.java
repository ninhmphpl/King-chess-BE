package com.example.king.controller.api;

import com.example.king.exception.TableIsFull;
import com.example.king.exception.TableNotExist;
import com.example.king.handler.HandlerException;
import com.example.king.model.Table;
import com.example.king.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;
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

    @GetMapping("/join/{tableId}")
    public void joinRoom (@RequestHeader(value = "username") String username,
                          @PathVariable Integer tableId) throws TableNotExist, TableIsFull {
        Object object = tableService.joinTable(tableId, username);
        messagingTemplate.convertAndSend("/topic/table", object);
    }
    @PostMapping
    public void createRoom(@RequestBody Table table){
        Object object = tableService.createTable(table.getId(), table.getPlayer1().getName());
        messagingTemplate.convertAndSend("/topic/table", object);
    }

    @GetMapping("/{tableId}")
    public void getTable(@PathVariable Integer tableId){
        Object table = tableService.getTable(tableId);
        messagingTemplate.convertAndSend("/topic/table/" + tableId, table);
    }

}