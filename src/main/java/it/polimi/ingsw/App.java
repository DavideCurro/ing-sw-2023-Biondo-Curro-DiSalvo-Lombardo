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
        p.fillUP();
        System.out.println("\n\n\n");
        Library l = new Library();
        Tiles [] provaT = new Tiles[2];
        provaT[0] = p.getGround()[4][5];
        provaT[1] = p.getGround()[6][3];
        //provaT[2] = g[2][2];
        l.posix(provaT,4,provaT.length);
        Tiles [][] prova = l.getShelf();
        for(int i = 0; i<6;i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(prova[i][j].getType() + "\t");
            }
            System.out.print("\n");
        }
        System.out.println("\n\n\n\n\n");
        Player player = new Player();
        player.pickUp();

        System.out.println( "Hello World!" );
    }
}



