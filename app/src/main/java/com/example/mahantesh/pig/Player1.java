package com.example.mahantesh.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;


public class Player1 extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int pl2score=0,plo=0;
    private int monty;
    private int run,round=0;
    int val1;
    int val2;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player1);
        Intent intent = getIntent();
        pl2score = intent.getIntExtra("player2count",0);
        TextView textView = (TextView) findViewById(R.id.p1);
        TextView textView2 = (TextView) findViewById(R.id.p2);
        TextView roun = (TextView) findViewById(R.id.round);
        StringBuilder sb = new StringBuilder();
        sb.append("P2: ");
        sb.append(pl2score);
        String strI = sb.toString();
        textView2.setText(strI);
        run = intent.getIntExtra("play1score",0);
        StringBuilder so = new StringBuilder();
        so.append("P1: ");
        so.append(run);
        String st = so.toString();
        textView.setText(st);
        round = intent.getIntExtra("round",0);
        StringBuilder sbldr = new StringBuilder();
        sbldr.append("Round :");
        sbldr.append(round);
        String roundds = sbldr.toString();
        roun.setText(roundds);
//        Toast.makeText(this, "The score is: " + run, Toast.LENGTH_LONG).show();
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
                /*The commented out code here was the code we used
                in class to send an integer to the next activty.
                It was replaced by an alert dialog to be used to indicate
                a winner (for demonstration purposes).
                Use the alert dialogcode in your program where appropriate */
                int diece=val1+val2;
                if(diece<3){
                    diece=0;
                }
                i=diece+run;

//                Intent intents = getIntent();
//                plo = intents.getIntExtra("play1count",0);
//                int to=i+plo;
                if(i>50||i==50){
                AlertDialog alertDialog = new AlertDialog.Builder(Player1.this).create();
                alertDialog.setTitle("Player 1 Won!");
                alertDialog.setMessage("Yipeeaieahhh!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                getIntent().removeExtra("play2score");
                                getIntent().removeExtra("play1score");
                                getIntent().removeExtra("player2count");
                                getIntent().removeExtra("round");
                                Intent intentd = getIntent();
                                finish();
                                startActivity(intentd);
                            }
                        });
                alertDialog.show();


                }
                else {
                    Intent intent = new Intent(Player1.this,Player2.class);
                    intent.putExtra("play2score", pl2score);
                    intent.putExtra("play1count", i);
                    int real_round = round + 1;
                    intent.putExtra("round", real_round);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
            }


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
        BitmapDrawable pic = null;

        switch (value) {
            case 1:
                pic = (BitmapDrawable)getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = (BitmapDrawable)getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = (BitmapDrawable)getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = (BitmapDrawable)getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = (BitmapDrawable)getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = (BitmapDrawable)getResources().getDrawable(R.drawable.die_face_6);
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