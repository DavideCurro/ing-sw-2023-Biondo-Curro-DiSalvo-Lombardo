package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;
/* Two groups each containing 4 tiles of the same type in a 2x2 square.
The tiles of one square can be different from those of the other square. */

public class GoalC1 implements CommonObj{

    public boolean check(Player p){

        int type;
        boolean correct = false;

        for(int j = 0; j<4; j++){
            for(int i = 0; i<4 || correct; i++){
                type = p.getMy_shelfie().getShelf()[i][j].getType();
                correct = squareCheck(i,j,type,p);
            }
            if (!correct)
                continue;
            else
                return true;
        }
        return false;
    }

    public boolean squareCheck(int row, int column, int type, Player p){
        return ((p.getMy_shelfie().getShelf()[row + 1][column + 1].getType()) == type) && ((p.getMy_shelfie().getShelf()[row + 1][column].getType()) == type) && ((p.getMy_shelfie().getShelf()[row][column + 1].getType()) == type);
    }
}
