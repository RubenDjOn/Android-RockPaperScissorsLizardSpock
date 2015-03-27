package com.indahouser.apps.rockpapersiccsorslizardspock;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by ruben on 3/23/15.
 */
public class Game {
    private ImageButton userButtonSelected;
    private ImageButton computerButtonSelected;
    private int shakes;

    public Game(){
        userButtonSelected = null;
        shakes = 0;
    }

    public void changeBackground(View view, Round round){
        int colorBackground = Color.GREEN;
        if (round.isDrawRound()) {
            colorBackground = Color.YELLOW;
        } else if(round.getWinner().name.equals("Computer")) {
            colorBackground = Color.RED;
        }
        view.setBackgroundColor(colorBackground);
    }

    public void selectButton(ImageButton button){
        if (!getUserButtonSelected()) {
            resetButtonSelected();
            button.setPressed(true);
            button.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
            this.userButtonSelected = button;
        }
    }

    public void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void resetButtonSelected(){
        if (getUserButtonSelected()) {
            userButtonSelected.setBackgroundResource(android.R.drawable.btn_default);
            userButtonSelected = null;
        }
    }

    public void increaseShakes(){
        shakes += 1;
    }

    public int getShakes(){
        return shakes;
    }

    public void resetShakes(){
        shakes = 0;
    }

    public boolean canStatToPlay(){
        return userButtonSelected!=null;
    }

    private boolean getUserButtonSelected(){
        return userButtonSelected !=null;
    }
}
