package it.polimi.ingsw.socket;

import java.io.IOException;
import it.polimi.ingsw.socket.Message;

public interface Observer {
    void update(Message message)throws IOException;
}
