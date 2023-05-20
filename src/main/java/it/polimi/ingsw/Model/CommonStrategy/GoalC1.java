package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Player.Player;

import java.io.Serializable;
import java.util.Vector;


public class GoalC1 implements CommonObj , Serializable {

    private final int type = 1;
    public int getType(){
        return type;
    }
    /**
     *  Two groups each containing 4 tiles of the same type in a 2x2 square.
     *  The tiles of one square can be different from those of the other square.
     *  @param p player to check
     *  @return
     *  */

    public boolean check(Player p){
        Vector<Tiles> square = new Vector<>();
        boolean correct = false;
        int count = 0;
        for(int j = 0; j<4; j++){
            for(int i = 0; i<5; i++){
                square.add(p.getMy_shelfie().getShelf()[i][j]);
                square.add(p.getMy_shelfie().getShelf()[i][j+1]);
                square.add(p.getMy_shelfie().getShelf()[i+1][j]);
                square.add(p.getMy_shelfie().getShelf()[i+1][j+1]);
                correct = squareCheck(square);
                square = new Vector<>();
                if(correct){
                    count++;
                    if(count>1) return true;
                }
            }
        }
        return correct;
    }

    public boolean squareCheck(Vector<Tiles>c){
        for (int i = 1;i<c.size();i++){
            if(c.get(i).getType() != c.get(i-1).getType())  return false;
        }
        return true;
    }
}
