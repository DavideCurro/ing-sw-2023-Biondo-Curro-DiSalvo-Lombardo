package it.polimi.ingsw;

import it.polimi.ingsw.Client.Client;
import it.polimi.ingsw.Client.ClientView;
import it.polimi.ingsw.Server.Server;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        if(args.length>0){
            switch (args[0]){
                case "Server" -> new Server();
                case "Client" ->{
                    if(args.length> 1){
                        new Client(args[1]);
                    }else{
                        new Client();
                    }
                }
            }
        }
    }
}
