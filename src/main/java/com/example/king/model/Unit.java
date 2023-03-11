package com.example.king.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.example.king.model.TypeUnit.*;

@Getter
@AllArgsConstructor
public class Unit {
    private TypeUnit name;
    private Player player;
    private String image;

    static public Unit getKing(Player player, boolean black){
        String img = black?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Chess_kdt45.svg/75px-Chess_kdt45.svg.png"
                :
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Chess_klt45.svg/75px-Chess_klt45.svg.png";
        return new Unit(KING, player, img);
    }
    static public Unit getQueen(Player player, boolean black){
        String img = black?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/47/Chess_qdt45.svg/75px-Chess_qdt45.svg.png"
                :
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/15/Chess_qlt45.svg/75px-Chess_qlt45.svg.png";

        return new Unit(QUEEN, player, img);
    }
    static public Unit getRook(Player player, boolean black){
        String img = black?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Chess_rdt45.svg/75px-Chess_rdt45.svg.png"
                :
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/72/Chess_rlt45.svg/75px-Chess_rlt45.svg.png";
        return new Unit(ROOK, player, img);
    }
    static public Unit getBishop(Player player, boolean black){
        String img = black?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/Chess_bdt45.svg/75px-Chess_bdt45.svg.png"
                :
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Chess_blt45.svg/75px-Chess_blt45.svg.png";
        return new Unit(BISHOP, player, img);
    }
    static public Unit getKnight(Player player, boolean black){
        String img = black?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Chess_ndt45.svg/75px-Chess_ndt45.svg.png"
                :
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/70/Chess_nlt45.svg/75px-Chess_nlt45.svg.png";
        return new Unit(KNIGHT, player, img);
    }
    static public Unit getPawn(Player player, boolean black){
        String img = black?
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Chess_pdt45.svg/75px-Chess_pdt45.svg.png"
                :
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Chess_plt45.svg/75px-Chess_plt45.svg.png";
        return new Unit(PAWN, player, img);
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
