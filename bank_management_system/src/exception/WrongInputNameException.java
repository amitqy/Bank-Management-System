package exception;

public class WrongInputNameException extends Exception {
    @Override
    public String getMessage() {
        return "Only alphabets are Allowed!";
    }
}
