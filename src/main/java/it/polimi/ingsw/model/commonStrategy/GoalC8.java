package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

/*Goal 8 = Four tiles of the same type in the four
           corners of the bookshelf.*/
public class GoalC8 implements CommonObj {
    public boolean check(Player p) {
       /* for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
            }
        }*/
        if (p.getMy_shelfie().getShelf()[0][0].getType() == p.getMy_shelfie().getShelf()[0][4].getType() && p.getMy_shelfie().getShelf()[0][4].getType() == p.getMy_shelfie().getShelf()[5][4].getType()
                && p.getMy_shelfie().getShelf()[5][0].getType() == p.getMy_shelfie().getShelf()[5][4].getType() && p.getMy_shelfie().getShelf()[0][0].getType() == p.getMy_shelfie().getShelf()[0][4].getType()) {
            System.out.println("Goal completed");
            return true;
        } else {
            System.out.println("Goal not completed");
            return false;
        }

  /*              //check on first column
                if (p.getMy_shelfie().getShelf()[0][0].getType() == p.getMy_shelfie().getShelf()[0][4].getType()) {
                    System.out.println("The Tiles are equal");
                } else return true;
                //check on last column
                if (p.getMy_shelfie().getShelf()[0][4].getType() == p.getMy_shelfie().getShelf()[5][4].getType()) {
                    System.out.println("The Tiles are equal");
                } else return true;
                //check if the bottom corners are equals
                if (p.getMy_shelfie().getShelf()[5][0].getType() == p.getMy_shelfie().getShelf()[5][4].getType()) {
                    System.out.println("The Tiles are equal");
                } else return true;
                ;
                //check if the top corners are equal
                if (p.getMy_shelfie().getShelf()[0][0].getType() == p.getMy_shelfie().getShelf()[0][4].getType()) {
                    System.out.println("The Tiles are equal");
                } else return true;

        System.out.println("Objective completed");
        return false;

    }*/
    }
}

