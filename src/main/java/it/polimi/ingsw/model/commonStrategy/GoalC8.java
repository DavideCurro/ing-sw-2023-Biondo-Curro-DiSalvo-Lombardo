package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

/*Goal 8 = Four tiles of the same type in the four
           corners of the bookshelf.*/
public class GoalC8 {
    public void check(Player p) {
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                //check on first column
                if (p.getMy_shelfie().getShelf()[i][j].getType() == p.getMy_shelfie().getShelf()[i + 5][j].getType()) {
                    System.out.println("The Tiles are equal");
                } else break;
                //check on last column
                if (p.getMy_shelfie().getShelf()[i][j + 4].getType() == p.getMy_shelfie().getShelf()[i + 5][j + 4].getType()) {
                    System.out.println("The Tiles are equal");
                } else break;
                //check if the bottom corners are equals
                if (p.getMy_shelfie().getShelf()[i + 5][j].getType() == p.getMy_shelfie().getShelf()[i + 5][j + 4].getType()) {
                    System.out.println("The Tiles are equal");
                } else break;
                ;
                //check if the top corners are equal
                if (p.getMy_shelfie().getShelf()[i][j].getType() == p.getMy_shelfie().getShelf()[i][j + 4].getType()) {
                    System.out.println("The Tiles are equal");
                } else break;
            }
        }
        System.out.println("Objective completed");
    }
}

