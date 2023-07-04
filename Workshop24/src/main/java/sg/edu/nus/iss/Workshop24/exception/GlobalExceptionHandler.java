package sg.edu.nus.iss.Workshop24.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiError errMsg = new ApiError(HttpStatus.NOT_FOUND.value(),
            new Date(), ex.getMessage());

        return new ResponseEntity<ApiError>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex){
        ApiError errMsg = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
            new Date(), ex.getMessage());

        return new ResponseEntity<ApiError>(errMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
