package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Library;
import it.polimi.ingsw.model.player.Player;

import java.util.Vector;

public class VirtualView {

    public VirtualView() {
    }


    public void execeptionOccurred(MatchExeception e) {
            System.out.println(e.getMessage());
            System.exit(-1);
    }
    public void printPlayground(){
        printOutPlayground(Match.getP());
    }
    public void printPlayerLibrary(){
        printOutLibrary(Match.getLastPlayer());
    }
    private static void colorChange(int type){
        switch (type) {
            case 0 -> System.out.print(Color.BLUE.toString());
            case 1 -> System.out.print(Color.CYAN_BRIGHT.toString());
            case 2 -> System.out.print(Color.YELLOW_BRIGHT.toString());
            case 3 -> System.out.print(Color.WHITE_BRIGHT.toString());
            case 4 -> System.out.print(Color.GREEN_BRIGHT.toString());
            case 5 -> System.out.print(Color.MAGENTA_BRIGHT.toString());
            case 6 -> System.out.print(Color.RESET.toString());
            default -> System.out.print(Color.BLACK.toString());
        }
    }
    /**
     *
     * Print output playground
     *
     */
    private void printOutPlayground(Playground ground) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                colorChange(ground.getGround()[i][j].getType());
                System.out.print("■" + "\t");
            }
            System.out.println();
        }
        colorChange(6);
    }


    /**
     *
     * Print output*
     *
     */
    private static void printOutLibrary(Player player) {
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

    public static void printPersonalOBJ(Player player){
        System.out.println(player.getNickname()+ " Personal OBJ");
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
}
