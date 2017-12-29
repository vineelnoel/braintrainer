package vineel.noel.com.braintrainer;

import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BrainTrainer extends AppCompatActivity {
    Button btnGo,button0,button1,button2,button3;
    ArrayList<Integer> answers = new ArrayList<>();
    TextView tvBrainResult,tvPoints,tvSum,tvBrainTimer,btnBrainPlayAgain;
    RelativeLayout rlGroupBrain;
    GridLayout glSumButtons;
    int locationOfCorrectAnswer;
    int score = 0;
    int numOfQuestions = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.brain_trainer);
        btnGo = (Button) findViewById(R.id.btnGo);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        btnBrainPlayAgain = (Button) findViewById(R.id.btnBrainPlayAgain);
        tvBrainResult = (TextView) findViewById(R.id.tvBrainResult);
        tvSum = (TextView) findViewById(R.id.tvSum);
        tvPoints = (TextView) findViewById(R.id.tvPoints);
        tvBrainTimer = (TextView) findViewById(R.id.tvBrainTimer);
        rlGroupBrain = (RelativeLayout) findViewById(R.id.rlGroupBrain);
        glSumButtons = (GridLayout) findViewById(R.id.glSumButtons);
        btnBrainPlayAgain.setVisibility(View.INVISIBLE);


    }

    public void start(View view) {
        btnGo.setVisibility(View.INVISIBLE);
        rlGroupBrain.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.btnBrainPlayAgain));
    }

    public void chooseAnswer(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            tvBrainResult.setText("Correct!");
        }else{
            tvBrainResult.setText("Wrong!");
        }
        numOfQuestions++;
        tvPoints.setText(Integer.toString(score)+"/"+Integer.toString(numOfQuestions));
        generateQuestions();
    }

    public void generateQuestions(){

        answers.clear();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        tvSum.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        int incorrectAnswer;

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                incorrectAnswer = rand.nextInt(41);
                while (incorrectAnswer == a+b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void playAgain(View view) {
        score = 0;
        numOfQuestions = 0;
        tvBrainTimer.setText("30s");
        tvPoints.setText("0/0");
        tvBrainResult.setText("");
        btnBrainPlayAgain.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        generateQuestions();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                tvBrainTimer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                btnBrainPlayAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

                tvBrainResult.setText("Your score: "+Integer.toString(score)+"/"+Integer.toString(numOfQuestions));
            }
        }.start();
    }
}
