package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.Serializable;
import java.util.Vector;

public class GoalC3 implements CommonObj, Serializable {
    private final int type = 3;
    public int getType(){
        return type;
    }
    public GoalC3(){

    }

    /**
     * 4 column same
     * @param p
     * @return
     */
    public boolean check(Player p) {
        int count = 0;
        Vector<Tiles> riga0 = new Vector<>();
        for(int j = 0; j<5; j++) {
            for (int i = 5; i >=0; i--) {
                riga0.add(p.getMy_shelfie().getShelf()[i][j]);
            }
            if (checkDown(riga0)) count++;
            riga0.clear();
        }
        return count>=4;
    }
    private boolean checkDown(Vector<Tiles> riga){
        for(int i = 0; i<riga.size()-3;i++){
            if (riga.get(i).getType() == riga.get(i + 1).getType() && riga.get(i).getType() == riga.get(i + 2).getType() && riga.get(i).getType() == riga.get(i + 3).getType()) {
                return true;
            }
        }
        return false;
    }


}
