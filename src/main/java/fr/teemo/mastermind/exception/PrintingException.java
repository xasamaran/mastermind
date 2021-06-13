package main.java.fr.teemo.mastermind.exception;

public class PrintingException extends RuntimeException {
    public PrintingException(String s, Throwable e) {
        super(s, e);
    }
}
