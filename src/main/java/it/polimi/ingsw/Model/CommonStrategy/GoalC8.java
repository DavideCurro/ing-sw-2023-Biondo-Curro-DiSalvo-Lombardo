package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Player;

import java.io.Serializable;
import static it.polimi.ingsw.Model.Playground.Tiles.NOT_VALID;

public class GoalC8 implements CommonObj , Serializable {
    private final int type = 8;
    public int getType(){
        return type;
    }
    /**
     * Goal 8 = Four tiles of the same type in the four
     * corners of the bookshelf.
     *
     * @param p
     * @return
     */
    public boolean check(Player p) {
        int[] type = new int[4];
        type[0] = p.getMy_shelfie().getShelf()[0][0].getType();
        type[1] = p.getMy_shelfie().getShelf()[0][4].getType();
        type[2] = p.getMy_shelfie().getShelf()[5][4].getType();
        type[3] = p.getMy_shelfie().getShelf()[5][0].getType();

        for (int i = 0; i < 4; i++) {
            if (type[i] == NOT_VALID) {
              //  System.out.println("Goal not completed");
                return false;
            }
        }
        for (int j = 1; j < type.length; j++) {
            if (type[0] != type[j]) {
              //  System.out.println("Goal not completed");
                return false;
            } else {
               // System.out.println("Goal completed");
                return true;
            }
        }
        return false;

    }
}

