package com.example.valentin.tetris;


public interface Move {
    public void rotate(int[][] baseGrid);
    public void left(int[][] baseGrid);
    public void right(int[][] baseGrid);
    public void down(int[][] baseGrid);

}
