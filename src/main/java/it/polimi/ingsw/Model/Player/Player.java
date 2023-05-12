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
    private int publicPoints;
    private int privatePoints;
    private int points;
    private boolean hasMadeCommonOBJ1;
    private boolean hasMadeCommonOBJ2;

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

        this.coordinates= new Vector<>();
        this.points = 0;


    }
    public Player(String nickname) {


        this.is_second = false;
        this.nickname = nickname;
        this.my_shelfie = new Library();

        this.coordinates= new Vector<>();
        this.points = 0;


    }
    public Player(Library library){
        this.my_shelfie = library;
        this.is_second = false;
        this.nickname = "";


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

    }

    public boolean isHasMadeCommonOBJ1() {
        return hasMadeCommonOBJ1;
    }
    public boolean isHasMadeCommonOBJ2() {
        return hasMadeCommonOBJ2;
    }

    public void setHasMadeCommonOBJ1(boolean hasMadeCommonOBJ1) {
        this.hasMadeCommonOBJ1 = hasMadeCommonOBJ1;
    }
    public void setHasMadeCommonOBJ2(boolean hasMadeCommonOBJ2) {
        this.hasMadeCommonOBJ2 = hasMadeCommonOBJ2;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = privatePoints + publicPoints + points;
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

    public int getPublicPoints() {
        return publicPoints;
    }

    public void setPublicPoints(int publicPoints) {
        this.publicPoints = publicPoints;
    }

    public int getPrivatePoints() {
        return privatePoints;
    }

    public void setPrivatePoints(int privatePoints) {
        this.privatePoints = privatePoints;
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
    public void addPoint(int point){
        this.points += point;
    }
    public Vector<Integer> calculateADJ(){
        Vector<Vector<Tiles>> vectorOfTiles = new Vector<>();
        for (int i = 0;i<6;i++){
            vectorOfTiles.add(new Vector<>());
        }
        for(int j = 0; j<5; j++){
            for(int i = 0; i<6; i++){
                if(my_shelfie.getShelf()[i][j].getType() == -1) continue;
               vectorOfTiles.get(my_shelfie.getShelf()[i][j].getType()).add(my_shelfie.getShelf()[i][j]);
            }
        }

        Vector<Integer> counter = new Vector<>();
        for(Vector<Tiles> vector : vectorOfTiles){
            Vector<Vector<Tiles>> adj = new Vector<>();
            for (int i = 0;i<vector.size();i++) {
                adj.add(new Vector<>());
                adj.get(i).add(vector.get(i));
                for(int j = i; j< vector.size();j ++){
                    if(j == i)  continue;
                    if(vector.get(j).checkSides(vector, j, vector.get(i).getX(), vector.get(i).getY())) {
                        if(!adj.get(i).contains(vector.get(j))) {
                            adj.get(i).add(vector.get(j));
                        }
                    }
                }
            }
            counter.add(mergeADJ(adj).size());
        }
        return counter;
    }
    private Vector<Tiles> mergeADJ(Vector<Vector<Tiles>> adj){
        Vector<Tiles> tmp = getFirstElementAvailableToMerge(adj);
        adj.removeIf(vector -> vector.size() == 0);
        Vector<Integer> toMerge = getToMergeIndex(tmp);
        Vector<Tiles> Adjacent = new Vector<>();
        for(Integer i : toMerge){
            for (Vector<Tiles> tiles : adj) {
                if (tmp.get(i).equals(tiles.firstElement())) {
                    for(int j=0;j<tiles.size();j++) {
                        if (!Adjacent.contains(tiles.get(j)))
                            Adjacent.add(tiles.get(j));
                    }
                }
            }

        }
        return Adjacent;
    }

    private Vector<Integer> getToMergeIndex(Vector<Tiles> tmp ){
        Vector<Integer> toMerge = new Vector<>();
        for (int i = 0;i<tmp.size();i++) {
            for(int j = i; j< tmp.size();j ++){
                if(j == i)  continue;
                if(tmp.get(i).checkSides(tmp, j, tmp.get(i).getX(), tmp.get(i).getY())){
                    if(!toMerge.contains(i)){
                        toMerge.add(i);
                    }
                    if(!toMerge.contains(j)){
                        toMerge.add(j);
                    }
                }
            }
        }
        return toMerge;
    }
    private Vector<Tiles> getFirstElementAvailableToMerge(Vector<Vector<Tiles>> adj){
        Vector<Tiles> tmp = new Vector<>();
        for (Vector<Tiles> tiles : adj) {
            if (tiles.size() > 1) {
                tmp.add(tiles.firstElement());
            }else{
                tiles.clear();
            }
        }
        return tmp;
    }
}
