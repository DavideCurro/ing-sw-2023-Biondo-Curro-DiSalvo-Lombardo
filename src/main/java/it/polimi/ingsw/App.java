package it.polimi.ingsw;
import java.io.*;
import java.util.Random;



/**
 * Hello world!
 *
 */
public class App {
    static final int col = 7;
    static final int row = 7;
    public static void main( String[] args ) {

        Playground p = new Playground(col*row);
        p.fillUP();
        p.printOutPlayground();
        System.out.println("\n\n\n\n\n");
        Player player = new Player();
        player.pickUp(p);
        player.getMy_shelfie().printOut(player.getMy_shelfie());

        System.out.println( "Hello World!" );
    }
}



