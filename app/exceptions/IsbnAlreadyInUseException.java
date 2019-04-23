package exceptions;

public class IsbnAlreadyInUseException extends Exception{

    public IsbnAlreadyInUseException(String isbn) {
        super("Id [" + isbn + "] is already in use");
    }
}
