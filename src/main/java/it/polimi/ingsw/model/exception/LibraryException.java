package it.polimi.ingsw.model.exception;


public class LibraryException extends Exception {
    /**
     *
     * Library Exception
     *
     * @param errorMessage  the error message.
     *
     */
    public LibraryException(String errorMessage) {
        super(errorMessage);
    }
}

