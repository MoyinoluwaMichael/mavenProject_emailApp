package michael.moyinoluwa.emailApp.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.InstanceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleExceptions(InstanceNotFoundException e){
        return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }
}
