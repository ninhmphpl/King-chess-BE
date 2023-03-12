package com.example.king.exception;

import com.example.king.handler.HandlerError;
import lombok.Getter;

@Getter
public class UserCantPerformAction extends Exception {

    private final HandlerError handlerError;
    public UserCantPerformAction() {
        super("User does not have permission to perform the action");
        handlerError = new HandlerError(3, super.getMessage(), "", null);
    }


}
