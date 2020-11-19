package io.zipcoder.casino.core;

import java.util.HashMap;
import java.util.ArrayList;

public class DiceGame {
    public Integer diceNum;

   /* private HashMap<Integer, Integer> diceRollMap;
    private HashMap<Integer, Integer> diceSavedMap;*/

    public DiceGame(){
    }

    public DiceGame(Integer diceNum)
    { this.diceNum = diceNum;
    /*    diceRollMap = new HashMap<Integer, Integer>();
        diceSavedMap = new HashMap<Integer, Integer>();*/
   }

    public ArrayList<Integer> tossAndList(Integer diceNum){
        ArrayList<Integer> tossList = new ArrayList<Integer>();
        for (int i = 1; i <= diceNum; i++) {
            tossList.add(1+(int)(Math.random()*6));
        }
        return tossList;
    }

    public Integer getDiceNum(){
        return diceNum;
    }

    public void setDiceNum(Integer diceNum){
        this.diceNum = diceNum;
    }

  /*  //toss and creates a hashmap of <diceNum> # of dices
    public HashMap<Integer, Integer> tossAndList(){
        for (int i = 1; i <= diceNum; i++) {
            diceRollMap.put(i, 1+(int)(Math.random()*6));
        }
        return diceRollMap;
    }

    //returns a new hashmap of the saved dices based on user input (dicedToSave), should also reset the diceNum
    public HashMap<Integer, Integer> saveDice(Integer[] diceToSaved) {
        for (int i = 0; i < diceToSaved.length; i++) {
            diceSavedMap.put(diceToSaved[i], diceRollMap.get(diceToSaved[i]));
            diceRollMap.remove(diceToSaved[i]);
        }
        return diceSavedMap;
    }

    public HashMap<Integer,Integer> returnDice(Integer[] diceToReturn){
        Integer[] userInput = {1, 3};
        for (int i = 0; i < diceToReturn.length; i++) {
            diceRollMap.put(userInput[i], diceSavedMap.get(userInput[i]));
            diceSavedMap.remove(userInput[i]);
        }
        return diceRollMap;
    }*/

}
