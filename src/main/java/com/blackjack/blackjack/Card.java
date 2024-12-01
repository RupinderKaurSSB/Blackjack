package com.blackjack.blackjack;

public class Card {
    private final String suit;
    private final String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public String getValue() {
        return value;
    }

    public int getPoints() {
        switch (value) {
            case "A": return 11;
            case "K":
            case "Q":
            case "J": return 10;
            default: return Integer.parseInt(value);
        }
    }

    @Override
    public String toString() {
        return suit + value;
    }
}
