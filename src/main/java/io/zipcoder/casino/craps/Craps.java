package io.zipcoder.casino.craps;
import io.zipcoder.casino.core.DiceGame;
import io.zipcoder.casino.core.GamblingGame;
import io.zipcoder.casino.utilities.Console;
import sun.tools.asm.SwitchData;
//import io.zipcoder.casino.interfaces.GamblingGame;

import java.util.ArrayList;

public class Craps extends DiceGame {
    DiceGame diceGame;
    Console console;
    private Integer numOfDie = 2;
    private Integer extraDieVal;
    ArrayList<Integer> dieRolls;
    ArrayList<Integer> dieArr;
    private Integer wagerAmt;
    private Boolean firstRoll;
    private Boolean extraRoll;


    public void Craps(Integer dieVal, Integer dieVal2) {
        this.firstRollVal = dieVal;
        this.extraDieVal = dieVal2;
        this.setDiceNum(2);
    }






    //Check player balance. Player must have at least 1 chip to buy in

    public void gameOn() {
        firstRoll = true;
        while(firstRoll) {
            extraRoll = true;
            getDie();
            rollBet();

            while(extraRoll) {
                getDie();
                extraRollBet();
            }
        }
        //Call menu
    }












    public void getDie() {
        dieRolls = diceGame.tossAndList(); // gets array list from DiceGame
        extraDieVal = dieRolls.get(0) + dieRolls.get(1); // gets 0 & 1 index value from array to add
        dieArr.add(extraDieVal);
    }


    public void rollBet() {
        if(dieArr.get(0) == 7 || dieArr.get(0) == 11) {
            //win passbet
            //lose dontpassbet
            firstRoll = playAgainBoo();
        } else if (dieArr.get(0) == 2 || dieArr.get(0) == 3 || dieArr.get(0) == 12) {
            //lose passbet
            //win dontpassbet
            firstRoll = playAgainBoo();
        }
    }

    public void extraRollBet() {
        if(dieArr.get(0) == dieArr.get(dieArr.size() - 1)) {
            //Win passbet
            //lose dontpassbet
            extraRoll = false;
            firstRoll = playAgainBoo();
        } else if (dieArr.get(dieArr.size() - 1) == 7) {
            //lose passbet
            //win dontpassbet
            extraRoll = false;
            firstRoll = playAgainBoo();
        } else {

        }
    }

    public String playAgainStr() {
        String play = console.getStringInput("Would you like to play again?  (Y or N)");
        return play;
    }



    public Boolean playAgainBoo() {
        String playStr = playAgainStr().toUpperCase();
        return !playStr.equals("N");
    }

}