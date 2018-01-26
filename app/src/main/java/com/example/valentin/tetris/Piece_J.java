package com.example.valentin.tetris;


public class Piece_J extends Piece {
    public Piece_J(){
        super();
        height = 2;
        width = 3;
        pColor = 6;
        matrix = new int[][]{{6,0,0},{6,6,6}};
        pos_i = 0;
        pos_j = 0;
    }
}
