package com.example.king.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.security.auth.Subject;
import java.security.Principal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Player implements Principal {
    private String name;
    private Status status;

    @Override
    public String getName() {
        return name;
    }
    public Player(String name){
        this.name = name;
        this.status = Status.WAITING;
    }
}
