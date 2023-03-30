package it.polimi.ingsw.commonStrategy;

//Goal 3 = Five tiles of the same type forming a diagonal.


import it.polimi.ingsw.Player;

//TODO = capire come verificare le altre 3 diagonali della libreria
public class GoalC3 {
    public void check(Player p) {
        for (int j = 0; j < 5; j++) {
            int i = 5;
            while (((p.getMy_shelfie().getShelf()[i][j]).getType() == (p.getMy_shelfie().getShelf()[i - 1][j]).getType())) {
                    i--;
            }
        }

    }

}
