package it.polimi.ingsw.commonStrategy;

import it.polimi.ingsw.Player;

/* Six groups each containing at least 2 tiles of the same type (not necessarily
in the depicted shape). The tiles of one group can be different from those of another group. */
public class GoalC4 {

        public boolean check(Player p){

            int type;
            int count = 0;

            for(int j = 0; j<5 ; j++){//columns
                for(int i=0; i<5; i++){//rows, <5 because the 6th row is the last one and can't compare it to the tiles down
                    type = p.getMy_shelfie().getShelf()[i][j].getType();
                    if ((p.getMy_shelfie().getShelf()[i++][j].getType()) == type)
                        count++;
                    if (count < 6)
                        continue;
                    else
                        return true;
                }
            }
            return false;
        }
}
