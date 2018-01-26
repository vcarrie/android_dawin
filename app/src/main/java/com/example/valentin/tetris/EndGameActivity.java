package com.example.valentin.tetris;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {

    final String EXTRA_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);


        Intent intent = getIntent();
        TextView scoreView = findViewById(R.id.score);
        int score = intent.getIntExtra(EXTRA_SCORE,0);
        String scoreString = "Votre score est de " + score + " !";

        if (intent != null) {
            scoreView.setText(scoreString);
        }
    }

    public void newGame(View view) {
        Intent intent = new Intent(EndGameActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
