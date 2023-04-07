package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

//Goal 6 = Two lines each formed by 5 different types of tiles.
public class GoalC6 {
    private int count;

    public void check(Player p){
        for(int i = 0; i < 6; i++ ){
            int j = 1;
            while(((p.getMy_shelfie().getShelf()[i][j]).getType() != (p.getMy_shelfie().getShelf()[i][j - 1]).getType()) && (j < 5)){
                j++;
            }
            if(j==4){ //if j==4 we have completed a row with 5 different types of tiles
                count++;
            }
            if(count == 2){ //we have 2 rows composed by 5 different types of tiles
                break;
            }
        }
    }
}
