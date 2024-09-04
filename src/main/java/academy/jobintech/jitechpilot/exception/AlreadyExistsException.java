package academy.jobintech.jitechpilot.exception;


public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(String message) {
        super(message);
    }
}
