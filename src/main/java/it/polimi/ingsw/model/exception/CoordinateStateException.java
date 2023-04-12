package it.polimi.ingsw.model.exception;

public class CoordinateStateException extends Exception {
    /**
     *
     * Coordinate state exception
     *
     * @param errorMessage  the error message.
     * @return public
     */
    public CoordinateStateException(String errorMessage) {
        super(errorMessage);
    }
}