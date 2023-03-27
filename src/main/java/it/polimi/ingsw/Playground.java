package it.polimi.ingsw;
import java.io.*;

public class Playground {
    private int num_tiles;
    private Tiles[][] ground;

    public Playground() {
        num_tiles = 0;
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
}
