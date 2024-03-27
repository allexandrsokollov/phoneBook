package exceptions;

public class MaxPhoneNumbersAmountException extends RuntimeException{
    public MaxPhoneNumbersAmountException(String errorMessage) {
        super(errorMessage);
    }
}
