package com.example.king.service;

import com.example.king.exception.TableIsFull;
import com.example.king.exception.TableNotExist;
import com.example.king.model.Player;
import com.example.king.model.Table;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableService {
    private final List<Table> tableList = new ArrayList<>();

    public List<Table> getTableList() {
        return tableList;
    }


    public Object joinTable(SimpMessageHeaderAccessor headerAccessor, Integer tableId) throws Exception {
        String username = headerAccessor.getUser().getName();
        return joinTable(tableId, username);
    }

    public Object joinTable(Integer tableId, String username) throws TableIsFull, TableNotExist {
        Player player = new Player(username);
        Table table = tableList.stream().filter(tables-> tables.getId().equals(tableId)).findFirst().orElse(null);
        if(table != null){
            if (table.getPlayer1() == null){
                table.setPlayer1(player);
            } else if (table.getPlayer2() == null){
                table.setPlayer2(player);
            } else throw new TableIsFull(table);
            return tableList;
        }
        throw new TableNotExist();
    }

    public Object createTable(SimpMessageHeaderAccessor headerAccessor, Integer tableId){
        String username = headerAccessor.getUser().getName();
        return createTable(tableId, username);
    }

    public Object createTable(Integer tableId, String username) {
        Player player = new Player(username);
        Table table = new Table(tableId, player, null);
        tableList.add(table);
        return tableList;
    }

    public Object leave(String username){
        for (int i = 0 ; i < tableList.size() ; i ++){
            if(tableList.get(i).getPlayer2()!= null && tableList.get(i).getPlayer2().getName().equals(username)){
                tableList.get(i).setPlayer2(null);
            }
            if (tableList.get(i).getPlayer1()!= null && tableList.get(i).getPlayer1().getName().equals(username)){
                tableList.get(i).setPlayer1(null);
            }
            if(tableList.get(i).getPlayer1() == null && tableList.get(i).getPlayer2() == null){
                tableList.remove(i);
                i--;
            }
        }
        return tableList;
    }
}
