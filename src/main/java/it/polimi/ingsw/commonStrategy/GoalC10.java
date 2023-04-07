package it.polimi.ingsw.commonStrategy;

import it.polimi.ingsw.Player;

/* 5 tiles of the same type that make together an X */
public class GoalC10 {


    public boolean check(Player p){

        int type;
        boolean correct = false;

        for(int j = 0; j<5; j++){
            for(int i = 0; i<6 || correct; i++){
                type = p.getMy_shelfie().getShelf()[i][j].getType();
                correct = xCheck(i,j,type,p);
            }
            if (!correct)
                continue;
            else
                return true;
        }
        return false;
    }

    public boolean xCheck(int row, int column, int type, Player p){
        return ((p.getMy_shelfie().getShelf()[row + 2][column + 2].getType()) == type) && ((p.getMy_shelfie().getShelf()[row + 2][column].getType()) == type) && ((p.getMy_shelfie().getShelf()[row][column + 2].getType()) == type) && ((p.getMy_shelfie().getShelf()[row + 1][column + 1].getType()) == type);
    }
}

