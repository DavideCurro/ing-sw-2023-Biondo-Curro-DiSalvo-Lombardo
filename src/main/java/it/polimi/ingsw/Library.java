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

    public void posix(Tiles[]t, int column, int len){
            for (int i =0;i<len;i++){
                this.shelf[5-i][column].setType(t[i].getType());
            }
    }
}
