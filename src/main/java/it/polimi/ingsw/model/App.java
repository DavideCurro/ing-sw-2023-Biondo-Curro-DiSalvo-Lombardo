package it.polimi.ingsw.model;


import it.polimi.ingsw.model.exception.PlaygroundException;
import it.polimi.ingsw.model.player.Player;

/**
 * Hello world!
 *
 */
public class App {
    static final int col = 9;
    static final int row = 9;
    public static void main( String[] args ) {
        Playground p = new Playground();
        try {
            p = new Playground(col * row, 2);
            p.fillUP();
        }catch (PlaygroundException e){
            System.out.println(e.getMessage());
        }
        p.printOutPlayground();
        System.out.println("\n\n\n\n\n");
        Player player = new Player();
        if(!player.pickUp(p)){
            System.out.println("SCEMO");
        }
        player.getMy_shelfie().printOut(player.getMy_shelfie());
        System.out.println("\n\n");
        p.printOutPlayground();
        player.pickUp(p);
        player.getMy_shelfie().printOut(player.getMy_shelfie());
        System.out.println( "Hello World!" );
    }
}



