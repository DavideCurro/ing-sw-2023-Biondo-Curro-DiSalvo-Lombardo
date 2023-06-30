package it.polimi.ingsw.Model.CommonStrategy;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Playground.Tiles;

import java.io.Serializable;
import java.util.Vector;


public class GoalC12 implements CommonObj, Serializable {
    private final int type = 12;
    public int getType(){
        return type;
    }
    Vector<Tiles> diagBlu = new Vector<>();
    Vector<Tiles> diagMarr = new Vector<>();
    Vector<Tiles> diagBianca = new Vector<>();
    Vector<Tiles> diagVerde = new Vector<>();
    Vector<Tiles> diagArcobaleno = new Vector<>();
    Vector<Tiles> diagFucsia = new Vector<>();

    /**Five columns of increasing or decreasing
     *  height. Starting from the first column on
     *  the left or on the right, each next column
     *  must be made of exactly one more tile.
     *  Tiles can be of any type.
     * @param p
     * @return
     */

    private void setUp(Player p){

        diagBlu.add(new Tiles(p.getMy_shelfie().getShelf()[0][0].getType(), 0, 0));
        diagBlu.add(new Tiles(p.getMy_shelfie().getShelf()[1][1].getType(), 1, 1));
        diagBlu.add(new Tiles(p.getMy_shelfie().getShelf()[2][2].getType(), 2, 2));
        diagBlu.add(new Tiles(p.getMy_shelfie().getShelf()[3][3].getType(), 3, 3));
        diagBlu.add(new Tiles(p.getMy_shelfie().getShelf()[4][4].getType(), 4, 4));


        diagVerde.add(new Tiles(p.getMy_shelfie().getShelf()[0][4].getType(), 0, 4));
        diagVerde.add(new Tiles(p.getMy_shelfie().getShelf()[1][3].getType(), 1, 3));
        diagVerde.add(new Tiles(p.getMy_shelfie().getShelf()[2][2].getType(), 2, 2));
        diagVerde.add(new Tiles(p.getMy_shelfie().getShelf()[3][1].getType(), 3, 1));
        diagVerde.add(new Tiles(p.getMy_shelfie().getShelf()[4][0].getType(), 4, 0));


        diagFucsia.add(new Tiles(p.getMy_shelfie().getShelf()[1][4].getType(), 1, 4));
        diagFucsia.add(new Tiles(p.getMy_shelfie().getShelf()[2][3].getType(), 2, 3));
        diagFucsia.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
        diagFucsia.add(new Tiles(p.getMy_shelfie().getShelf()[4][1].getType(), 4, 1));
        diagFucsia.add(new Tiles(p.getMy_shelfie().getShelf()[5][0].getType(), 5, 0));

        diagMarr.add(new Tiles(p.getMy_shelfie().getShelf()[1][0].getType(), 1, 0));
        diagMarr.add(new Tiles(p.getMy_shelfie().getShelf()[2][1].getType(), 2, 1));
        diagMarr.add(new Tiles(p.getMy_shelfie().getShelf()[3][2].getType(), 3, 2));
        diagMarr.add(new Tiles(p.getMy_shelfie().getShelf()[4][3].getType(), 4, 3));
        diagMarr.add(new Tiles(p.getMy_shelfie().getShelf()[5][4].getType(), 5, 4));

        diagBianca.add(new Tiles(p.getMy_shelfie().getShelf()[0][1].getType(), 0, 1));
        diagBianca.add(new Tiles(p.getMy_shelfie().getShelf()[1][2].getType(), 1, 2));
        diagBianca.add(new Tiles(p.getMy_shelfie().getShelf()[2][3].getType(), 2, 3));
        diagBianca.add(new Tiles(p.getMy_shelfie().getShelf()[3][4].getType(), 3, 4));

        diagArcobaleno.add(new Tiles(p.getMy_shelfie().getShelf()[0][3].getType(), 0, 3));
        diagArcobaleno.add(new Tiles(p.getMy_shelfie().getShelf()[1][2].getType(), 1, 2));
        diagArcobaleno.add(new Tiles(p.getMy_shelfie().getShelf()[2][1].getType(), 2, 1));
        diagArcobaleno.add(new Tiles(p.getMy_shelfie().getShelf()[3][0].getType(), 3, 0));
    }

    public boolean check(Player p){
        setUp(p);
        return checkDiagonal(diagBlu, diagBianca) || checkDiagonal(diagMarr,diagBlu) || checkDiagonal(diagVerde,diagArcobaleno) || checkDiagonal(diagFucsia,diagVerde);
    }

    private boolean checkDiagonal(Vector<Tiles> diag1, Vector<Tiles> diag2){
        for(Tiles tile : diag1){
            if(!tile.checkSet())
                return false;
        }
        for(Tiles tile : diag2){
            if(tile.checkSet())
                return false;
        }
        return true;
    }

}

