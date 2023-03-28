package it.polimi.ingsw;
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
    private Map<Integer, Integer> coordinates;


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
        this.coordinates= new HashMap<Integer, Integer>();

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
        this.coordinates = new HashMap<Integer,Integer>();
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

    /*
     *
     *
     *
     * */

    /**
     *
     * Pick up
     *
     * Interface with user, for choose his strategy of pickUp tiles from playground.
     *
     * @param p  the playground, it's where all the tiles are stored
     * @return Boolean
     */

    public Boolean pickUp(Playground p){

        int i = 0;
        int pick = 0;
        System.out.println("Scrivi la colonna\n");
        int column = scanner.nextInt();
        do {
            i++;
            System.out.println("Scrivi le coordinate\n");
            this.coordinates.put(scanner.nextInt(), scanner.nextInt());
            pick = scanner.nextInt();
        }while((pick!= -1 )&&(i!=3));
        Vector<Tiles> picked = new Vector<Tiles>();
        for(Map.Entry<Integer,Integer> entry : this.coordinates.entrySet()){
            System.out.println(entry.getKey()+"\t"+ entry.getValue());
            picked.add(p.getGround()[entry.getKey()][entry.getValue()]);
            // System.out.println(picked.get(0).getType());
        }
        setMy_shelfie(this.my_shelfie.posix(picked,column,picked.size(),this.my_shelfie,p));
        coordinates = new HashMap<Integer,Integer>();

        return false;
    }

}