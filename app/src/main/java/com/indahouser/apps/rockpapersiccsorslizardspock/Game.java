package com.indahouser.apps.rockpapersiccsorslizardspock;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by ruben on 3/23/15.
 */
public class Game {
    private Button buttonSelected;

    public Game(){
        buttonSelected = null;
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

    public void selectButton(Button button){
        if (!isButtonSelected()) {
            resetButtonSelected();
            button.setPressed(true);
            button.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
            button.setTextColor(Color.WHITE);
            this.buttonSelected = button;
        }
    }

    public void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public void resetButtonSelected(){
        if (isButtonSelected()) {
            buttonSelected.setBackgroundResource(android.R.drawable.btn_default);
            buttonSelected.setTextColor(Color.BLACK);
        }
    }

    private boolean isButtonSelected(){
        return buttonSelected!=null;
    }
}
