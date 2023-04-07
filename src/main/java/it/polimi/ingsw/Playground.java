package it.polimi.ingsw;
import java.io.*;
import java.util.Random;
import java.lang.*;

public class Playground {
    private  static  int [][] mask2Player = new int[][]{{-1,-1,-1,-1,-1,-1,-1,-1,-1},
            {-1,-1, -1, 0, 0, -1,-1,-1,-1},
            {-1,-1, -1, 0, 0, 0, -1, -1,-1},
            {-1,-1, 0, 0, 0, 0, 0, 0,-1},
            {-1,0, 0, 0, 0, 0, 0, 0,-1},
            {-1,0, 0, 0, 0, 0, 0, -1,-1},
            {-1, -1, 0, 0, 0, -1, -1,-1},
            {-1,-1, -1, -1, 0, 0, -1, -1,-1},
            {-1,-1, -1, -1, -1,-1,-1, -1,-1}
    };
    private  static  int [][] mask3Player = new int[][]{{-1, -1, -1, 0, -1, -1,-1,-1,-1},
            {-1,-1, -1, 0, 0, -1, -1,-1,-1},
            {-1, -1, 0, 0, 0, 0, 0, -1,-1},
            {-1,-1, 0, 0, 0, 0, 0, 0, 0},
            {-1, 0, 0, 0, 0, 0, 0, 0, -1},
            {0, 0, 0, 0, 0, 0, 0, -1, -1},
            {-1, -1, 0, 0, 0, 0, 0, -1, -1},
            {-1, -1, -1, -1, 0, 0, -1, -1, -1},
            {-1, -1, -1, -1, -1, 0, -1, -1, -1}
    };
    private  static  int [][] mask4Player = new int[][]{{-1, -1, -1, 0, 0, -1,-1,-1,-1},
            {-1,-1, -1, 0, 0, 0, -1,-1,-1},
            {-1, -1, 0, 0, 0, 0, 0, -1,-1},
            {-1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, -1},
            {-1, -1, 0, 0, 0, 0, 0, -1, -1},
            {-1, -1, -1, 0, 0, 0, -1, -1, -1},
            {-1, -1, -1, -1, 0, 0, -1, -1, -1}
    };
    private static int col = 9;
    private static int row = 9;
    private int num_tiles;
    private int num_players;
    private Tiles[][] ground;

    public Playground() {
        num_tiles = 0;
        ground = new Tiles[0][0];
    }
    public Playground(int num_tiles, int num_players) {
        this.num_tiles = num_tiles;
        this.num_players = num_players;
        switch(num_players){
            case 2:{
                maskSetup(mask2Player);
                break;
            }
            case 3:{
                maskSetup(mask3Player);
                break;
            }
            case 4:{
                maskSetup(mask4Player);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + num_players);
        }
    }
    public Playground(int num_tiles, Tiles[][] ground, int num_players) {
        this.num_tiles = num_tiles;
        this.num_players = num_players;
        this.ground = ground;
        int row = this.ground.length;
        int col = this.ground[0].length;
        if((row*col)!=num_tiles){
            System.out.println("ERROR");
            System.exit(-1);
        }
    }
    private void maskSetup(int[][] maskPlayer) {
        Tiles[][] g = new Tiles[9][9];
        Random r = new Random();
        for (int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                if (maskPlayer[i][j]!=-1) {
                    g[i][j] = new Tiles(r.nextInt(5), i, j);
                }else{
                    g[i][j] = new Tiles(-1, i, j);
                }
            }
        }
        setGround(g);
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
        switch (num_players) {
            case 2 -> {
                maskSetup(mask2Player);
            }
            case 3 -> {
                maskSetup(mask3Player);
            }
            case 4 -> {
                maskSetup(mask4Player);
            }
            default -> throw new IllegalStateException("Unexpected value: " + num_players);
        }
    }

    public boolean checkAdjacency(int x1, int y1){ // one tile picked
        boolean free = false;
        if(((this.ground[x1][y1-1]).getType()==-1) || ((this.ground[x1][y1+1]).getType()==-1) || ((this.ground[x1+1][y1]).getType()==-1) || (this.ground[x1-1][y1]).getType()==-1)
            free = true;
        return free;
    }

    public boolean checkAdjacency(int x1, int y1, int x2, int y2){  //two tiles picked
        boolean free = false;
        boolean near = false;

        if(((this.ground[x1][y1-1]).getType()==-1) || ((this.ground[x1][y1+1]).getType()==-1) || ((this.ground[x1+1][y1]).getType()==-1) || (this.ground[x1-1][y1]).getType()==-1)
            free = true;
        else
            return false;

        if ((((this.ground[x2][y2-1]).getType()==-1) || ((this.ground[x2][y2+1]).getType()==-1) || ((this.ground[x2+1][y2]).getType()==-1) || (this.ground[x2-1][y2]).getType()==-1) && (free == true))
            free = true;
        else
            return false;

        if (x1==x2)
            if ((y2 == y1+1) || (y2 == y1-1))
                near = true;
        else if(y1==y2)
            if ((x2 == x1+1) || (x2 == x1-1))
                near = true;

        if (near == true)
            return true;

        return false;

    }

    public boolean checkAdjacency(int x1, int y1, int x2, int y2, int x3, int y3){ //three tiles picked
        boolean free = false;
        boolean near = false;

        if(((this.ground[x1][y1-1]).getType()==-1) || ((this.ground[x1][y1+1]).getType()==-1) || ((this.ground[x1+1][y1]).getType()==-1) || (this.ground[x1-1][y1]).getType()==-1)
            free = true;
        else
            return false;

        if ((((this.ground[x2][y2-1]).getType()==-1) || ((this.ground[x2][y2+1]).getType()==-1) || ((this.ground[x2+1][y2]).getType()==-1) || (this.ground[x2-1][y2]).getType()==-1) && (free == true))
            free = true;
        else
            return false;

        if( (x1==x2)&&(x2==x3) )
            if (((y1 == y2-1) && (y1 == y3-2)) || ((y1 == y2-1) && (y1==y3+2)))
                near = true;
        else if( (y1==y2)&&(y2==y3) )
            if (((x1 == x2-1) && (x1 == x3-2)) || ((x1 == x2-1) && (x1==x3+2)))
                near = true;

        if (near == true)
            return true;

        return false;

    }
}
