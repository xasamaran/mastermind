package main.java.fr.teemo.mastermind.model;

public enum MatchingType {


    Perfecft("+"),

    Partial("-"),
    None(" ");

    private final String matchingString;


    MatchingType(String matchingString) {
        this.matchingString = matchingString;
    }

    public String getMatchingString() {
        return matchingString;
    }
}
