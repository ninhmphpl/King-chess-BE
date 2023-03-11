package com.example.king.exception;

import com.example.king.model.Table;
import lombok.Getter;

@Getter
public class TableIsFull extends Exception{
    private final Table table;
    public TableIsFull(Table table){
        super("Table is full");
        this.table = table;
    }

}
