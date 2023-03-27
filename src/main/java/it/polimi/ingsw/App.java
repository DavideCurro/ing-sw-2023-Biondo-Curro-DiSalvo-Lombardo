package it.polimi.ingsw;
import java.io.*;
import java.util.Random;



/**
 * Hello world!
 *
 */
public class App {
    static final int col = 9;
    static final int row = 9;
    public static void main( String[] args ){

        Random r =  new Random();
        Tiles [][] g = new Tiles[row][col];
        for(int i = 0;i<row;i++){
            for(int j =0;j<col;j++){
                g[i][j] = new Tiles(r.nextInt(6));
            }
        }
        Playground p = new Playground(row*col,g);
        for(int i = 0; i<row;i++){
            for(int j = 0;j<col;j++){
                System.out.print(g[i][j].getType()+" ");
            }
            System.out.print("\n");
        }



        System.out.println( "Hello World!" );
    }
}
