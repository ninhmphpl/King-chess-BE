package com.example.king.handler;

import com.example.king.exception.TableIsFull;

@FunctionalInterface
public interface FunctionVoid {
    Object run() throws Exception;
}
