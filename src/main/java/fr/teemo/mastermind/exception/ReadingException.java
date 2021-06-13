package main.java.fr.teemo.mastermind.exception;

public class ReadingException extends RuntimeException {
    public ReadingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
