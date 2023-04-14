package it.polimi.ingsw.model.player;
import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.exception.CoordinateStateException;
import it.polimi.ingsw.model.exception.LibraryException;
import it.polimi.ingsw.model.personalStrategy.ObjectivePersonalEXEC;
import it.polimi.ingsw.model.personalStrategy.PersonalObj;

import java.util.*;
/*
 * This class is used for manage all the aspect of the player.
 *
 * */
public class Player {
    /*
     *  Determine if the player is the first.
     *   TRUE => First player
     *   FALSE => not First Player
     * */
    private Boolean is_first;
    /*
     *  Represent the library of the player, which is used to calculate points and pickUp tiles
     * */
    private Library my_shelfie;
    private String nickname;
    private Boolean turn;
    private final Scanner scanner;
    private Vector<Tiles> coordinates;
    private int points;

    private ObjectivePersonalEXEC personalObj;

    /**
     *
     * It is a constructor.
     *
     */
    public Player() {


        this.is_first = false;
        this.nickname = "";
        this.my_shelfie = new Library();
        this.turn   = false;
        this.scanner = new Scanner(System.in);
        this.coordinates= new Vector<>();
        this.points = 0;


    }
    public Player(Library library){
        this.my_shelfie = library;
        this.is_first = false;
        this.nickname = "";
        this.turn   = false;
        this.scanner = new Scanner(System.in);
        this.coordinates= new Vector<>();
        this.points = 0;
    }
    /**
     *
     * It is a constructor.
     *
     * @param nickname  the nickname
     * @param turn  the turn
     */
    public Player(PersonalObj personalObj, String nickname, Boolean turn) {
        this.personalObj = new ObjectivePersonalEXEC(personalObj);
        this.is_first = false;
        this.nickname = nickname;
        this.turn = turn;
        this.my_shelfie = new Library();
        this.scanner = new Scanner(System.in);
        this.coordinates= new Vector<>();
        this.points = 0;
    }
    public Player(PersonalObj personalObj, String nickname) {
        this.personalObj = new ObjectivePersonalEXEC(personalObj);
        this.is_first = false;
        this.nickname = nickname;
        this.turn = false;
        this.my_shelfie = new Library();
        this.scanner = new Scanner(System.in);
        this.coordinates= new Vector<>();
        this.points = 0;
    }

    /**
     * It is a constructor.
     *
     * @param is_first    the is_first
     * @param my_shelfie  the my_shelfie
     * @param nickname    the nickname
     * @param turn        the turn
     * @param personalObj define the personal OBJ
     */
    public Player(Boolean is_first, Library my_shelfie, String nickname, Boolean turn, ObjectivePersonalEXEC personalObj) {


        this.is_first = is_first;
        this.my_shelfie = my_shelfie;
        this.nickname = nickname;
        this.personalObj = personalObj;
        this.scanner = new Scanner(System.in);
        this.turn = turn;
        this.coordinates = new Vector<>();
        this.points = 0;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    /**
     *
     * Gets the is_first
     *
     * @return the is_first
     */
    public Boolean getIs_first() {


        return is_first;
    }


    /**
     *
     * Sets the is_first
     *
     * @param is_first  the is_first
     */
    public void setIs_first(Boolean is_first) {


        this.is_first = is_first;
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


    /**
     *
     * Gets the turn
     *
     * @return the turn
     */
    public Boolean getTurn() {


        return turn;
    }


    /**
     *
     * Sets the turn
     *
     * @param turn  the turn
     */
    public void setTurn(Boolean turn) {


        this.turn = turn;
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


    public Boolean pickUp(Playground p) throws RuntimeException {
        System.out.println(this.nickname);
        if(this.my_shelfie.isFull()) throw new RuntimeException("FULL Shelfie");
        // Initialize variables
        this.coordinates = new Vector<>();
        int tmpX,tmpY;
        System.out.println("Choose Column");
        int column = validateInput(true);
        int max = calculateMaxTiles(column);
        // Allow the player to pick up to 3 tiles
        for(int i = 0; i< max ;i++){
            // Prompt the player for coordinates
            System.out.println("Write your chosen coordinate\n");
            System.out.print("X:");
            tmpX = validateInput(false);
            System.out.println();
            System.out.print("Y:");
            tmpY = validateInput(false);
            // Store the picked tile in the picked Vector
            this.coordinates.add(new Tiles(p.getGround()[tmpX][tmpY].getType(),tmpX,tmpY));
            // Display information about the picked tile
            System.out.println(this.coordinates.get(i).toString() );
        }
// Call the posix method of the player's shelfie to place the picked up tiles on their shelf
        try {
            return this.my_shelfie.posix(this.coordinates, column, this.coordinates.size(), p, max);
        }catch (LibraryException e){
            throw new RuntimeException(e);
        }
    }





    //TODO: MOVE THIS CLIENT SIDE, THE INPUT CHECK SHOULD BE IN CLIENT SIDE, THE SERVER NEEDS TO CHECK IF THE VALID INPUT IS POSSIBLE FOR THE GAME
    /**
     *
     * Check coordinate valid
     *
     * @param x  the x.
     * @throws   CoordinateStateException, throws exception for wrong coordinate choose
     */
    private void checkCoordinateValid(int x) throws CoordinateStateException{

        if(x<0 || x>9) throw new CoordinateStateException("Impossible coordinate state");
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
    private int calculateMaxTiles(int column){

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
        System.out.println("Choose amount of Tiles");
        int amount = scanner.nextInt();
        return Math.min(amount, max);
    }
    /**
     * Validate the coordinate input
     * @return tmp
     */
    private int validateInput(boolean column){

        int tmp ;
        try {
            tmp = scanner.nextInt() - 1;
            if(column)
                checkColValid(tmp);
            else
                checkCoordinateValid(tmp);
        }catch (java.util.InputMismatchException e){
            scanner.next();
            throw new RuntimeException(e);
        } catch (CoordinateStateException e){
            throw new RuntimeException(e);
        }
        return tmp;
    }



    public void checkPersonalOBJ(){
        this.points +=this.personalObj.execCheck(this);
    }
}
