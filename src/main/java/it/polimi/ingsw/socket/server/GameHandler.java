package it.polimi.ingsw.socket.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.controller.Match;
import it.polimi.ingsw.controller.MatchExeception;
import it.polimi.ingsw.controller.VirtualView;
import it.polimi.ingsw.model.Playground.Playground;
import it.polimi.ingsw.model.Playground.Tiles;
import it.polimi.ingsw.model.player.Player;
import it.polimi.ingsw.socket.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Logger;

public class GameHandler implements Runnable {
    private Socket[] players;
    private String[] usernames;
    private MessageDispatcher[] messageDispatchers;
    private ObjectInputStream[] inputStreams;
    private ObjectOutputStream[] objectOutputStreams;
    private Match match;
    private Controller controller;
    private Vector<String> validName;
    private Message message;

    private static final Logger log = Logger.getLogger(GameHandler.class.getName());
    public GameHandler(Socket[] players, String[] usernames, ObjectOutputStream[] objectOutputStreams, ObjectInputStream[] objectInputStream) {
        this.players = players;
        this.usernames = usernames;
        this.objectOutputStreams = objectOutputStreams;
        this.inputStreams = objectInputStream;
        match = new Match();
        controller = new Controller(match, new VirtualView());
        validName = new Vector<>();
    }

    @Override
    public void run() {
        log.info("Game for started");
        for(int i = 0; i< usernames.length; i++){
            if(!valid_Name(usernames[i])){
                do {
                    log.warning("Same Nickname");
                    messageDispatchers[i].send(SenderCode.ERROR_CODE, usernames[i]);
                    message = readMessage(i);
                    usernames[i] = (String) message.getPayload();
                }while (!valid_Name(usernames[i]));
            }
        }
        setPlayer();
        match.setupPlayground(2);
        log.info("New Match started");
    }

    private Message readMessage(int i){
      return this.messageDispatchers[i].read();
    }
    private boolean valid_Name(String name){
        if(validName.contains(name)) return false;
        return validName.add(name);
    }
    private void setPlayer(){
        for(String tmp : usernames) {
            try {
                match.newPlayer(tmp);
            }catch (MatchExeception exeception){
                log.warning("ERROR In add player");
            }
        }

    }
}
