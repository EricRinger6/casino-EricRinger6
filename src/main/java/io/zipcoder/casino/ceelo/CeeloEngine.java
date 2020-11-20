package io.zipcoder.casino.ceelo;

import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CeeloEngine {

    private CeeloGame game;
    private CeeloMenu menu;
    private Menu mainMenu;
    private ArrayList<Integer> diceTossedPlayer;
    private ArrayList<Integer> diceTossedComp;
    private Integer diceNum = 3;
    private Console console;
    private Integer[] compare;
    private Integer comboType;
    private Integer whoseTurn;

    public CeeloEngine(){
        game = new CeeloGame(diceNum);
        menu = new CeeloMenu();
        console = new Console(System.in, System.out);
        mainMenu = new Menu(console);
        diceTossedPlayer = new ArrayList<Integer>();
        diceTossedComp = new ArrayList<Integer>();
        compare = new Integer[2];
        comboType = 2;
;    }

    public void userPressOne() {
        boolean isPlaying = true;
        while (isPlaying) {
            promptRoll();
            whoseTurn = 0;
            diceTossedPlayer = Toss();
            if(comboType == 0 && whoseTurn == 0){ //checks if player is auto win
                console.println(menu.youWon());
                isPlaying = tryAgain();
            }
            else if(comboType == 1 && whoseTurn == 0){ //checks if player is auto loss
                console.println(menu.youLose());
                isPlaying = tryAgain();
                //break;
            }
            else{ // you rolled a point or a triple, computer's turn to roll
                whoseTurn = 1;
                diceTossedComp = Toss();

                if(comboType == 0 && whoseTurn == 1 ){ //computer auto win
                    console.println(menu.youLose());
                    isPlaying = tryAgain();
                }
                else if (comboType == 1 && whoseTurn == 1){ //computer auto lose
                    console.println(menu.youWon());
                    isPlaying = tryAgain();
                }
                else { //determine winner base on triple and point;
                    console.println(determineWinner());
                    if(compareToss(compare, diceTossedPlayer,diceTossedComp) != 2)
                        isPlaying = tryAgain();
                }
            }
        }
    }

    public boolean tryAgain(){
        boolean playAgain = true;
        boolean loop = true;
        while (loop) {
            Integer userInput = console.getIntegerInput(menu.playAgain());
            if (userInput == 1) {
                compare[0] = 4;
                compare[1] = 4;
                comboType = 4; //reset combo type to arbitrary number other than 0 and 1 to not trip
                loop = false;
            }
            else if(userInput == 2){
                loop = false;
                playAgain = false;
            }
        }
        return playAgain;
    }

    public String determineWinner(){
        String result = "";
        if(compareToss(compare,diceTossedPlayer,diceTossedComp) == 0){
            result = menu.youWon();
        }
        else if(compareToss(compare,diceTossedPlayer,diceTossedComp) == 1){
            result = menu.youLose();
        }
        else{
            whoseTurn = 0; //sets turn back to player because there is a shootout!
            result = menu.shootOut();
        }
        delay();
        return result;
    }

    public void promptRoll() {
        boolean loop = true;
        while (loop) {
            Integer userInput = console.getIntegerInput(menu.promptRoll());
            if (userInput == 1) {
                loop = false;
            }
        }
    }

    public ArrayList<Integer> Toss(){
        //Integer[]x = {1,2,3};
        //ArrayList<Integer> y = new ArrayList<Integer>(Arrays.asList(x));
        ArrayList<Integer> result = new ArrayList<Integer>();
        boolean loop = true;
        while(loop){
            result = game.tossAndList(diceNum);   //to debug, sub game.tossAndList(diceNum) for y;
            if (whoseTurn == 1) {
                delay();
            }
            console.println(result.toString());
            if (whoseTurn == 1) {
                delay();
            }
            if(checkResult(result, whoseTurn)){ //did not toss a unique combo, returns true, prompts "got nothing" then keep looping
                if (whoseTurn == 0) {
                    Integer userInput = console.getIntegerInput(menu.gotNothing());
                }
            }
            else{
                loop = false;
            }
         }
        return result;
    }

    public boolean checkResult(ArrayList<Integer> result,Integer whoseTurn) {
    boolean loop = true;
        if (game.checkAutoWin(result)) {
            comboType = 0;
            loop = false;
        }
        else if (game.checkAutoLose(result)) {
            comboType = 1;
            loop = false;
        }
        else if (game.checkPoint(result)) {
            if (whoseTurn == 0) {
                console.println(menu.pointPrompt());
                compare[0] = 1;
            } else {
                console.println(menu.compPoint());
                compare[1] = 1;
            }
            loop = false;
        }
        else if (game.checkTriple(result)) {
            if(whoseTurn == 0) {
                console.println(menu.triplePrompt());
                compare[0] = 2;
            }
            else{
                console.println(menu.compTriple());
                compare[1] = 2;
            }
            loop = false;
        }
        return loop;
    }

    public Integer compareToss(Integer[] compare, ArrayList<Integer>player1, ArrayList<Integer>player2) {
        Integer winner = 2;
        if(compare[0] > compare[1]){ //player 1 has triple, player 2 has point, player 1 wins
            winner = 0;
        }
        else if (compare[0] < compare[1]){ //player 2 has triple, player 1 has point, player 2 wins
            winner = 1;
        }
        else { //both have triple or points
            if(compare[0] == 2){ //both players have triples, compare triples
                winner = compareTriples(player1, player2);
            }
            else{ //both players have points, compare points
                winner =comparePoints(player1, player2);
            }
        }
        return winner;
    }

    public Integer comparePoints(ArrayList<Integer> player1, ArrayList<Integer> player2) {
        Integer winner = 2;
        Integer playerSingleton = 0;
        Integer compSingleton = 0;
        for(int i = 0; i<player1.size();i++){
            if(Collections.frequency(player1, player1.get(i))==1){
                playerSingleton = player1.get(i); //gets the single dice value that is not a pair for player 1
            }
            if(Collections.frequency(player2,player2.get(i))==1){
                compSingleton = player2.get(i); //gets the single dice value that is not a pair for player 2
            }
        }
        if(playerSingleton>compSingleton){
            winner = 0;
        }
        else if(playerSingleton<compSingleton){
            winner = 1;
        }
        return winner;
    }

    public Integer compareTriples(ArrayList<Integer> player1, ArrayList<Integer> player2) {
        Integer winner = 2; // tie in triple
        if(player1.get(0)>player2.get(0)){ //player 1 wins triple
            winner = 0;
        }
        else if (player1.get(0)<player2.get(0)){ //player 2 wins triple
            winner = 1;
        }
        return winner;
    }

    public void delay() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
