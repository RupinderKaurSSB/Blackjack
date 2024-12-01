package no.blackjack.model;

public class Card {
    private String suit;
    private String value;

    // Default constructor for Jackson
    public Card() {
    }

    // Constructor with parameters
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    // Getters and setters
    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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