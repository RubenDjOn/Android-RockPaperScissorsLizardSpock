package com.indahouser.apps.rockpapersiccsorslizardspock;

import android.util.Log;

import java.util.Random;

/**
 * Created by ruben on 3/23/15.
 */
public class Player {
    String name;
    int wins;
    String lastMovement;

    private String[] allMovements;

    public Player(String name){
        this.name = name;
        this.wins = 0;
        this.lastMovement = "";
        this.allMovements = new String[] {"Rock", "Paper", "Scissors", "Lizard", "Spock"};
    }

    public void winRound(){
        wins += 1;
    }

    public String getLastMovement(){
        if (this.lastMovement.isEmpty()){
            this.lastMovement = this.getRandomMovement();
        }
        Log.d(this.name, this.lastMovement);
        return this.lastMovement;
    }

    public void setMovement(String movement){
        this.lastMovement = movement;
    }

    public void cleanMovement(){
        this.lastMovement = "";
    }
    private String getRandomMovement(){
        int idx = new Random().nextInt(allMovements.length);
        return allMovements[idx];
    }
}
