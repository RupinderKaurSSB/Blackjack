package no.blackjack.model;

public enum Suit {
    HEARTS("H"),
    DIAMONDS("D"),
    SPADES("S"),
    CLUBS("C");

    private final String shortName;

    Suit(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}
