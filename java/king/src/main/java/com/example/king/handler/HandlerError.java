package com.example.king.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HandlerError{
    private int code;
    private String name;
    private String description;
    private Object object;

}
