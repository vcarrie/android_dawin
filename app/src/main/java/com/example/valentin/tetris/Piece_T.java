package com.example.valentin.tetris;


public class Piece_T extends Piece {
    public Piece_T(){
        super();
        height = 2;
        width = 3;
        pColor = 7;
        matrix = new int[][]{{0,7,0},{7,7,7}};
        pos_i = 0;
        pos_j = 0;
    }
}
