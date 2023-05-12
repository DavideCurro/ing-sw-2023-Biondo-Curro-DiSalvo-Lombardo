package it.polimi.ingsw.RMI.client;

import it.polimi.ingsw.Client.ClientView;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.Model.Playground.Tiles;
import it.polimi.ingsw.RMI.GameHandlerRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Vector;

public class clientRMI {
    private clientRMI() {}
    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 2000);

            // Looking up the registry for the remote object
            GameHandlerRMI stub = (GameHandlerRMI) registry.lookup("GameHandler");
            ClientView view = new ClientView();
            // Calling the remote method using the obtained object
            stub.handleLogin("2hello",2);
            stub.handleLogin("2hello",2);
            Playground playground =  stub.playgroundStart();
            view.printPlayground(playground);
            Vector<Tiles> tiles = new Vector<>();
            tiles.add(new Tiles(-1,1,3));
           System.out.println(stub.handleTurn(1,tiles));
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}