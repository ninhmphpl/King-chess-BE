package com.example.king.service;

import com.example.king.exception.TableIsFull;
import com.example.king.exception.TableNotExist;
import com.example.king.model.Player;
import com.example.king.model.Table;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    private List<Table> tableList;

    public List<Table> getTableList() {
        return tableList;
    }

    public void setTableList(List<Table> tableList) {
        this.tableList = tableList;
    }

    public Table joinTable(SimpMessageHeaderAccessor headerAccessor, Integer tableId) throws Exception {
        Player player = new Player(headerAccessor.getUser().getName());
        Table table = tableList.stream().filter(tables-> tables.getId().equals(tableId)).findFirst().orElse(null);
        if(table != null){
            if (table.getPlayer1() == null){
                table.setPlayer1(player);
            } else if (table.getPlayer2() == null){
                table.setPlayer2(player);
            } else throw new TableIsFull(table);
            return table;
        } throw new TableNotExist();
    }
}
