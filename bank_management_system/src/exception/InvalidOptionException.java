package exception;

public class InvalidOptionException extends Exception {
    @Override
    public String getMessage() {
        return "Invalid Option Entered";
    }
}
