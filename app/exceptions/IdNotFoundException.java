package exceptions;

public class IdNotFoundException extends Exception {

    public IdNotFoundException(String id) {
        super("Id [" + id + "] not found in database");
    }
}
