package it.polimi.ingsw.socket.client;

import it.polimi.ingsw.controller.Color;
import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;

import java.util.Vector;

public class ClientView {
    public ClientView() {

    }
    public void printPlayground(Playground ground){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                colorChange(ground.getGround()[i][j].getType());
                System.out.print("■" + "\t");
            }
            System.out.println();
        }
        colorChange(6);
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


    /**
     *
     * Print output*
     *
     */
    private static void printOutLibrary(Player player) {
        System.out.println(player.getNickname() +"'s Library");
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                colorChange(player.getMy_shelfie().getShelf()[i][j].getType());
                System.out.print("■"+ "\t");
            }
            System.out.println();
        }
        colorChange(6);
    }
    public void printOutPointsPerPlayer(Player player){
        System.out.println(player.getNickname() + " achieved : "+ player.getPoints()+" points" );
    }

    public void printPersonalOBJ(Player player){
        Vector<Tiles> position = player.getPersonalObj().getPersonalObj().getPosition();
        Library tmp = buildLibrarybyPosition(position);
        printOutLibrary(new Player(tmp));
        System.out.println();
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
}
