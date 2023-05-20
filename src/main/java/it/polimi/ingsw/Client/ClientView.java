package it.polimi.ingsw.Client;

import it.polimi.ingsw.Utility.Color;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.*;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;

import java.util.Map;
import java.util.Vector;


/**
 * The class Client view
 */
public class ClientView {
    private int playerNum;
    private static final Map<Integer,Integer> pointOBJ2player = Map.of(1,8,2,4);
    private static final Map<Integer,Integer> pointOBJ3player = Map.of(1,8,2,6,3,4);
    private static final Map<Integer,Integer> pointOBJ4player = Map.of(1,8,2,4,3,4,4,2);


    /**
     *
     * It is a constructor.
     *
     */
    public ClientView(){}

    /**
     * Set playerNum
     * @param playerNum, lobby type
     */
    public void setPlayerNum(int playerNum){

        this.playerNum = playerNum;
    }

    /**
     *
     * Print playground
     *
     * @param ground  the ground.
     */
    public void printPlayground(Playground ground){

        for (int i = 0; i < 10; i++) {
            colorChange(3);
            if(i !=0)  System.out.print(i+"\t");
            else System.out.print("\t");
            for (int j = 0; j < 9; j++) {
                if(i == 0){
                    System.out.print(j+1+"\t");
                }else {
                    colorChange(ground.getGround()[i-1][j].getType());
                    System.out.print("■" + "\t");
                }
            }
            System.out.println();
        }
        colorChange(3);

    }

    /**
     *
     * Print player library
     *
     * @param player  the player.
     */
    public void printPlayerLibrary(Player player){

        printOutLibrary(player);
    }
    static void colorChange(int type){
        switch (type) {
            case 0 -> System.out.print(Color.BLUE);
            case 1 -> System.out.print(Color.CYAN_BRIGHT);
            case 2 -> System.out.print(Color.YELLOW_BRIGHT);
            case 3 -> System.out.print(Color.WHITE_BRIGHT);
            case 4 -> System.out.print(Color.GREEN_BRIGHT);
            case 5 -> System.out.print(Color.MAGENTA_BRIGHT);
            case 6 -> System.out.print(Color.RESET);
            default -> System.out.print(Color.BLACK);
        }
    }


    public void printCommonOBJ(int type){
        switch (type){
            case 1-> System.out.println("This game is playing on Goal 1");
            case 2-> System.out.println("This game is playing on Goal 2");
            case 3-> System.out.println("This game is playing on Goal 3");
            case 4-> System.out.println("This game is playing on Goal 4");
            case 5-> System.out.println("This game is playing on Goal 5");
            case 6-> System.out.println("This game is playing on Goal 6");
            case 7-> System.out.println("This game is playing on Goal 7");
            case 8-> System.out.println("This game is playing on Goal 8");
            case 9-> System.out.println("This game is playing on Goal 9");
            case 10->System.out.println("This game is playing on Goal 10");
            case 11->System.out.println("This game is playing on Goal 11");
            case 12->System.out.println("This game is playing on Goal 12");


        }
    }
    /**
     *
     * Print output*
     *
     */
    private static void printOutLibrary(Player player) {

        if(player.getMy_shelfie().isEmpty())   System.out.println("This player has an empty library");
        for (int i = 0; i < 7; i++) {
            colorChange(3);
            if(i!=0)  System.out.print(i+"\t");
            else System.out.print("\t");
            for (int j = 0; j < 5; j++) {
                if(i==0) System.out.print(j+1+"\t");
                else {
                    colorChange(player.getMy_shelfie().getShelf()[i-1][j].getType());
                    System.out.print("■" + "\t");
                }
            }
            System.out.println();

        }
        colorChange(3);
    }

    /**
     *
     * Print output points per player
     *
     * @param player  the player.
     */
    public void printOutPointsPerPlayer(Player player){

        System.out.println(player.getNickname() + " achieved : "+ player.getPublicPoints()+" points" );
    }

    /**
     *
     * Print personal point
     *
     * @param player  the player.
     */
    public void printPersonalPoint(Player player){

        System.out.println("You achieved : "+ player.getPoints()+" points" );
    }

    /**
     *
     * Check remaining point
     *
     * @param index  the index.
     * @return int
     */
    private int checkRemainingPoint(int index){

        switch (playerNum){
            case 2-> {return pointOBJ2player.get(index);}
            case 3-> {return pointOBJ3player.get(index);}
            case 4-> {return pointOBJ4player.get(index);}
        }
        return -1;
    }

