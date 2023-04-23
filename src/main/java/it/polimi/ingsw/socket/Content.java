package it.polimi.ingsw.socket;

import java.io.Serializable;

public enum Content implements Serializable {

    NICKNAME,
    NEWGAME,
    PICKTILES,
    GAMEJOIN,
    WINNER,
    POINTS,
    NEWPOINTSMAX,
    TILESPICKED,
    COORDINATE,
    SUCCESS,
    FAIL,
    PING,
    ACK,
    GAMECREATED,
    NEWTURN,
    PLAYGROUND
}
