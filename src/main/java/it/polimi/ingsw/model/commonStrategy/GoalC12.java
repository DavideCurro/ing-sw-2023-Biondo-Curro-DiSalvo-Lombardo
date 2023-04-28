package it.polimi.ingsw.model.commonStrategy;

import it.polimi.ingsw.model.player.Player;

import java.io.Serializable;

public class GoalC12 implements CommonObj, Serializable {

    /**Five columns of increasing or decreasing
     *  height. Starting from the first column on
     *  the left or on the right, each next column
     *  must be made of exactly one more tile.
     *  Tiles can be of any type.
     * @param p
     * @return
     */

    public boolean check(Player p) {
        if (leftOrRight(0, p)) {
            return scalaLeftToRight(0, p);
        } else if (leftOrRight(4, p)) {
            return scalaRightToLeft(0, p);
        } else {
            return false;
        }


    }

    private boolean leftOrRight(int j, Player p) {
        int count = 0;
        boolean ok = false;
        for (int i = 0; i < 6; i++) {
            if (p.getMy_shelfie().getShelf()[i][j].getType() != -1) {
                count++;
            }
        }
        if (count == 5 || count == 6) {
            ok = true;
        }
        return ok;
    }

    private boolean scalaLeftToRight(int y, Player p) {
        int offset = 0;
        if (p.getMy_shelfie().getShelf()[0][0].getType() == -1) {
            offset = 1;
        }
        for (int j = 0; j < 5; j++) {
            for (int i = offset; i < 6; i++) {
                if (p.getMy_shelfie().getShelf()[i][j+1].getType() != -1) return false;
                while(i == j) {
                    return true;
                }
            }
        }
        return false;
    }
       /* for (int j = y + 1; j < 5; j++) {
            for (int i = offset + j; i < 5 - j; i++) {

                if (p.getMy_shelfie().getShelf()[i][j].getType() == -1) {
                    return false;
                }
            }
        }
        return true;
    }*/

    private boolean scalaRightToLeft(int y, Player p) {
        int offset = 1;
        if (p.getMy_shelfie().getShelf()[0][4].getType() == -1) {
            offset = 0;
        }
        for (int j = 3; j > 0; j--) {
            for (int i = offset; i < 6; i++) {
                if (p.getMy_shelfie().getShelf()[i+1][j].getType() != -1) return false;
                while (i == j) {
                    return true;
                }

                }
            }
        return false;
        }

    }


