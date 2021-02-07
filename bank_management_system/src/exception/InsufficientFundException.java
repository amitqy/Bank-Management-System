package exception;
public class InsufficientFundException extends Exception {
    @Override
    public String getMessage() {
        return "InSufficient Funds! ";
    }
}