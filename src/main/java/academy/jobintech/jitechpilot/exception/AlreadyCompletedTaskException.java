package academy.jobintech.jitechpilot.exception;


public class AlreadyCompletedTaskException extends RuntimeException{
    public AlreadyCompletedTaskException(String message) {
        super(message);
    }
}
