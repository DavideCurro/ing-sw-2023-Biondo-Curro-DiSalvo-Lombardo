package it.polimi.ingsw;
import java.nio.channels.ScatteringByteChannel;
import java.util.*;
public class Player {
    private Boolean is_first;
    private Library my_shelfie;
    private String nickname;
    private Boolean turn;
    private Scanner scanner;
    Map<Integer, Integer> coordinates;

    public Player() {
        this.is_first = false;
        this.my_shelfie = new Library();
        this.nickname = new String();
        this.turn   = false;
        this.scanner = new Scanner(System.in);
        this.coordinates= new HashMap<Integer, Integer>();

    }
    public Player(Boolean is_first, Library my_shelfie, String nickname, Boolean turn) {
        this.is_first = is_first;
        this.my_shelfie = my_shelfie;
        this.nickname = nickname;
        this.scanner = new Scanner(System.in);
        this.turn = turn;
        this.coordinates = new HashMap<Integer,Integer>();
    }

    public Boolean getIs_first() {
        return is_first;
    }

    public void setIs_first(Boolean is_first) {
        this.is_first = is_first;
    }

    public Library getMy_shelfie() {
        return my_shelfie;
    }

    public void setMy_shelfie(Library my_shelfie) {
        this.my_shelfie = my_shelfie;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }


    public Boolean pickUp(){
        int i = 0;
        int pick = 0;
        do {
            i++;
            this.coordinates.put(scanner.nextInt(), scanner.nextInt());
            pick = scanner.nextInt();
        }while((pick!= -1 )&&(i!=3));
        for(Map.Entry<Integer,Integer> entry : this.coordinates.entrySet()){
            System.out.println(entry.getKey()+"\t"+ entry.getValue());
        }
        return false;
    }

}
