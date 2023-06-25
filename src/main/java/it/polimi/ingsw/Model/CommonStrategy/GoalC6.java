package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.Serializable;

import java.util.Vector;
import static it.polimi.ingsw.Model.Playground.Tiles.NOT_VALID;


public class GoalC6 implements CommonObj, Serializable {
    private final int type = 6;
    public int getType(){
        return type;
    }

    private final Vector<Integer> types = new Vector<>();

    /** Goal 6 = Two lines each formed by 5 different types of tiles.
     * @param p
     * @return
     */
    public boolean check(Player p){
        int differentColumn=0;
        for(int i = 0; i < 6; i++ ){
            for (int j = 0; j<5; j++) {
                if(p.getMy_shelfie().getShelf()[i][j].getType() == NOT_VALID ) break;
                if (types.contains(p.getMy_shelfie().getShelf()[i][j].getType()))   break;
                else types.add(p.getMy_shelfie().getShelf()[i][j].getType());
            }
            if(types.size() == 5){
                differentColumn++;
            }
            types.clear();
        }
        return differentColumn > 1;
    }
}
