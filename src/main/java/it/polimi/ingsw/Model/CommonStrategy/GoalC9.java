package it.polimi.ingsw.Model.CommonStrategy;



import it.polimi.ingsw.Model.Player.Player;

import java.io.Serializable;

import static it.polimi.ingsw.Model.Playground.Tiles.*;

public class GoalC9 implements CommonObj, Serializable {
    private final int type = 9;

    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;
    public int getType(){
        return type;
    }
    /** Goal 9 = Eight tiles of the same type.
     * There’s no restriction about the position of these
     * tiles
     * @param p
     * @return
     * */
    public boolean check(Player p) {
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 6; i++) {
                int type = p.getMy_shelfie().getShelf()[i][j].getType();
                if(type == NOT_VALID)
                    continue;
                switch (type) {
                    case BLUE -> {
                        count1++;
                        if (count1 == 8) {
                            return true;
                        } break;
                    }
                    case CYAN -> {
                        count2++;
                        if (count2 == 8) {
                            return true;
                        } break;
                    }
                    case YELLOW -> {
                        count3++;
                        if (count3 == 8) {
                            return true;
                        }
                    }
                    case WHITE -> {
                        count4++;
                        if (count4 == 8) {
                            return true;
                        } break;
                    }
                    case GREEN -> {
                        count5++;
                        if (count5 == 8) {
                            return true;
                        } break;
                    }
                    case PINK -> {
                        count6++;
                        if (count6 == 8) {
                            return true;
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }
}