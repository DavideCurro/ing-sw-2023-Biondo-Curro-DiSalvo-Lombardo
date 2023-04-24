package it.polimi.ingsw.model.commonStrategy;

//Goal 11 = Five tiles of the same type forming a diagonal.


import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;
import java.util.Vector;

public class GoalC11 implements CommonObj , Serializable {

        Vector<Tiles> tmp = new Vector<>();
        Vector<Tiles> tmp1 = new Vector<>();
        Vector<Tiles> tmp2 = new Vector<>();
        Vector<Tiles> tmp3 = new Vector<>();

        private void setTmp(Player p){
            //check gialla
            tmp.add(new Tiles(p.getMy_shelfie().getShelf()[1][4].getType(), 1, 4));
            tmp.add(new Tiles(p.getMy_shelfie().getShelf()[2][3].getType(), 2, 3));
            tmp.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
            tmp.add(new Tiles(p.getMy_shelfie().getShelf()[4][1].getType(), 4, 1));
            tmp.add(new Tiles(p.getMy_shelfie().getShelf()[5][0].getType(), 5, 0));

            //check rossa
            tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[1][0].getType(), 1, 0));
            tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[2][1].getType(), 2, 1));
            tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
            tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[4][3].getType(), 4, 3));
            tmp1.add(new Tiles(p.getMy_shelfie().getShelf()[5][4].getType(), 5, 4));

            //check verde
            tmp2.add(new Tiles(p.getMy_shelfie().getShelf()[0][4].getType(), 0, 4));
            tmp2.add(new Tiles(p.getMy_shelfie().getShelf()[3][1].getType(), 3, 1));
            tmp2.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
            tmp2.add(new Tiles(p.getMy_shelfie().getShelf()[1][3].getType(), 1, 3));
            tmp2.add(new Tiles(p.getMy_shelfie().getShelf()[0][4].getType(), 0, 4));

            //check blu
            tmp3.add(new Tiles(p.getMy_shelfie().getShelf()[0][0].getType(), 0, 0));
            tmp3.add(new Tiles(p.getMy_shelfie().getShelf()[1][1].getType(), 1, 1));
            tmp3.add(new Tiles(p.getMy_shelfie().getShelf()[2][2].getType(), 2, 2));
            tmp3.add(new Tiles(p.getMy_shelfie().getShelf()[3][3].getType(), 3, 3));
            tmp3.add(new Tiles(p.getMy_shelfie().getShelf()[4][4].getType(), 4, 4));

        }

        public boolean check(Player p){

            setTmp(p);

            return checkDiagonal(tmp) || checkDiagonal(tmp1) || checkDiagonal(tmp2) || checkDiagonal(tmp3);
        }

        private boolean checkDiagonal(Vector <Tiles> tmp) {
            int type = tmp.get(0).getType();

            for (Tiles tiles : tmp) {
                if (tiles.getType() == Tiles.NOT_VALID) return false;
                if (tiles.getType() != type) {
                    return false;
                }
            }
            return true;
        }
}

