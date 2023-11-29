package uz.pdp.doctor.domain.exceptionHandle.exceptions;

public class UserEmailNotFoundException extends RuntimeException{
    public UserEmailNotFoundException(String message) {
        super(message);
    }
}
