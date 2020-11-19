package io.zipcoder.casino.ceelo;

import io.zipcoder.casino.core.DiceGame;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;

public class CeeloGame extends DiceGame {
    //private Integer diceNum;

    public CeeloGame(Integer diceNum){
        super(diceNum);
        //diceNum = 3;
    }

    public boolean checkAutoWin(ArrayList<Integer> diceTossed){
        Integer[] autoWinArr = {4,5,6};
        ArrayList<Integer> autoWinList = new ArrayList<Integer>(Arrays.asList(autoWinArr));
        if(diceTossed.contains(autoWinList)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkAutoLose(ArrayList<Integer> diceTossed){
        Integer[] autoWinArr = {1,2,3};
        ArrayList<Integer> autoWinList = new ArrayList<Integer>(Arrays.asList(autoWinArr));
        if(diceTossed.contains(autoWinList)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkTriple(ArrayList<Integer> diceTossed){
        if(diceTossed.get(0) == diceTossed.get(1) && diceTossed.get(0) == diceTossed.get(2)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkPoint(ArrayList<Integer> diceTossed){
        //Integer test = Collections.frequency(diceTossed,diceTossed.get(0));
        boolean notAllSame = Collections.frequency(diceTossed,diceTossed.get(0))!=diceTossed.size();
        if((diceTossed.get(0) == diceTossed.get(1) || diceTossed.get(0) == diceTossed.get(2) || diceTossed.get(1) == diceTossed.get(2)) && notAllSame ){
            return true;
        }
        else{
            return false;
        }
    }


}
