package com.techexchange.mobileapps.lab2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreActivity extends AppCompatActivity {
    private int score;
    static final String KEY_RESTART_QUIZ = "RetakeQuiz";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        Button againButton = findViewById(R.id.again_button);
        againButton.setOnClickListener(v -> onAgainButtonPressed());

        Button emailButton = findViewById(R.id.emailButton);
        emailButton.setOnClickListener(v -> onEmailButtonPressed());

        TextView scoreText = findViewById(R.id.score_text);
        score = getIntent().getIntExtra(QuizFragment.KEY_SCORE, 0);
        scoreText.setText("Quiz Score: " + score);
    }

    private void onEmailButtonPressed() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Quiz Scores");
        intent.putExtra(Intent.EXTRA_TEXT, "Your quiz score is: " + score);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Toast.makeText(this,
                    "The activity could not be resolved.", Toast.LENGTH_SHORT).show();
        }
    }


    private void onAgainButtonPressed() {
        Intent data = new Intent();
        data.putExtra(KEY_RESTART_QUIZ, true);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}
