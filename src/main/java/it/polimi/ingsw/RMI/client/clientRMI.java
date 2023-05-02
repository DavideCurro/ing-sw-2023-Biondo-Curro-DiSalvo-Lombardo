package it.polimi.ingsw.RMI.client;

import it.polimi.ingsw.RMI.GameHandlerRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class clientRMI {
    private clientRMI() {}
    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Looking up the registry for the remote object
            GameHandlerRMI stub = (GameHandlerRMI) registry.lookup("GameHandler");

            // Calling the remote method using the obtained object
           System.out.println( stub.handleLogin("hello"));

            // System.out.println("Remote method invoked");
        } catch (Exception e) {
            System.err.println("Client exception: " + e);
            e.printStackTrace();
        }
    }
}