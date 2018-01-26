package com.example.valentin.tetris;

public class Piece_S extends Piece {
    public Piece_S(){
        super();
        height = 2;
        width = 3;
        pColor = 5;
        matrix = new int[][]{{0,5,5},{5,5,0}};
        pos_i = 0;
        pos_j = 0;
    }
}
