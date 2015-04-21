package com.example.mahantesh.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class Player2 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int score2,play2score,player2s,player1s,play1score=0;
    int val1,val2,round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player2);
        Intent intent = getIntent();
        player1s = intent.getIntExtra("play1count",0);
        play2score = intent.getIntExtra("play2score",0);
        TextView textView = (TextView) findViewById(R.id.pl1);
        TextView textView2 = (TextView) findViewById(R.id.pl2);
        TextView roun = (TextView) findViewById(R.id.round);
        StringBuilder sb = new StringBuilder();
        sb.append("P1: ");
        sb.append(player1s);
        String strI = sb.toString();
        textView.setText(strI);
        StringBuilder sbx = new StringBuilder();
        sbx.append("P2:");
        sbx.append(play2score);
        String s1 = sbx.toString();
        textView2.setText(s1);
        round = intent.getIntExtra("round",0);
        StringBuilder sbldr = new StringBuilder();
        sbldr.append("Round :");
        sbldr.append(round);
        String roundds = sbldr.toString();
        roun.setText(roundds);
        //Toast.makeText(this, "The score is: " + play2score, Toast.LENGTH_LONG).show();

        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();

            }
        });

        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int diece=val1+val2;
                if(diece<3){
                    diece=0;
                }
                int player2count=diece+play2score;
                if(player2count>50||player2count==50){
                    AlertDialog alertDialog = new AlertDialog.Builder(Player2.this).create();
                    alertDialog.setTitle("Player 2 Won!");
                    alertDialog.setMessage("Yipeeaieahhh!");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getIntent().removeExtra("play2score");
                                    getIntent().removeExtra("play1score");
                                    getIntent().removeExtra("player1count");
                                    getIntent().removeExtra("round");
                                    Intent intent = new Intent(Player2.this,Player1.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                    startActivity(intent);
                                }
                            });
                    alertDialog.show();

                } else{
                Intent intent = new Intent(Player2.this,Player1.class);
                intent.putExtra("player2count", player2count);
                intent.putExtra("play1score",player1s);
                intent.putExtra("round", round);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }}
        });

        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);

    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        val1 = 1 + (int) (6 * Math.random());
        val2 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);

    }

    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;

        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }
        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
* AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
alertDialog.setTitle("Alert");
alertDialog.setMessage("Alert message to be shown");
alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
    new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });
alertDialog.show();*/