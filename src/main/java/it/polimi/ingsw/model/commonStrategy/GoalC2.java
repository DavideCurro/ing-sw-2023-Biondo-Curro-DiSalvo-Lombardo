package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;


public class GoalC2 implements CommonObj, Serializable {
    private int count = 0;
    /**
     * Goal 2 = Two columns each formed by 6 different types of tiles.
     * @param p player to check
     * @return
     * */
    public boolean check(Player p) {
        int column = 0;
        for (int j = 0; j < 5; j++) {
            int different = 1;
            for(int i = 1;i<6;i++){
                if(p.getMy_shelfie().getShelf()[i][j].getType() != p.getMy_shelfie().getShelf()[i-1][j].getType())
                    different++;
            }
            if(different == 6)
                column ++;

        }
        if(column>1) return true;
        return false;

    }

}
