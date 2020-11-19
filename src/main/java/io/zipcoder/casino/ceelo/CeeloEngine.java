package io.zipcoder.casino.ceelo;

import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CeeloEngine {
    private CeeloGame game;
    private CeeloMenu menu;
    private ArrayList<Integer> diceTossedPlayer;
    private ArrayList<Integer> diceTossedComp;
    private Integer diceNum = 3;
    private Console console;
    private Integer[] compare;
    private Integer promptOpt;

    public CeeloEngine(){
        game = new CeeloGame(diceNum);
        menu = new CeeloMenu();
        console = new Console(System.in, System.out);
        diceTossedPlayer = new ArrayList<Integer>();
        diceTossedComp = new ArrayList<Integer>();
        compare = new Integer[2];
        promptOpt = 1;
    }

    public void userPressOne() {
        while (true) {
            try {
                switch (promptOpt) {
                    case 1:
                        console.println(menu.welcome());
                        promptRoll();
                        break;
                    case 2:
                        Integer userInputTryAgain = console.getIntegerInput(menu.tryAgain());
                        if (userInputTryAgain == 1) {
                            promptRoll();
                        }
                      /* else if(userInputTryAgain == 2){

                       }*/
                        break;
                    case 3:
                        Integer userInputPlayAgain = console.getIntegerInput(menu.playAgain());
                        if (userInputPlayAgain == 1) {
                            promptRoll();
                        }
                        /* else if(userInputTryAgain == 2){
                            CasinoMenu
                        }*/
                        break;
                    default:
                        console.println("Please enter valid option");
                }
            }
            catch (NullPointerException e) {
            }
        }
    }

    public void promptRoll() {
        boolean loop = true;
        while (loop) {
            Integer userInput = console.getIntegerInput(menu.promptRoll());
            if (userInput == 1) {
                playerToss();
                loop = false;
            }
        }
    }

    public void playerToss() {
        //Integer[]x = {2,2,2};
        //ArrayList<Integer> y = new ArrayList<Integer>(Arrays.asList(x));
        boolean loop = true;
        while(loop){
            ArrayList<Integer> result = new ArrayList<Integer>(game.tossAndList(diceNum));   //sub for debug, game.tossAndList(diceNum)
            console.println(result.toString());
            if(game.checkAutoWin(result)) {
                console.println(menu.youWon());
                promptOpt = 3;
                userPressOne();
                break;
            }
            else if (game.checkAutoLose(result)){
                console.println(menu.youLose());
                promptOpt = 2;
                userPressOne();
                break;
            }
            else if (game.checkPoint(result)) {
                console.println(menu.pointPrompt());
                diceTossedPlayer = result;
                compare[0] = 1;
                ComputerToss();
                loop = false;
                break;
            }
            else if (game.checkTriple(result)){
                console.println(menu.triplePrompt());
                diceTossedPlayer = result;
                compare[0] = 2;
                ComputerToss();
                loop = false;
                break;
            }
            else {
                Integer userInput = console.getIntegerInput(menu.gotNothing());
            }
        }
    }

    public void ComputerToss() {

        //Integer[]x = {2,2,2};
        //ArrayList<Integer> y = new ArrayList<Integer>(Arrays.asList(x));
        boolean loop = true;
        while (loop) {
            delay();
            ArrayList<Integer> result = new ArrayList<Integer>(game.tossAndList(diceNum)); //sub for debug, game.tossAndList(diceNum)
            console.println(result.toString());
            if (game.checkAutoWin(result)) {
                delay();
                console.println(menu.youLose());
                promptOpt = 2;
                userPressOne();
                break;

            } else if (game.checkAutoLose(result)) {
                delay();
                console.println(menu.youWon());
                promptOpt = 3;
                userPressOne();
                break;
            }
            else if (game.checkPoint(result)) {
                console.println(menu.compPoint());
                diceTossedComp = result;
                compare[1] = 1;
                delay();
                CompareToss();
                loop = false;
                break;
            }
            else if (game.checkTriple(result)) {
                console.println(menu.compTriple());
                diceTossedComp = result;
                compare[1] = 2;
                delay();
                CompareToss();
                loop = false;
                break;
            }
            //adding for debugging computer
            //else {
            //        Integer userInput = console.getIntegerInput(menu.gotNothing());
            //}
            //end debug
        }
    }

    public void CompareToss() {
        if(compare[0] > compare[1]){
            delay();
            console.println(menu.youWon());
            promptOpt = 3;
            userPressOne();
        }
        else if (compare[0] < compare[1]){
            delay();
            console.println(menu.youLose());
            promptOpt = 2;
            userPressOne();
        }
        else {
            if(compare[0] == 2){
                compareTriples();
            }
            else{
                comparePoints();
            }
        }
    }

    public void comparePoints() {
        Integer playerSingleton = 0;
        Integer compSingleton = 0;
        for(int i = 0; i<diceTossedPlayer.size();i++){
            if(Collections.frequency(diceTossedPlayer, diceTossedPlayer.get(i))==1){
                playerSingleton = diceTossedPlayer.get(i);
            }
            if(Collections.frequency(diceTossedComp,diceTossedComp.get(i))==1){
                compSingleton = diceTossedComp.get(i);
            }
        }
        if(playerSingleton>compSingleton){
            delay();
            console.println(menu.youWon());
            promptOpt = 3;
            userPressOne();
        }
        else if(playerSingleton<compSingleton){
            delay();
            console.println(menu.youLose());
            promptOpt = 2;
            userPressOne();
        }
        else{
            console.println(menu.shootOut());
            promptRoll();
        }
    }

    public void compareTriples() {
        if(diceTossedPlayer.get(0)>diceTossedComp.get(0)){
            delay();
            console.println(menu.youWon());
            promptOpt = 3;
            userPressOne();
        }
        else if (diceTossedPlayer.get(0)<diceTossedComp.get(0)){
            delay();
            console.println(menu.youLose());
            promptOpt = 2;
            userPressOne();
        }
        else{
            console.println(menu.shootOut());
            promptRoll();
        }
    }

    public void delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
