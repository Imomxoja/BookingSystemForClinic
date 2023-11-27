package uz.pdp.doctor.domain.exceptionHandle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import uz.pdp.doctor.domain.exceptionHandle.exceptions.UserEmailNotFoundException;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<String> userEmailNotFoundException(UserEmailNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
