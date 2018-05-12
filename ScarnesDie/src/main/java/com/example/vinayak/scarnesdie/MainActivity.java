package com.example.vinayak.scarnesdie;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    private Button button_roll;
    private Button button_hold;
    private Button button_reset;
    private ImageView image_dice;
    private TextView text_overall_score;
    private TextView text_turn_score;

    int user_overall_score=0;
    int user_turn_score=0;
    int computer_overall_score=0;
    int computer_turn_score=0;
    private boolean isUser = true;
    private Random random = new Random();

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_roll=(Button)findViewById(R.id.buttonok);
        button_hold=(Button)findViewById(R.id.buttonhold);
        button_reset=(Button)findViewById(R.id.buttonreset);
        image_dice=(ImageView)findViewById(R.id.DiceImage);
        text_overall_score=(TextView)findViewById(R.id.textView1);
        text_turn_score=(TextView)findViewById(R.id.textView2);

        button_roll.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       int r=random.nextInt(6)+1;
                       image_dice.setImageResource(getImageId(r));

                       if(r==1){
                           user_turn_score=0;
                           String a = "Your Turn Score: " + user_turn_score;
                           text_turn_score.setText(a);

                           String b = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                           text_overall_score.setText(b);

                           Toast.makeText(MainActivity.this, "Computer's Turn", Toast.LENGTH_SHORT).show();

                           /*Computer's Turn*/
                           timer=new Timer();
                           timer.schedule(new TimerTask() {
                               @Override
                               public void run() {
                                   runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           int rr = random.nextInt(6) + 1;
                                           image_dice.setImageResource(getImageId(rr));
                                           if (rr == 1) {
                                               computer_turn_score = 0;
                                               String aa = "Computer Turn Score: " + computer_turn_score;
                                               text_turn_score.setText(aa);

                                               String bb = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                                               text_overall_score.setText(bb);
//                                       Toast.makeText(MainActivity.this, "User's Turn", Toast.LENGTH_SHORT).show();
                                               if(timer!=null){
                                                   timer.cancel();
                                                   timer=null;
                                                   computer_overall_score += computer_turn_score;
                                                   computer_turn_score = 0;
                                                   String aa2 = "Computer Turn Score: " + computer_turn_score;
                                                   text_turn_score.setText(aa2);

                                                   String bb2 = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                                                   text_overall_score.setText(bb2);

//                                           Toast.makeText(MainActivity.this, "User's Turn", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                           computer_turn_score += rr;
                                           text_turn_score.setText("Computer Turn Score: " + computer_turn_score);
                                           if(computer_turn_score>15 && timer!=null){
                                               timer.cancel();
                                               timer=null;
                                               computer_overall_score += computer_turn_score;
                                               computer_turn_score = 0;
                                               String aa = "Computer Turn Score: " + computer_turn_score;
                                               text_turn_score.setText(aa);

                                               String bb = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                                               text_overall_score.setText(bb);

                                               Toast.makeText(MainActivity.this, "User's Turn", Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   });
                               }
                           },1*1000,2*1000);
                        }
                        else{
                           user_turn_score+=r;
                           String a = "Your Turn Score: " + user_turn_score;
                           text_turn_score.setText(a);

                           String b = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                           text_overall_score.setText(b);
                       }

                   }
               });

            button_hold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        user_overall_score+=user_turn_score;
                        user_turn_score = 0;
                        String a = "Your Turn Score: " + user_turn_score;
                        text_turn_score.setText(a);

                        String b = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                        text_overall_score.setText(b);
                        image_dice.setImageResource(getImageId(1));


                        /*Computer's Turn*/
                        timer=new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int rr = random.nextInt(6) + 1;
                                    image_dice.setImageResource(getImageId(rr));
                                    if (rr == 1) {
                                        computer_turn_score = 0;
                                        String aa = "Computer Turn Score: " + computer_turn_score;
                                        text_turn_score.setText(aa);

                                        String bb = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                                        text_overall_score.setText(bb);
//                                       Toast.makeText(MainActivity.this, "User's Turn", Toast.LENGTH_SHORT).show();
                                        if(timer!=null){
                                            timer.cancel();
                                            timer=null;
                                            computer_overall_score += computer_turn_score;
                                            computer_turn_score = 0;
                                            String aa2 = "Computer Turn Score: " + computer_turn_score;
                                            text_turn_score.setText(aa2);

                                            String bb2 = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                                            text_overall_score.setText(bb2);

//                                           Toast.makeText(MainActivity.this, "User's Turn", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                    computer_turn_score += rr;
                                    text_turn_score.setText("Computer Turn Score: " + computer_turn_score);
                                    if(computer_turn_score>15 && timer!=null){
                                        timer.cancel();
                                        timer=null;
                                        computer_overall_score += computer_turn_score;
                                        computer_turn_score = 0;
                                        String aa = "Computer Turn Score: " + computer_turn_score;
                                        text_turn_score.setText(aa);

                                        String bb = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                                        text_overall_score.setText(bb);

                                        Toast.makeText(MainActivity.this, "User's Turn", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            }
                        },1*1000,2*1000);
                    }
                });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_overall_score = 0;
                user_turn_score = 0;
                computer_overall_score = 0;
                computer_turn_score = 0;

                String a = "Your Turn Score: " + user_turn_score;
                text_turn_score.setText(a);

                String b = "Your Score: " + user_overall_score + " Computer Score: " + computer_overall_score;
                text_overall_score.setText(b);
            }
        });

        }


    private int getImageId(int diceNum){
        Log.e("ASD",""+diceNum);
        switch (diceNum){
            case 1: return R.drawable.dice1;
            case 2: return R.drawable.dice2;
            case 3: return R.drawable.dice3;
            case 4: return R.drawable.dice4;
            case 5: return R.drawable.dice5;
            case 6: return R.drawable.dice6;
        }
        return R.drawable.dice1;
    }
}
