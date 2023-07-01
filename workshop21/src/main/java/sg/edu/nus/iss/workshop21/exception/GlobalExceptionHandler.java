package sg.edu.nus.iss.workshop21.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex,
    HttpServletRequest request){
        ApiError errMsg = new ApiError(404, new Date(), ex.getMessage());

        return new ResponseEntity<ApiError>(errMsg, HttpStatus.NOT_FOUND);
    }
}
