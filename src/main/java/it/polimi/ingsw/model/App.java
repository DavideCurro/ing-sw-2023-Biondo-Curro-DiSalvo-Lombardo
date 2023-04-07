package it.polimi.ingsw.model;


import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.player.Player;

import java.util.Vector;

/**
 * Hello world!
 *
 */
public class App {
    static final int col = 9;
    static final int row = 9;
    public static void main( String[] args ) {
        Vector<Player> players = new Vector<>();
        players.add(new Player(true,"Claudio",true));
        players.add(new Player(false,"Sibb",false));
        players.add(new Player(false,"Mati ",false));
        Playground p = new Playground();
        try {
            p = new Playground(players.size());
            p.countSelected();
        }catch (PlaygroundException e){
            System.out.println(e.getMessage());
        }
        p.printOutPlayground();
        System.out.println("\n\n\n\n");
        int i = 0;
        while(i < players.size()){
            if(players.get(i).getTurn()){
                System.out.println(players.get(i).getNickname()+ " Sta giocando");
                players.get(i).pickUp(p);
                players.get(i).getMy_shelfie().printOut();
                players.get(i).setTurn(false);
            }
            i++;
            p.printOutPlayground();
            System.out.println("\n\n\n\n");
        }
        System.out.println("CHECK");
        for(i = 0; i< players.size();i++){
            players.get(i).getMy_shelfie().printOut();
            System.out.println("\n\n\n\n");
        }


    }
}



