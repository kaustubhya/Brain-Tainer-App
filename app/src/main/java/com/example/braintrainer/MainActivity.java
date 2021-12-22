package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;


    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");

        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        // scores are reset too
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);

        resultTextView.setText("");




        new CountDownTimer(30100,1000){
            // 30 seconds = 30000 + delay of 1/10 th of a second i.e. 100
//                code updates after every 1 second
            @Override
            public void onTick(long l) {
                // displaying the time remaining now
                // since we use long, we do slight modifications
                timerTextView.setText(String.valueOf(l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                resultTextView.setText("Done!!");
                playAgainButton.setVisibility(View.VISIBLE);

            }
        }.start();



    }


    public void chooseAnswer (View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            Log.i("Correct!!", "You got it!!");
            resultTextView.setText("Correct!!");
            score++;
        } else {
            Log.i("Wrong", " Try Again :( ");
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
//        set on click method or button will not disappear

        gameLayout.setVisibility(View.VISIBLE);

        playAgain(findViewById(R.id.timerTextView));
        // here playAgain is asking for a view, since it is not affected by any view, we can enter any view inside it from above

    }

    public void newQuestion(){
//        Enables us to generate a new question
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
//        generates a random number from 0 to 20 in the question

        sumTextView.setText(Integer.toString(a) + " + "  + Integer.toString(b));

        // Now to set the answers in random options, we run a for loop and set the answer in a random variable
        locationOfCorrectAnswer = rand.nextInt(4);
//        The correct answer should be in one of the 4 options
//        Since we are performing addition, the maximum answer could be forty, (20 + 20).
//        Therefore, we are putting random values from 0 to 40.


        answers.clear();
//        Clears the array list for new options to be added when new question comes

        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);
//               if answer is correct, go for it
            }else{
                int wrongAnswer = rand.nextInt(41);
//
//               now making sure we do not get 2 matching answers, hence we generate a new random number
//               for this we use a while loop in case the correctAnswer and wrongAnswer are matching

                while(wrongAnswer == a + b){
                    wrongAnswer = rand.nextInt(41);

                }

                answers.add(wrongAnswer);

//               so we generated 4 options, 3 wrong and 1 correct, all different
            }

        }
//       Making the options available in the buttons

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);

            goButton = findViewById(R.id.goButton);
            goButton.setVisibility(View.VISIBLE);
            gameLayout.setVisibility(View.INVISIBLE);



    }
}