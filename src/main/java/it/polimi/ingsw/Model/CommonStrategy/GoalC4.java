package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.Serializable;
import java.util.Vector;


public class GoalC4 implements CommonObj, Serializable {
    private final int type = 4;
    public int getType(){
        return type;
    }
    /** Six groups each containing at least 2 tiles of the same type (not necessarily
     *  in the depicted shape). The tiles of one group can be different from those of another group.
     *  @param p player to check
     *  @return
     *  */
        public boolean check(Player p){
            int count = 0;
            Vector<Tiles> riga0 = new Vector<>();
            for(int j = 0; j<5; j++) {
                for (int i = 5; i >=0; i--) {
                    riga0.add(p.getMy_shelfie().getShelf()[i][j]);
                }
                if (checkcouple(riga0)>0) count += checkcouple(riga0);
                riga0.clear();
            }


            return count >= 6;

        }
        private int checkcouple(Vector<Tiles> riga){
            int conteggio = 0;
            for (int i = 0; i < riga.size() - 1; i++) {
                if (riga.get(i).getType() == riga.get(i+1).getType()) {
                        conteggio ++;
                        i++;
                }
            }
            return conteggio;
        }
}
