package it.polimi.ingsw.Client;

import it.polimi.ingsw.Controller.Color;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.Model.CommonStrategy.*;
import it.polimi.ingsw.Model.Player.Library;
import it.polimi.ingsw.Model.Player.Player;

import java.util.Map;
import java.util.Vector;

public class ClientView {
    private int playerNum;
    private static final Map<Integer,Integer> pointOBJ2player = Map.of(1,8,2,4);
    private static final Map<Integer,Integer> pointOBJ3player = Map.of(1,8,2,6,3,4);
    private static final Map<Integer,Integer> pointOBJ4player = Map.of(1,8,2,4,3,4,4,2);
    public ClientView(){}
    public void setPlayerNum(int playerNum){
        this.playerNum = playerNum;
    }
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
    public void printCommonOBJ(Object commonObj){
        if(commonObj instanceof GoalC1){
            System.out.println("This game is playing on Goal 1");
        } else if (commonObj instanceof GoalC2) {
            System.out.println("This game is playing on Goal 2");
        } else if (commonObj instanceof GoalC3) {
            System.out.println("This game is playing on Goal 3");
        } else if (commonObj instanceof GoalC4) {
            System.out.println("This game is playing on Goal 4");
        } else if (commonObj instanceof GoalC5) {
            System.out.println("This game is playing on Goal 5");
        } else if (commonObj instanceof GoalC6) {
            System.out.println("This game is playing on Goal 6");
        } else if (commonObj instanceof GoalC7) {
            System.out.println("This game is playing on Goal 7");
        } else if (commonObj instanceof GoalC8) {
            System.out.println("This game is playing on Goal 8");
        } else if (commonObj instanceof GoalC9) {
            System.out.println("This game is playing on Goal 9");
        } else if (commonObj instanceof GoalC10) {
            System.out.println("This game is playing on Goal 10");
        } else if (commonObj instanceof GoalC11) {
            System.out.println("This game is playing on Goal 11");
        } else if (commonObj instanceof GoalC12) {
            System.out.println("This game is playing on Goal 12");
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
    public void printOutPointsPerPlayer(Player player){
        System.out.println(player.getNickname() + " achieved : "+ player.getPublicPoints()+" points" );
    }
    public void printPersonalPoint(Player player){
        System.out.println("You achieved : "+ player.getPoints()+" points" );
    }
    private int checkRemainingPoint(int index){
        switch (playerNum){
            case 2-> {return pointOBJ2player.get(index);}
            case 3-> {return pointOBJ3player.get(index);}
            case 4-> {return pointOBJ4player.get(index);}
        }
        return -1;
    }
    public void printNewHighestScore(int[] commonOBJResult){
        System.out.println("The players who made the first commonObj are " + commonOBJResult[1]+1 +" the remaining points are: "+ checkRemainingPoint(commonOBJResult[1]));
        System.out.println("The players who made the second commonObj are " + commonOBJResult[2]+1 + " the remaining points are: "+ checkRemainingPoint(commonOBJResult[2]));

    }

    public void printPersonalOBJ(Player player){
        Vector<Tiles> position = player.getPersonalObj().getPersonalObj().getPosition();
        Library tmp = buildLibrarybyPosition(position);
        printOutLibrary(new Player(tmp));
        System.out.println();
    }
    public void printEndGamePoint(Player player){
        System.out.println(player.getNickname()+  " achieved"+ player.getPoints()+" points" );
    }
    private static Library buildLibrarybyPosition(Vector<Tiles> position){
        Library tmp = new Library();
        for (Tiles tiles : position) {
            tmp.getShelf()[tiles.getX()][tiles.getY()].setType(tiles.getType());
        }
        return tmp;
    }
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

    public void printWinner(Player player){
        System.out.println("\n" +
                "▀█▀ █░█ █▀▀   █░█░█ █ █▄░█ █▄░█ █▀▀ █▀█   █ █▀\n" +
                "░█░ █▀█ ██▄   ▀▄▀▄▀ █ █░▀█ █░▀█ ██▄ █▀▄   █ ▄█");
        System.out.println(player.getNickname());
    }
}
