package exceptions;

public class InvalidInputOptionException extends Exception {
    public InvalidInputOptionException() {
        super();
    }

    public InvalidInputOptionException(String message) {
        super(message);
    }
}
