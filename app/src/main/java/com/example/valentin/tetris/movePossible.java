package com.example.valentin.tetris;


public interface movePossible {
    //left right down possible
    public boolean downPossible(int[][] theGrid);
    public boolean leftPossible(int[][] theGrid);
    public boolean rightPossible(int[][] theGrid);
    public boolean rotatePossible(int[][] theGrid, int[][] matrix);
    public boolean appearPossible(int[][] baseGrid);

}
