package com.example.valentin.tetris;


public class Piece_O extends Piece {
    public Piece_O(){
        super();
        height = 2;
        width = 2;
        pColor = 4;
        matrix = new int[][]{{4,4},{4,4}};
        pos_i = 0;
        pos_j = 0;
    }
}
