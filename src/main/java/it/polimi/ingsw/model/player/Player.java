package it.polimi.ingsw.model.player;
import it.polimi.ingsw.model.Coordinate;
import it.polimi.ingsw.model.Playground;
import it.polimi.ingsw.model.exception.CoordinateStateException;

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
    private Coordinate coordinates;


    /**
     *
     * It is a constructor.
     *
     */
    public Player() {

        this.is_first = false;
        this.my_shelfie = new Library();
        this.turn   = false;
        this.scanner = new Scanner(System.in);
        this.coordinates= new Coordinate();

    }

    /**
     *
     * It is a constructor.
     *
     * @param is_first  the is_first
     * @param my_shelfie  the my_shelfie
     * @param nickname  the nickname
     * @param turn  the turn
     */
    public Player(Boolean is_first, Library my_shelfie, String nickname, Boolean turn) {

        this.is_first = is_first;
        this.my_shelfie = my_shelfie;
        this.nickname = nickname;
        this.scanner = new Scanner(System.in);
        this.turn = turn;
        this.coordinates = new Coordinate();
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


    /**

     This method allows the player to pick up tiles from the playground.
     @param p The playground from which the player wants to pick up the tiles.
     @return A boolean value indicating whether the player's shelfie was able to successfully
     */
    public Boolean pickUp(Playground p){
        // Initialize variables
        this.coordinates = new Coordinate();
        int i = 0;
        int tmpX,tmpY;
        int pick;
        System.out.println("Scrivi la colonna\n");
        int column = scanner.nextInt()-1;

        // Allow the player to pick up to 3 tiles
        do {
            // Prompt the player for coordinates
            System.out.println("Scrivi le coordinate\n");
            System.out.println("X:");
            tmpX = scanner.nextInt()-1;
            System.out.println("Y:");
            tmpY=scanner.nextInt()-1;
            System.out.println("Per stop -1:");
            pick = scanner.nextInt();
            // Store the picked tile in the picked Vector
            this.coordinates.addALL(tmpX,tmpY,p.getGround()[tmpX][tmpY].getType());
            // Display information about the picked tile
            System.out.print("Type: "+this.coordinates.getType().get(i)+"\t");
            try{
                System.out.print("X:"+this.coordinates.getXByIndex(i)+"\t");
                System.out.println("Y:"+this.coordinates.getYByIndex(i));
            }catch (CoordinateStateException e){
                System.out.println(e.getMessage());
            }
            i++;
        }while(((pick!= -1 )&&(i!=3))||(i<this.my_shelfie.available()[column]));
// Call the posix method of the player's shelfie to place the picked up tiles on their shelf
        try {
            return this.my_shelfie.posix(this.coordinates, column, this.coordinates.size(), p);
        }catch (CoordinateStateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}