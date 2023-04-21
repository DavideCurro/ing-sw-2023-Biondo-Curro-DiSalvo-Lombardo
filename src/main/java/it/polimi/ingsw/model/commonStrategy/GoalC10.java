package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

/* 5 tiles of the same type that make together an X */
public class GoalC10 implements CommonObj{


    public boolean check(Player p){

        int type;
        boolean correct = false;

         for(int j = 0; j<3; j++){
            for(int i = 0; i<4 ; i++){
                type = p.getMy_shelfie().getShelf()[i][j].getType();
                if(type == -1)
                    continue;
                correct = xCheck(i,j,type,p);
                if (correct)
                    return true;
            }
        }
        return false;
    }

    public boolean xCheck(int row, int column, int type, Player p){
        return (((p.getMy_shelfie().getShelf()[row + 2][column + 2].getType()) == type) &&
                ((p.getMy_shelfie().getShelf()[row + 2 ][column].getType()) == type) &&
                ((p.getMy_shelfie().getShelf()[row][column + 2].getType()) == type) &&
                ((p.getMy_shelfie().getShelf()[row + 1][column + 1].getType()) == type));
    }
}

