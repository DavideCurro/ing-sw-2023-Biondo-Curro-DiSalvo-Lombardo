package it.polimi.ingsw.Model.Exception;

public class PlaygroundException extends Exception {
    /**
     *
     * Playground player exception
     *
     * @param errorMessage  the error message.
     * @return public
     */
    public PlaygroundException(String errorMessage) {
        super(errorMessage);
    }
}
