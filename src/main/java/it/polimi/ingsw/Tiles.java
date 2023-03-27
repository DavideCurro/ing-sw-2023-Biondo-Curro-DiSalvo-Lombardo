package it.polimi.ingsw;

public class Tiles {

    private int type;
    private int x;
    private int y;


    public Tiles(){
        type=-1;
        x=0;
        y=0;
    }
    public Tiles(int type){
        this.type = type;
        this.x = 0;
        this.y = 0;
    }
    public Tiles(int type,int x,int y){
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
