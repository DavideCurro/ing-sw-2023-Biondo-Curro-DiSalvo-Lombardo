package it.polimi.ingsw.RMI.client;

import it.polimi.ingsw.Client.ClientView;
import it.polimi.ingsw.Controller.Match;
import it.polimi.ingsw.Message.Content;
import it.polimi.ingsw.Message.Message;
import it.polimi.ingsw.Model.Playground.Playground;
import it.polimi.ingsw.RMI.GameHandlerRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.lang.Thread.sleep;

public class clientRMI {
    private clientRMI() {}
    public static void main(String[] args) {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 2001);

            // Looking up the registry for the remote object
            GameHandlerRMI stub = (GameHandlerRMI) registry.lookup("GameHandler");
            ClientView view = new ClientView();
            // Calling the remote method using the obtained object
            stub.handleLogin("2hello",2);
            Message message = null;
            do {
                message = stub.getData();
            }while (message.getMessageType() == Content.FAIL);
            view.printPlayground((Playground) message.getPayload());
           sleep(50000);


        } catch (Exception e) {
            System.err.println("Client exception: " + e);

        }
    }
}