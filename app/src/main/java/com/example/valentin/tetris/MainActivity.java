package com.example.valentin.tetris;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Integer> images = new ArrayList<>();
    public GridView baseGrid;
    public int[][] theGrid = new int[20][10];
    public boolean game = true;
    public Piece thePiece;
    public int score = 0;
    public Thread thread;
    public Chronometer simpleChronometer;
    public TextView scoreView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);

        baseGrid = findViewById(R.id.BaseGrid);
        thePiece = generatePiece();
        thePiece.appear(theGrid);
        refresh();

        simpleChronometer = (Chronometer) findViewById(R.id.Chrono);
        simpleChronometer.setFormat("Time Playing - %s");
        simpleChronometer.setBase(SystemClock.elapsedRealtime());


        scoreView = findViewById(R.id.Score);
        scoreView.setText("Score : " + score);

        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (!Thread.interrupted())
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() // start actions in UI thread
                        {
                            @Override
                            public void run() {
                                if (game) {

                                    thePiece.down(theGrid); // this action have to be in UI thread

                                    if (!thePiece.isMove() && game) {
                                        checkLineCompleated();
                                        thePiece = generatePiece();
                                        if (!thePiece.appear(theGrid)) {
                                            game = false;
                                        }
                                    }
                                    refresh();
                                } else {
                                    Intent intent = new Intent(MainActivity.this, EndGameActivity.class);
                                    intent.putExtra("score", score);
                                    startActivity(intent);
                                }


                            }
                        });
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
            }

        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        thread.start();
        simpleChronometer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        thread.interrupt();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void refresh() {
        images.clear();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                switch (theGrid[i][j]) {
                    case 1:
                        images.add(R.drawable.carre_cyan);
                        break;
                    case 2:
                        images.add(R.drawable.carre_rouge);
                        break;
                    case 3:
                        images.add(R.drawable.carre_orange);
                        break;
                    case 4:
                        images.add(R.drawable.carre_jaune);
                        break;
                    case 5:
                        images.add(R.drawable.carre_vert);
                        break;
                    case 6:
                        images.add(R.drawable.carre_bleu);
                        break;
                    case 7:
                        images.add(R.drawable.carre_violet);
                        break;
                    default:
                        images.add(R.drawable.carre_gris);
                        break;

                }
            }
        }

        baseGrid.setAdapter(new ImageAdapter(this, images));

    }

    public Piece generatePiece() {

        Random rand = new Random();

        int x = rand.nextInt(5) + 3;

        int type = rand.nextInt(7) + 1;

        switch (type) {
            case 1:
                thePiece = new Piece_I();
                break;
            case 2:
                thePiece = new Piece_J();
                break;
            case 3:
                thePiece = new Piece_L();
                break;
            case 4:
                thePiece = new Piece_O();
                break;
            case 5:
                thePiece = new Piece_S();
                break;
            case 6:
                thePiece = new Piece_T();
                break;
            case 7:
                thePiece = new Piece_Z();
                break;
            default:
                thePiece = new Piece_I();
                break;

        }

        thePiece.setPos_j(x);

        return thePiece;
    }


    public void left(View view) {
        thePiece.left(theGrid);
        refresh();
    }

    public void right(View view) {
        thePiece.right(theGrid);
        refresh();
    }

    public void down(View view) {
        thePiece.down(theGrid);
        refresh();
    }

    public void rotate(View view) {
        thePiece.rotate(theGrid);
        refresh();
    }

    public void checkLineCompleated() {
        boolean complete = true;

        for (int i = 0; i < theGrid.length; i++) {
            for (int j = 0; j < theGrid[i].length; j++) {
                if (theGrid[i][j] == 0) {
                    complete = false;
                }
            }
            if (complete) {
                resetLine(i);
                score++;
                scoreView.setText("Score : " + score);
            }
            complete = true;
        }
    }

    public void resetLine(int nbLine) {
        for (int i = 0; i < theGrid[nbLine].length; i++) {
            theGrid[nbLine][i] = 0;
        }

        for (int j = nbLine; j > 0; j--) {
            for (int k = 0; k < theGrid[j].length; k++) {
                theGrid[j][k] = theGrid[j - 1][k];
            }
        }
    }


}
