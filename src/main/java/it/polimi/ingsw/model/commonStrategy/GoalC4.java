package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;


public class GoalC4 implements CommonObj, Serializable {
    /** Six groups each containing at least 2 tiles of the same type (not necessarily
     *  in the depicted shape). The tiles of one group can be different from those of another group.
     *  @param p player to check
     *  @return
     *  */
        public boolean check(Player p){

            int type;
            int count = 0;

            for(int j = 0; j<4 ; j++){//columns
                for(int i=0; i<5; i++){//rows
                    type = p.getMy_shelfie().getShelf()[i][j].getType();
                    if(type == -1)
                        continue;
                    if (((p.getMy_shelfie().getShelf()[i+1][j].getType()) == type) ||  ((p.getMy_shelfie().getShelf()[i][j+1].getType()) == type) )
                        count++;
                    if (count > 5)
                        return true;
                }
            }
            return false;
        }
}
