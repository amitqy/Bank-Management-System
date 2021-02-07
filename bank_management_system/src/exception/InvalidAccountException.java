package exception;

public class InvalidAccountException extends Exception {
    @Override
    public String getMessage() {
        return "This account does not exist";
    }
}
