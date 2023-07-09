package sg.edu.nus.iss.Workshop26.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.nus.iss.Workshop26.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException ex){
        ApiError errMsg = new ApiError(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage());

        return new ResponseEntity<ApiError>(errMsg, HttpStatus.NOT_FOUND);

        // ModelAndView mav = new ModelAndView();
        // mav.setViewName("not-found");
        // mav.addObject(errMsg);
        // mav.setStatus(HttpStatusCode.valueOf(404));
        // return mav;

    }

}
