package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;//this is the go start button
    TextView resultTextView;
    TextView pointsTextView;
    TextView sumTextView;
    Button button0, button1, button2, button3;
    Button playAgainButton;
    ArrayList<Integer> answers = new ArrayList<Integer>(); // array list which stores all the answers
    int locationOfCorrectAnswer;//option 1 or 2 or 3 or 4
    //allows the user to choose the answer
    int score = 0; // score of the user
    int numberOfQuestion = 0;
    TextView timer;
    ConstraintLayout gameRelativeLayout;
    // to generate a new question
    public void generateQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);//generates random numbers between 0 and 20
        int b = rand.nextInt(21);

        //we need to update the text view with the values of a and b
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4); // location of the correct option
        answers.clear(); // this ensures updation of option as each time the value gets pushed to the array
        int incorrectAnswer;
        for(int i = 0; i < 4; i++)//ensuring there is only one correct answer and remaining 3 are incorrect
        {
            if(i == locationOfCorrectAnswer){
                answers.add(a + b);//adding the correct answer
            }
            else
            {
                incorrectAnswer = rand.nextInt(41);
                while(incorrectAnswer == a+b)
                {
                    incorrectAnswer = rand.nextInt(41); // a+b should not be equal to the incorrect answer
                }
                answers.add(incorrectAnswer);//adding the incorrect answer
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void playAgain(View view)
    {
        score = 0;
        numberOfQuestion = 0;
        timer.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timer.setText("0s");
                resultTextView.setText("Your score is " + score);
            }
        }.start();
    }

    public void chooseAnswer(View view)
    {
        //Log.i("tag :" , (String)view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
        {
            //Log.i("Correct", " correct");
            score++;
            resultTextView.setText("Correct!!");
        }
        else
        {
            resultTextView.setText("Incorrect!!");
        }
        numberOfQuestion++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestion));
        generateQuestion();
    }

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        gameRelativeLayout = (ConstraintLayout)findViewById(R.id.gameRelativeLayout);
        button0 = (Button)findViewById(R.id.button1);
        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button3);
        button3 = (Button)findViewById(R.id.button4);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        timer = (TextView)findViewById(R.id.timerTextView);//for the timer




    }
}