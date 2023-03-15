package com.example.king.service;

import com.example.king.exception.UserCantPerformAction;
import com.example.king.model.Player;
import com.example.king.model.Status;
import com.example.king.model.Table;
import com.example.king.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class PlayService {
    private final Map<Integer, Unit[][]> chess = new HashMap<>();
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    @Autowired TableService tableService;

    public void crateTable(Table table){
        Unit[][] startChess = new Unit[8][8];
        chess.put(table.getId(), startChess);
        table.getPlayer1().setStatus(Status.PLAYING);
        table.getPlayer2().setStatus(Status.PLAYING);

        Unit kingP1 = Unit.getKing(table.getPlayer1(), true);
        Unit queenP1 = Unit.getQueen(table.getPlayer1(),true);
        Unit bishopP1 = Unit.getBishop(table.getPlayer1(),true);
        Unit rookP1 = Unit.getRook(table.getPlayer1(),true);
        Unit pawnP1 = Unit.getPawn(table.getPlayer1(),true);
        Unit knightP1 = Unit.getKnight(table.getPlayer1(),true);

        Unit knightP2 = Unit.getKnight(table.getPlayer2(), false);
        Unit kingP2 = Unit.getKing(table.getPlayer2(), false);
        Unit queenP2 = Unit.getQueen(table.getPlayer2(), false);
        Unit bishopP2 = Unit.getBishop(table.getPlayer2(), false);
        Unit rookP2 = Unit.getRook(table.getPlayer2(), false);
        Unit pawnP2 = Unit.getPawn(table.getPlayer2(), false);

        Arrays.fill(startChess[1], pawnP1);
        Arrays.fill(startChess[6], pawnP2);

        startChess[0][0] = rookP1;
        startChess[0][7] = rookP1;
        startChess[0][1] = knightP1;
        startChess[0][6] = knightP1;
        startChess[0][2] = bishopP1;
        startChess[0][5] = bishopP1;
        startChess[0][3] = queenP1;
        startChess[0][4] = kingP1;

        startChess[7][0] = rookP2;
        startChess[7][7] = rookP2;
        startChess[7][1] = knightP2;
        startChess[7][6] = knightP2;
        startChess[7][2] = bishopP2;
        startChess[7][5] = bishopP2;
        startChess[7][3] = kingP2;
        startChess[7][4] = queenP2;

        messagingTemplate.convertAndSend("/topic/play/" + table.getId(), startChess);
    }
    public void ready(Table table, SimpMessageHeaderAccessor accessor) throws UserCantPerformAction {
        String username = accessor.getUser().getName();
        table = tableService.getTable(table.getId());
        ready(username, table);
    }
    public void ready(String username, Table table) throws UserCantPerformAction {
        Player player1 = table.getPlayer1();
        Player player2 = table.getPlayer2();

        if(player1.getName().equals(username)){
            setStatusPlayer(player1);
        } else if (player2.getName().equals(username)) {
            setStatusPlayer(player2);
        }else throw new UserCantPerformAction();

        messagingTemplate.convertAndSend("/topic/table/" + table.getId(), table);
        if(player1.getStatus() == Status.READY && player2.getStatus() == Status.READY){
            crateTable(table);
        }
    }
    public void getChess(Table table) throws Exception {
        if(checkTable(table) && table.getPlayer1().getStatus() == Status.PLAYING){
            Unit[][] chess = this.chess.get(table.getId());
            messagingTemplate.convertAndSend("/topic/play/" + table.getId(), chess);
        }
    }

    private boolean checkTable(Table table) throws Exception {
        if(table == null) throw new Exception("Table is null");
        if(table.getPlayer1() == null) throw new Exception("Player1 is null");
        if(table.getPlayer2() == null) throw new Exception("Player2 is null");
        return true;
    }
    private static void setStatusPlayer(Player player) {
        if(player.getStatus() == Status.WAITING){
            player.setStatus(Status.READY);
        }else if (player.getStatus() == Status.READY){
            player.setStatus(Status.WAITING);
        }
    }
}
