package com.example.valentin.tetris;

public class Piece_L extends Piece {
    public Piece_L(){
        super();
        height = 2;
        width = 3;
        pColor = 3;
        matrix = new int[][]{{0,0,3},{3,3,3}};
        pos_i = 0;
        pos_j = 0;
    }
}
