package it.polimi.ingsw;
import java.io.*;
import java.util.Random;

public class Playground {
    private static int col = 7;
    private static int row = 7;
    private int num_tiles;
    private Tiles[][] ground;

    public Playground() {
        num_tiles = 0;
        ground = new Tiles[0][0];
    }
    public Playground(int num_tiles) {
        this.num_tiles = num_tiles;
        ground = new Tiles[0][0];
    }
    public Playground(int num_tiles, Tiles[][] ground) {
        this.num_tiles = num_tiles;

        this.ground = ground;
        int row = this.ground.length;
        int col = this.ground[0].length;
        if((row*col)!=num_tiles){
            System.out.println("ERROR");
            System.exit(-1);
        }
    }

    public int getNum_tiles() {
        return num_tiles;
    }

    public void setNum_tiles(int num_tiles) {
        this.num_tiles = num_tiles;
    }

    public Tiles[][] getGround() {
        return ground;
    }

    public void setGround(Tiles[][] ground) {
        this.ground = ground;
    }
    public void printOutPlayground(){
        for(int i = 0 ; i<7;i++){
            for (int j = 0;j<7; j++){
                System.out.print(this.ground[i][j].getType()+"\t");
            }
            System.out.println("");
        }
    }
    public void fillUP(){
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
                }else if (j==2){
                    if(i > 5 ){
                        g [i][j] = new Tiles(-1, i, j);
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
        this.setGround(g);
    }
}
