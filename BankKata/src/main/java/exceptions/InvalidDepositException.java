package exceptions;

public class InvalidDepositException extends RuntimeException{
    public InvalidDepositException(String message) {
        super(message);
    }
}
