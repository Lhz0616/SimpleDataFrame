package data;

/**
 * Thrown when the number of column or row are different
 */
public class UnequalNumberException extends Exception{

    /**
     * Construct a {@code UnequalNumberException} with a detailed message
     * @param message detailed message
     */
    public UnequalNumberException(String message){
        super(message);
    }
}
