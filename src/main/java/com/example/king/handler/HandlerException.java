package com.example.king.handler;

import com.example.king.exception.TableDisconnect;
import com.example.king.exception.TableIsFull;
import com.example.king.exception.TableNotExist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

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
            e.printStackTrace();
        }
        return null;
    }

}
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class HandlerError{
    private int code;
    private String name;
    private String description;
    private Object object;

}
