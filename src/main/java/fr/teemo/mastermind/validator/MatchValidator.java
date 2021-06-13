package main.java.fr.teemo.mastermind.validator;

import main.java.fr.teemo.mastermind.exception.MatchingException;
import main.java.fr.teemo.mastermind.model.Digit;
import main.java.fr.teemo.mastermind.model.MatchingType;

import java.util.Optional;
import java.util.Set;

public class MatchValidator {
    public void validate(Set<Digit> inputedDigits, Set<Digit> secretCode) throws MatchingException {
        inputedDigits.forEach(digit -> checkPerfectMatch(digit, secretCode));
        inputedDigits.stream()
                .filter(digit -> digit.getMatch() == MatchingType.None)
                .forEach(digit -> checkPartialMatch(digit, secretCode));
        secretCode.forEach(digit -> digit.setMatch(MatchingType.None));
        if (inputedDigits.stream().anyMatch(digit -> !digit.getMatch().equals(MatchingType.Perfecft))) {
            throw new MatchingException(inputedDigits);
        }
    }

    private void checkPerfectMatch(Digit digit, Set<Digit> secretCode) {
        Optional<Digit> perfectMatch = secretCode.stream().filter(secretDigit -> secretDigit.getMatch() == MatchingType.None && secretDigit.equals(digit)).findFirst();
        if (perfectMatch.isPresent()) {
            digit.setMatch(MatchingType.Perfecft);
            perfectMatch.get().setMatch(MatchingType.Perfecft);
        }
    }

    private void checkPartialMatch(Digit digit, Set<Digit> secretCode) {
        Optional<Digit> partialMatch = secretCode.stream().filter(secretDigit -> secretDigit.getMatch() == MatchingType.None && secretDigit.getValue() == (digit.getValue())).findFirst();
        if (partialMatch.isPresent()) {
            digit.setMatch(MatchingType.Partial);
            partialMatch.get().setMatch(MatchingType.Partial);
        }
    }
}
