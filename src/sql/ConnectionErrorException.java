package sql;

/**
 * Thrown when there is an connection error to the database
 */
public class ConnectionErrorException extends Exception{

    /**
     * Construct a {@code ConnectionErrorException} with a detailed message
     * @param message   the error message that will be shown to the user
     */
    public ConnectionErrorException(String message){
        super(message);

    }
}
