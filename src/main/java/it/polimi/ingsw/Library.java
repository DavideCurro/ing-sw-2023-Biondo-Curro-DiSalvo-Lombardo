package it.polimi.ingsw;

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

    public Boolean posix(Library s1,Tiles t1,Tiles t2, Tiles t3, int column){
            for (int i =0;i<3;i++){
                s1.shelf[5-i][column].setType(t1.getType());
            }
        return true;
    }
}
