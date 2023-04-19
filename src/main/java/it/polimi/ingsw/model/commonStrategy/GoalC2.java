package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

/*Goal 2 = Two columns each formed by 6 different types of tiles.*/
public class GoalC2 implements CommonObj{
    private int count = 0;

    public boolean check(Player p) {
        int column = 0;
        for (int j = 0; j < 5; j++) {
            int different = 1;
            for(int i = 1;i<6;i++){
                if(p.getMy_shelfie().getShelf()[i][j].getType() != p.getMy_shelfie().getShelf()[i][j].getType())
                    different++;
            }
            if(different == 6)
                column ++;

        }
        if(column>1) return true;
        return false;

    }

}