    /**
     *
     * Print new highest score
     *
     * @param commonOBJResult  the common OBJ result.
     */
    public void printNewHighestScore(int[] commonOBJResult){

        System.out.println("The players who made the first commonObj are " + commonOBJResult[1]+1 +" the remaining points are: "+ checkRemainingPoint(commonOBJResult[1]));
        System.out.println("The players who made the second commonObj are " + commonOBJResult[2]+1 + " the remaining points are: "+ checkRemainingPoint(commonOBJResult[2]));

    }

    /**
     *
     * Print choose lobby
     *
     * @param nickname  the nickname.
     */
    public void printChooseLobby(String nickname){

        System.out.println(nickname + " please choose in which lobby do you want to join!");
        System.out.println("2\t3\t4");
        System.out.print("Please log me in the lobby with : ");
    }

    /**
     *
     * Print personal OBJ
     *
     * @param player  the player.
     */
    public void printPersonalOBJ(Player player){

        Vector<Tiles> position = player.getPersonalObj().getPersonalObj().getPosition();
        Library tmp = buildLibraryPosition(position);
        printOutLibrary(new Player(tmp));
        System.out.println();
    }

    /**
     *
     * Print end game point
     *
     * @param player  the player.
     */
    public void printEndGamePoint(Player player){

        System.out.println(player.getNickname()+  " achieved"+ player.getPoints()+" points" );
    }

    /**
     *
     * Build library position
     *
     * @param position  the position.
     * @return Library
     */
    public static Library buildLibraryPosition(Vector<Tiles> position){

        Library tmp = new Library();
        for (Tiles tiles : position) {
            tmp.getShelf()[tiles.getX()][tiles.getY()].setType(tiles.getType());
        }
        return tmp;
    }

    /**
     *
     * Welcome
     *
     */
    public void welcome(){

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n" +
                " __     __     ______     __         ______     ______     __    __     ______        __     __   __        __    __     __  __        ______     __  __     ______     __         ______   __     ______    \n" +
                "/\\ \\  _ \\ \\   /\\  ___\\   /\\ \\       /\\  ___\\   /\\  __ \\   /\\ \"-./  \\   /\\  ___\\      /\\ \\   /\\ \"-.\\ \\      /\\ \"-./  \\   /\\ \\_\\ \\      /\\  ___\\   /\\ \\_\\ \\   /\\  ___\\   /\\ \\       /\\  ___\\ /\\ \\   /\\  ___\\   \n" +
                "\\ \\ \\/ \".\\ \\  \\ \\  __\\   \\ \\ \\____  \\ \\ \\____  \\ \\ \\/\\ \\  \\ \\ \\-./\\ \\  \\ \\  __\\      \\ \\ \\  \\ \\ \\-.  \\     \\ \\ \\-./\\ \\  \\ \\____ \\     \\ \\___  \\  \\ \\  __ \\  \\ \\  __\\   \\ \\ \\____  \\ \\  __\\ \\ \\ \\  \\ \\  __\\   \n" +
                " \\ \\__/\".~\\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\ \\_\\  \\ \\_____\\     \\ \\_\\  \\ \\_\\\\\"\\_\\     \\ \\_\\ \\ \\_\\  \\/\\_____\\     \\/\\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\  \\ \\_____\\  \\ \\_\\    \\ \\_\\  \\ \\_____\\ \n" +
                "  \\/_/   \\/_/   \\/_____/   \\/_____/   \\/_____/   \\/_____/   \\/_/  \\/_/   \\/_____/      \\/_/   \\/_/ \\/_/      \\/_/  \\/_/   \\/_____/      \\/_____/   \\/_/\\/_/   \\/_____/   \\/_____/   \\/_/     \\/_/   \\/_____/ \n" +
                "                                                                                                                                                                                                             \n");
    }


    /**
     *
     * Print winner
     *
     * @param player  the player.
     */
    public void printWinner(Player player){

        System.out.println("\n" +
                "▀█▀ █░█ █▀▀   █░█░█ █ █▄░█ █▄░█ █▀▀ █▀█   █ █▀\n" +
                "░█░ █▀█ ██▄   ▀▄▀▄▀ █ █░▀█ █░▀█ ██▄ █▀▄   █ ▄█");
        System.out.println(player.getNickname());
    }
}
