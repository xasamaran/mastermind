package main.java.fr.teemo.mastermind.model;

public class Digit {

    private final int value;
    private final int position;
    private MatchingType match = MatchingType.None;

    public Digit(int value, int position) {
        this.value = value;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public MatchingType getMatch() {
        return match;
    }

    public void setMatch(MatchingType match) {
        this.match = match;
    }

    public boolean equals(Digit digit) {
        return this.position == digit.getPosition() && this.value == digit.getValue();
    }

    public int compareTo(Digit o) {
        return Integer.compare(position, o.position);
    }
}
