package it.polimi.ingsw;

//import java.util.Arrays;
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

    public Boolean posix(Vector<Tiles> t, int column, int len,Playground p){
            int[] count = this.available();
            int j = 0;
            Boolean tmp = this.adjacence(t,p);
            System.out.println(tmp);
            System.out.println(t.size());
            for (int i = count[column];i<len+count[column];i++){
                if(tmp){
                    this.shelf[5-i][column].setType(t.get(j).getType());
                    System.out.println(this.shelf[5-i][column].getType());
                    p.getGround()[t.get(j).getX()][t.get(j).getY()].setType(-1);
                    j++;
                }else{
                    System.out.println("NOT POSSIBLE");
                }

            }
            return tmp;
    }
    private Boolean adjacence(Vector<Tiles> t, Playground p){
        boolean pick = false;
        if(t.size()==1){
            return p.checkAdjacency(t.get(0).getX(), t.get(0).getY());
        }
        for(int i = 1; i< t.size(); i++){
            pick = p.checkAdjacency(t.get(i - 1).getX(), t.get(i - 1).getY(), t.get(i).getX(), t.get(i).getY());
        }
        return pick;
    }
    public  void printOut(Library s){
        for(int i = 0;i<6;i++){
            for(int j = 0;j<5;j++){
                System.out.print(s.shelf[i][j].getType()+"\t");
            }
            System.out.println("");
        }
    }
    private int[] available(){ //how many free spots in the library
        int[] count = new int[5];

        for(int j = 0; j < 5; j++){
            for (int i = 0; i<6; i++){
                if ( (this.shelf[i][j]).getType() == -1)
                    count[j] = 5-i;

            }
        }

        //System.out.println("The max number of tiles you can enter in column 'j' is: ", count[j]);
        return count;
    }
}
