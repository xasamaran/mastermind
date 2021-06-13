package main.java.fr.teemo.mastermind.exception;

import main.java.fr.teemo.mastermind.model.Digit;

import java.util.Set;

public class MatchingException extends Exception {
    private final Set<Digit> digits;

    public MatchingException(Set<Digit> digits) {
        this.digits = digits;
    }

    public Set<Digit> getDigits() {
        return digits;
    }
}
