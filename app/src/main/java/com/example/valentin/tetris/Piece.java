package com.example.valentin.tetris;


import android.util.Log;

public class Piece implements Move, movePossible {

    protected int width;
    protected int height;
    protected int[][] matrix;
    protected int pos_i;
    protected int pos_j;
    protected int pColor;
    protected boolean move = true;
    protected int state = 0;

    public Piece() {

    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getPos_i() {
        return pos_i;
    }

    public void setPos_i(int pos_i) {
        this.pos_i = pos_i;
    }

    public int getPos_j() {
        return pos_j;
    }

    public void setPos_j(int pos_j) {
        this.pos_j = pos_j;
    }

    public int getpColor() {
        return pColor;
    }

    public void setpColor(int pColor) {
        this.pColor = pColor;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }



    public boolean appear(int[][] baseGrid) {
        if (appearPossible(baseGrid)){
            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    baseGrid[i][j] = matrix[i - pos_i][j - pos_j];
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean appearPossible(int[][] baseGrid){
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (baseGrid[i + pos_i][j + pos_j] != 0 && matrix[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public void rotate(int[][] baseGrid) {

        int[][] newMatrix = new int[width][height];

        if (rotatePossible(baseGrid, newMatrix)) {
            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    baseGrid[i][j] = 0;
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    newMatrix[j][i] = matrix[i][j];
                }

            }
            matrix = newMatrix;

            int theHeight = height;
            height = width;
            width = theHeight;


            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    baseGrid[i][j] = matrix[i - pos_i][j - pos_j];
                }
            }
        }
    }

    @Override
    public void left(int[][] baseGrid) {
        if (leftPossible(baseGrid)) {
            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && baseGrid[i][j] != 0))
                        baseGrid[i][j] = 0;
                }
            }

            pos_j -= 1;

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && baseGrid[i][j] != 0))
                        baseGrid[i][j] = matrix[i - pos_i][j - pos_j];
                }
            }
        }
    }

    @Override
    public void right(int[][] baseGrid) {
        if (rightPossible(baseGrid)) {
            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && baseGrid[i][j] != 0))
                        baseGrid[i][j] = 0;
                }
            }

            pos_j += 1;

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && baseGrid[i][j] != 0))
                        baseGrid[i][j] = matrix[i - pos_i][j - pos_j];
                }
            }
        }
    }

    @Override
    public void down(int[][] baseGrid) {

        if (downPossible(baseGrid)) {

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && baseGrid[i][j] != 0))
                        baseGrid[i][j] = 0;
                }
            }

            pos_i += 1;

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && baseGrid[i][j] != 0))
                        baseGrid[i][j] = matrix[i - pos_i][j - pos_j];
                }
            }
        }
    }

    @Override
    public boolean downPossible(int[][] theGrid) {

        boolean possible = true;
        int[][] canvas = new int[20][10];

        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = theGrid[i][j];
            }
        }

        if (pos_i + 1 + height <= canvas.length) {

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && canvas[i][j] != 0))
                        canvas[i][j] = 0;
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (canvas[i + pos_i + 1][j + pos_j] != 0 && matrix[i][j] != 0) {
                        possible = false;
                        Log.d("DownPossible", "Mouvement impossible collision");
                    }
                }
            }
        } else {
            possible = false;
            Log.d("DownPossible", "Mouvement impossible sortie de tableau");

        }
        if (!possible){
            move = false;
        }
        return possible;
    }

    @Override
    public boolean leftPossible(int[][] theGrid) {
        boolean possible = true;
        int[][] canvas = new int[20][10];

        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                canvas[i][j] = theGrid[i][j];
            }
        }

        if (pos_j - 1 >= 0) {

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && canvas[i][j] != 0))
                        canvas[i][j] = 0;
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (canvas[i + pos_i][j + pos_j - 1] != 0 && matrix[i][j] != 0) {
                        possible = false;
                        Log.d("LeftPossible", "Mouvement impossible collision");
                    }
                }
            }
        } else {
            possible = false;
            Log.d("LeftPossible", "Mouvement impossible sortie de tableau");

        }
        return possible;
    }

    @Override
    public boolean rightPossible(int[][] theGrid) {

        boolean possible = true;
        int[][] canvas = new int[20][10];

        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[0].length; j++) {
                    canvas[i][j] = theGrid[i][j];
            }
        }

        if (pos_j + 1 + width <= theGrid[0].length) {

            for (int i = pos_i; i < height + pos_i; i++) {
                for (int j = pos_j; j < width + pos_j; j++) {
                    if (!(matrix[i - pos_i][j - pos_j] == 0 && canvas[i][j] != 0))
                        canvas[i][j] = 0;
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (canvas[i + pos_i][j + pos_j + 1] != 0 && matrix[i][j] != 0) {
                        possible = false;
                        Log.d("RightPossible", "Mouvement impossible collision");
                    }
                }
            }
        } else {
            possible = false;
            Log.d("RightPossible", "Mouvement impossible sortie de tableau");

        }
        return possible;
    }

    @Override
    public boolean rotatePossible(int[][] theGrid, int[][] matrix) {
        boolean possible = true;

        if (pos_i + matrix.length > theGrid.length) {
            Log.d("rotatePossible1", "Mouvement impossible");
            possible = false;
        }

        if (pos_j + matrix[0].length > theGrid[0].length) {
            Log.d("rotatePossible2", "Mouvement impossible");
            possible = false;
        }

        return possible;
    }

}
