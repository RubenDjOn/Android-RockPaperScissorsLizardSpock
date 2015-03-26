package com.indahouser.apps.rockpapersiccsorslizardspock;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by ruben on 3/25/15.
 */
public class Round {
    private Player user;
    private Player computer;
    private Player winner;
    private HashMap loserMovements;


    public Round(Player user, Player computer){
        this.user = user;
        this.computer = computer;
        this.winner = null;

        loserMovements = new HashMap();
        loserMovements.put("Rock", new String[] {"Paper","Spock"});
        loserMovements.put("Paper", new String[] {"Scissors", "Lizard"});
        loserMovements.put("Scissors", new String[] {"Rock", "Spock"});
        loserMovements.put("Lizard", new String[] {"Rock", "Scissors"});
        loserMovements.put("Spock", new String[] {"Lizard", "Paper"});
    }

    public Player getWinner(){
        return this.winner;
    }

    public void processWinner(){
        if (userLosts(user.getLastMovement(), computer.getLastMovement())) {
            this.winner = computer;
        }
        else if(!user.getLastMovement().equals(computer.getLastMovement())){
            this.winner = user;
        }
    }

    public String getWinnerMessage(){
        String newline = System.getProperty("line.separator");
        String winnerMessage = getMovementsMessage();

        if (isDrawRound()){
            winnerMessage += "Draw";
        }
        else {
            winnerMessage += winner.name + " Wins";
        }

        return winnerMessage;
    }

    public Boolean isDrawRound(){
        return (winner==null);
    }

    public void newRound(){
        user.cleanMovement();
        computer.cleanMovement();
        winner = null;
    }

    private String getMovementsMessage(){
        String newline = System.getProperty("line.separator");
        String message = "User: " + user.getLastMovement() + newline
                + "Computer: " + computer.getLastMovement() + newline
                + newline;
        return message;
    }

    private Boolean userLosts(String userMovement, String computerMovement){
        return Arrays.asList((String[]) loserMovements.get(userMovement)).contains(computerMovement);
    }
}
