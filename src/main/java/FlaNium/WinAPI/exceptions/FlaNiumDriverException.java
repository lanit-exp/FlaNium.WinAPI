package FlaNium.WinAPI.exceptions;

public class FlaNiumDriverException extends RuntimeException{

    public FlaNiumDriverException(Throwable cause) {
        super(cause);
    }

    public FlaNiumDriverException(String message) {
        super(message);
    }

    public FlaNiumDriverException(String message, Throwable cause) {
        super(message, cause);
    }
}
