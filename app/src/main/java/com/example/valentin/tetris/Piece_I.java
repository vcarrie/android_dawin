package com.example.valentin.tetris;

import android.graphics.Color;


public class Piece_I extends Piece {
    public Piece_I(){
        super();
        height = 4;
        width = 1;
        pColor = 1;
        matrix = new int[][]{{1},{1},{1},{1}};
        pos_i = 0;
        pos_j = 0;
    }
}
