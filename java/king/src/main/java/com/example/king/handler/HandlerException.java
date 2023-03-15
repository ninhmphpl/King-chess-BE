package com.example.king.handler;

import com.example.king.exception.TableDisconnect;
import com.example.king.exception.TableIsFull;
import org.springframework.stereotype.Component;

@Component
public class HandlerException {
    public Object handler(FunctionVoid functionVoid){
        try{
            return functionVoid.run();
        }
        catch (TableDisconnect tableNotExist){
            return new HandlerError(2, tableNotExist.getMessage(), "", null);
        }
        catch (TableIsFull tableIsFull){
            return new HandlerError(1, "Table is full", "", tableIsFull.getTable());
        }
        catch (Exception e){
            return new HandlerError(0, e.getMessage(), "", null);
        }
    }

}