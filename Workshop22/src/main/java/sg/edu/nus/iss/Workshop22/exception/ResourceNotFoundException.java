package sg.edu.nus.iss.Workshop22.exception;

public class ResourceNotFoundException extends RuntimeException{
    
    public ResourceNotFoundException(){
        super();
    }
    
    public ResourceNotFoundException(String message){
        super(message);
    } 
    
    public ResourceNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause){
        super(cause);
    }
}
