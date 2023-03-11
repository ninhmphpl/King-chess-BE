package com.example.king.exception;

public class TableNotExist extends Exception{
    public TableNotExist() {
        super("Table not exit");
    }
}
