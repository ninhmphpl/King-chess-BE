package com.example.king.service;

import com.example.king.model.Player;
import com.example.king.model.Table;
import com.example.king.model.Unit;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class PlayService {
    private HashMap<Integer, Unit[][]> table;

    public Object crateTable(Table table){
        Unit[][] startChess = new Unit[8][8];

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




        return startChess;
    }

    public static void main(String[] args) {
        PlayService a = new PlayService();
        Unit[][] units = (Unit[][]) a.crateTable(new Table(12,new Player("ninh",null), new Player("yen", null)));
        for(Unit[] unit : units){
            System.out.println(Arrays.toString(unit));
        }
    }
}
