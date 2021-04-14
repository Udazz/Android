package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0:yellow  1:red 2:empty

    int activePlayer = 0;
    int gameState [] = {2,2,2,2,2,2,2,2,2};
    int [][] winningPosition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;



    public void dropIn(View view) {


        ImageView counter = (ImageView) view;


        // Log.i("Counter Tag", counter.getTag().toString());    //which counter is tapped

        int tappedCounter = Integer.parseInt(counter.getTag().toString()); // tappedCounter stored the counter tag number which we pressed

        if (gameState[tappedCounter] == 2 && gameActive) { // here 2 is empty if any counter is empty then only it fill by red or yellow either not
            gameState[tappedCounter] = activePlayer;   // stored it in the gameState array which make count which counter is empty
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            // loop

            for (int[] winning : winningPosition) {       // in for each loop here wining is a random variable

                if (gameState[winning[0]] == gameState[winning[1]] && gameState[winning[1]] == gameState[winning[2]] && gameState[winning[0]] != 2) {

                    gameActive = false; // stop response from user

                    String winner = "";

                    if (activePlayer == 1) {

                        winner = "Yellow";

                    } else {

                        winner = "Red";
                    }

                    Button playAgain = (Button) findViewById(R.id.playAgain1);
                    TextView winnerTextView = (TextView) findViewById(R.id.WinnertextView);
                    winnerTextView.setText(winner + " is win !");
                    playAgain.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);

                }
            }
        }

    }
        public void playAgain (View view){

            activePlayer = 0;

            Button playAgain = (Button) findViewById(R.id.playAgain1);
            TextView winnerTextView = (TextView) findViewById(R.id.WinnertextView);
            playAgain.setVisibility(View.INVISIBLE);
            winnerTextView.setVisibility(View.INVISIBLE);
            for (int i = 0; i < gameState.length; i++) {
                gameState[i] = 2;
            }

            GridLayout grid_lay = (GridLayout) findViewById(R.id.grid_lay);

            for (int i = 0; i < grid_lay.getChildCount(); i++) {

                ImageView counter = (ImageView) grid_lay.getChildAt(i);
                counter.setImageDrawable(null);

            }

            gameActive = true;

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}