package it.polimi.ingsw.Model.Exception;

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