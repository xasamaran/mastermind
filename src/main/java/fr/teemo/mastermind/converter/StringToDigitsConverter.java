package main.java.fr.teemo.mastermind.converter;

import main.java.fr.teemo.mastermind.model.Digit;

import java.util.*;

public class StringToDigitsConverter {
    public SortedSet<Digit> toDigits(String inputedString) {
        int position = 1;
        SortedSet<Digit> digits = new TreeSet<>(Digit::compareTo);
        for (char chad : inputedString.toCharArray()) {
            digits.add(new Digit(chad - '0', position));
            position++;
        }
        return digits;
    }

    public String toInputedString(Set<Digit> digits) {
        return digits.stream()
                .map(digit -> Integer.toString(digit.getValue()))
                .reduce((s, s2) -> s + s2)
                .orElse("");
    }

    public String toMatchingString(Set<Digit> digits) {
        return digits.stream()
                .map(digit -> digit.getMatch().getMatchingString())
                .reduce((s, s2) -> s + s2)
                .orElse("");
    }
}
