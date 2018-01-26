package com.example.valentin.tetris;


public class Piece_Z extends Piece {
    public Piece_Z(){
        super();
        height = 2;
        width = 3;
        pColor = 2;
        matrix = new int[][]{{2,2,0},{0,2,2}};
        pos_i = 0;
        pos_j = 0;
    }
}
