package it.polimi.ingsw.commonStrategy;

import it.polimi.ingsw.Player;
import it.polimi.ingsw.Tiles;

public class GoalC1 {
    private int points;
    private int count = 0;


    //punteggio massimo raggiungibile con obbiettivo comune


    public void check(Player p) {
        for (int j = 0; j < 5; j++) {
            int i = 1;
            while (((p.getMy_shelfie()[i][j]).getType() == (p.getMy_shelfie()[ i - 1][j]).getType()) && (i < 6)) {
                i++;
            }
            if (i == 5)
                count++;
            if (count == 2)
                break;
            //metodo conta punteggi
            //decremento punteggio massimo raggiungibile
            //passato e memorizzato nel match
        }


    }

    public void setPoints() {
        if (count == 2) {
            points =
        }
    }
}
