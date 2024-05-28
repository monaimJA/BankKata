package exceptions;

public class InvalidWithdrawalException extends RuntimeException{
    public InvalidWithdrawalException(String message) {
        super(message);
    }
}
