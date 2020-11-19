package io.zipcoder.casino.core;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DiceGameTest {
    DiceGame diceToss;
    Integer diceNum = 2;

    @Before
    public void setup(){
    diceToss = new DiceGame(diceNum);
    //diceTossYahtzee = new DiceGame(diceNum2);
    //diceMap = new HashMap<Integer, Integer>();
    }

    @Test
    public void tossAndList() {
        /*Integer expectedMax = 6;
        Integer expectedMin = 1;
        //when
        ArrayList<Integer> tossList = diceToss.tossAndList();

        for (Integer x: tossList) {
            assertTrue(x <= 6);
            assertTrue(x >= 1);
        }
        //then*/

    }

/*    @Test //making sure the values are 1 to 6
    public void tossAndList1(){
        Integer expectedMin = 1;
        Integer expectedMax = 6;

        diceMap = diceTossYahtzee.tossAndList();
        for(Integer x: diceMap.values()){
            assertTrue(x>=expectedMin && x<=expectedMax);
        }
    }

    @Test //making sure the keys are 1 to 5
    public void tossAndList2() {
        diceMap = diceTossYahtzee.tossAndList();
        for (int i = 1; i <= 5; i++) {
            assertTrue(diceMap.containsKey(i));
        }
        assertTrue(diceMap.size()==5);
    }

    @Test
    public void saveDice(){
        diceMap = diceTossYahtzee.tossAndList();

    }*/


}

