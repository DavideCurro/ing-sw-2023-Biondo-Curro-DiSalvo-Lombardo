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

        Random r = new Random();
        Tiles[][] g = new Tiles[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == 0) {
                    if (i < 3 || i > 4) {
                        g[i][j] = new Tiles(-1, i, j);
                    } else {
                        g[i][j] = new Tiles(r.nextInt(5), i, j);
                    }
                } else if (j == 1) {
                    if (i < 2 || i > 4) {
                        g[i][j] = new Tiles(-1, i, j);
                    } else {
                        g[i][j] = new Tiles(r.nextInt(5), i, j);
                    }
                } else if (j == 4) {
                    if (i < 1) {
                        g[i][j] = new Tiles(-1, i, j);
                    } else {
                        g[i][j] = new Tiles(r.nextInt(5), i, j);
                    }
                } else if (j == 5) {
                    if (i < 2 || i > 4) {
                        g[i][j] = new Tiles(-1, i, j);
                    } else {
                        g[i][j] = new Tiles(r.nextInt(5), i, j);
                    }
                } else if (j == 6) {
                    if (i < 2 || i > 3) {
                        g[i][j] = new Tiles(-1, i, j);
                    } else {
                        g[i][j] = new Tiles(r.nextInt(5), i, j);
                    }
                } else {
                    g[i][j] = new Tiles(r.nextInt(5), i, j);
                }
            }
        }

        Playground p = new Playground(row*col,g);
        for(int i = 0; i<row;i++){
            for(int j = 0;j<col;j++){
                System.out.print(g[i][j].getType()+"\t");
            }
            System.out.print("\n");
        }
        System.out.println("\n\n\n");
        Library l = new Library();
        l.posix(l,g[4][5],g[4][5],g[4][5],4);
        Tiles [][] prova = l.getShelf();
        for(int i = 0; i<6;i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(prova[i][j].getType() + "\t");
            }
            System.out.print("\n");
        }

        System.out.println( "Hello World!" );
    }
}



