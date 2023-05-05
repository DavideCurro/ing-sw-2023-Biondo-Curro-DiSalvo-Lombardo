package it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.Exception.CoordinateStateException;
import it.polimi.ingsw.Model.Exception.LibraryException;
import it.polimi.ingsw.Model.PersonalStrategy.ObjectivePersonalEXEC;
import it.polimi.ingsw.Model.PersonalStrategy.PersonalObj;

import java.io.Serializable;
import java.util.*;
/*
 * This class is used for manage all the aspect of the player.
 *
 * */
public class Player implements Serializable {
    /*
     *  Determine if the player is the first.
     *   TRUE => Second player
     *   FALSE => not Second Player
     * */
    private Boolean is_second;
    /*
     *  Represent the library of the player, which is used to calculate points and pickUp tiles
     * */
    private Library my_shelfie;
    private String nickname;
    private Vector<Tiles> coordinates;
    private int points;
    private boolean hasMadeCommonOBJ;

    private ObjectivePersonalEXEC personalObj;

    /**
     *
     * It is a constructor.
     *
     */
    public Player() {


        this.is_second = false;
        this.nickname = "";
        this.my_shelfie = new Library();
        this.hasMadeCommonOBJ= false;
        this.coordinates= new Vector<>();
        this.points = 0;


    }
    public Player(String nickname) {


        this.is_second = false;
        this.nickname = nickname;
        this.my_shelfie = new Library();
        this.hasMadeCommonOBJ= false;
        this.coordinates= new Vector<>();
        this.points = 0;


    }
    public Player(Library library){
        this.my_shelfie = library;
        this.is_second = false;
        this.nickname = "";
        this.hasMadeCommonOBJ= false;

        this.coordinates= new Vector<>();
        this.points = 0;
    }
    /**
     *
     * It is a constructor.
     *
     * @param nickname  the nickname
     * @param is_second  is second
     */
    public Player(PersonalObj personalObj, String nickname, Boolean is_second) {
        this.personalObj = new ObjectivePersonalEXEC(personalObj);
        this.is_second = is_second;
        this.nickname = nickname;
        this.my_shelfie = new Library();
        this.hasMadeCommonOBJ= false;
        this.coordinates= new Vector<>();
        this.points = 0;
    }
    public Player(PersonalObj personalObj, String nickname) {
        this.personalObj = new ObjectivePersonalEXEC(personalObj);
        this.is_second = false;
        this.nickname = nickname;
        this.my_shelfie = new Library();
        this.coordinates= new Vector<>();
        this.points = 0;
        this.hasMadeCommonOBJ= false;
    }

    public boolean isHasMadeCommonOBJ() {
        return hasMadeCommonOBJ;
    }

    public void setHasMadeCommonOBJ(boolean hasMadeCommonOBJ) {
        this.hasMadeCommonOBJ = hasMadeCommonOBJ;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     *
     * Gets the my_shelfie
     *
     * @return the my_shelfie
     */
    public Library getMy_shelfie() {


        return my_shelfie;
    }


    /**
     *
     * Sets the my_shelfie
     *
     * @param my_shelfie  the my_shelfie
     */
    public void setMy_shelfie(Library my_shelfie) {


        this.my_shelfie = my_shelfie;
    }

    /**
     *
     * Gets the nickname
     *
     * @return the nickname
     */
    public String getNickname() {


        return nickname;
    }


    /**
     *
     * Sets the nickname
     *
     * @param nickname  the nickname
     */
    public void setNickname(String nickname) {


        this.nickname = nickname;
    }

    public Boolean getIs_second() {
        return is_second;
    }

    public void setIs_second(Boolean is_second) {
        this.is_second = is_second;
    }

    public ObjectivePersonalEXEC getPersonalObj(){
        return personalObj;
    }

    /**

     Allows the player to pick up tiles from a given playground, up to a maximum of 3 tiles.
     Prompts the player for the column and coordinates of each tile they want to pick up, and stores information about each picked tile in a Coordinate object.
     Displays information about each picked tile, and calls the posix method of the player's shelfie to place the picked up tiles on their shelf.
     @param p The playground from which the player will pick up tiles.
     @return True if the picked up tiles are successfully placed on the player's shelf, false otherwise.
     */


    public Boolean pickUp(Playground p, int column, Vector<Tiles> coordinates) throws RuntimeException, CoordinateStateException {
        System.out.println(this.nickname);
        if(this.my_shelfie.isFull()) throw new RuntimeException("FULL Shelfie");
        // Initialize variables
        this.coordinates = coordinates;
        checkColValid(column);
        if(coordinates.size() > 3) // Allow the player to pick up to 3 tiles
            return false;
        int max = calculateMaxTiles(column,coordinates.size());
        System.out.println("Number of Tiles PICKED : "+max);

        for(Tiles tile:coordinates){
            if(!(checkCoordinateValid(tile.getX())||checkCoordinateValid(tile.getY()))) return false;
            tile.setType(p.getGround()[tile.getX()][tile.getY()].getType());
            System.out.println(tile); // Display information about the picked tile
        }
        return this.my_shelfie.posix(this.coordinates, column, p);// Call the posix method of the player's shelfie to place the picked up tiles on their shelf
    }


    /**
     *
     * Check coordinate valid
     *
     * @param x  the x.
     * @throws   CoordinateStateException, throws exception for wrong coordinate choose
     */
    private boolean checkCoordinateValid(int x) throws CoordinateStateException{

        if(x<0 || x>9) throw new CoordinateStateException("Impossible coordinate state");
        return true;
    }


    /**
     *
     * Check column valid
     *
     * @param col  the col.
     * @throws  CoordinateStateException, throws an exception for wrong column choose
     */
    private void checkColValid(int col)throws CoordinateStateException{

        if(col <0 || col > 5) throw new CoordinateStateException("Impossible Column");
    }

    /**
     *
     * Calculate max tiles for the chosen column
     *
     * @param column  the column.
     * @return int
     */
    public int calculateMaxTiles(int column, int len){

        int max;
        try{
            max = this.my_shelfie.available(column);
        }catch (LibraryException e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        if(max < 3){
            max = 3;
        }else{
            max =  6 - max;
        }
        return Math.min(len, max);
    }

    /**
     * check for personal goal
     * @return true if is done, false otherwise
     */

    public int checkPersonalOBJ(){

        return this.personalObj.execCheck(this);
    }
}
