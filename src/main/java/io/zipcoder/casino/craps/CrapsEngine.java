package io.zipcoder.casino.craps;

import io.zipcoder.casino.core.Player;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

public class CrapsEngine {
    Console console;
    Bets bets;
    Player player;
    Menu menu;



    // Only bet in whole numbers

    private void playerMoney() {
        if(player.getPlayerMoney() >= 1) {
            comeoutRollMenu();
        } else {
            console.println("Get a job ya bummmm, Mmmmmkay");
            menu.mainMenuLoop();
        }
    }


    private Integer comeoutRollMenu() {
        Integer passDontPass = console.getIntegerInput("Would you like to place a pass bet or a don't pass bet?\n" +
                "1. Pass bet\n" +
                "2. Don't pass bet");
        return passDontPass;
    }

    private void betType(Integer bet) {
        bet = comeoutRollMenu();
        switch(bet) {
            case 1:
                bets.passBet();
                break;
            case 2:
                bets.dontPass();
                break;
            default:
               comeoutRollMenu();
               break;
        }
    }

    public Integer getBetAmt() {
        Integer betAmt = console.getIntegerInput("How many chips would you like to bet?");
        return betAmt;
    }










}
