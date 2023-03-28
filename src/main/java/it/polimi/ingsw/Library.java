package it.polimi.ingsw;

import java.util.Vector;

public class Library {
    private Tiles[][] shelf;


    public Library() {
        this.shelf = new Tiles[6][5];
        for(int i =0;i<6;i++){
            for(int j = 0;j<5;j++){
                this.shelf[i][j] = new Tiles(-1,i,j);
            }
        }
    }

    public Library(Tiles[][] shelf) {
        this.shelf = shelf;
    }
    public Tiles[][] getShelf() {
        return shelf;
    }

    public void setShelf(Tiles[][] shelf) {
        this.shelf = shelf;
    }

    public Library posix(Vector<Tiles> t, int column, int len, Library s){
            for (int i =0;i<len;i++){

                s.shelf[5-i][column].setType(t.get(i).getType());
                System.out.println(s.shelf[5-i][column].getType());
            }
            return s;
    }
    public  void printOut(Library s){
        for(int i = 0;i<5;i++){
            for(int j = 0;j<5;j++){
                System.out.print(s.shelf[i][j].getType()+"\t");
            }
            System.out.println("");
        }
    }
}
