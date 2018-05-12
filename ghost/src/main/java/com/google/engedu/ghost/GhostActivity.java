/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.engedu.ghost;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;


public class GhostActivity extends AppCompatActivity {
    private static final String COMPUTER_TURN = "Computer's turn";
    private static final String USER_TURN = "Your turn";
    private GhostDictionary dictionary;
    private boolean userTurn = false;
    private Random random = new Random();
    private TextView ghostText ;
    private TextView gameStatus;
    private Button button_challenge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_ghost);

        ghostText= (TextView)findViewById(R.id.ghostText);
        gameStatus = (TextView)findViewById(R.id.gameStatus);
        button_challenge=(Button)findViewById(R.id.challenge);

        AssetManager assetManager = getAssets();

        try {
            dictionary =new FastDictionary(assetManager.open("words.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        onStart(null);
        button_challenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word=ghostText.getText().toString();
                String newWord=dictionary.getAnyWordStartingWith(word);
                Log.d("NEW WORD",newWord);
                if(word.length()>=4 && dictionary.isWord(word)){
                    gameStatus.setText("You won!");
                }
                else if(newWord==null){
                    gameStatus.setText("You Won!");
                }
                else{
                    gameStatus.setText("Computer Won!");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ghost, menu);
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

    /**
     * Handler for the "Reset" button.
     * Randomly determines whether the game starts with a user turn or a computer turn.
     * @param view
     * @return true
     */
    public boolean onStart(View view) {
        userTurn = random.nextBoolean();
        TextView text = (TextView) findViewById(R.id.ghostText);
        text.setText("");
        TextView label = (TextView) findViewById(R.id.gameStatus);
        if (userTurn) {
            label.setText(USER_TURN);
        } else {
            label.setText(COMPUTER_TURN);
            computerTurn();
        }
        return true;
    }

    private void computerTurn() {
        TextView label = (TextView) findViewById(R.id.gameStatus);
        String word= (String) ghostText.getText();
        Log.e("ASD",word);
        if(word.length()>4 && dictionary.isWord(word)){
            gameStatus.setText("Computer Won");
            return;
        }
        String newWord=dictionary.getAnyWordStartingWith(word);

        if(newWord==null){
            gameStatus.setText("Computer Won");
            return;
        }
        else{
            Log.e("ASD",newWord);
            char letter=newWord.charAt(word.length());
            ghostText.setText(ghostText.getText().toString()+letter);
        }

        // Do computer turn stuff then make it the user's turn again
        userTurn = true;
        label.setText(USER_TURN);
    }

    /**
     * Handler for user key presses.
     * @param keyCode
     * @param event
     * @return whether the key stroke was handled.
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if(keyCode>=event.KEYCODE_A && keyCode<=event.KEYCODE_Z){
            ghostText.setText(ghostText.getText().toString()+(char)(event.getUnicodeChar()) );
            computerTurn();
        }
        /**
         **
         **  YOUR CODE GOES HERE
         **
         **/
        return super.onKeyUp(keyCode, event);
    }
}
