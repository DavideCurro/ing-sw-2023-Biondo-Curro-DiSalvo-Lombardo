package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

/*Goal 8 = Four tiles of the same type in the four
           corners of the bookshelf.*/
public class GoalC8 implements CommonObj , Serializable {
    public boolean check(Player p) {

        if (p.getMy_shelfie().getShelf()[0][0].getType() == p.getMy_shelfie().getShelf()[0][4].getType() && p.getMy_shelfie().getShelf()[0][4].getType() == p.getMy_shelfie().getShelf()[5][4].getType()
                && p.getMy_shelfie().getShelf()[5][0].getType() == p.getMy_shelfie().getShelf()[5][4].getType() && p.getMy_shelfie().getShelf()[0][0].getType() == p.getMy_shelfie().getShelf()[0][4].getType()) {
            System.out.println("Goal completed");
            return true;
        } else {
            System.out.println("Goal not completed");
            return false;
        }
    }

}

