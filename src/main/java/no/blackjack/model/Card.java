package no.blackjack.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {
    private final Suit suit;
    private final Rank rank;

    @JsonCreator
    public Card(
        @JsonProperty("suit") String suitStr,
        @JsonProperty("value") String rankStr
    ) {
        this.suit = parseSuit(suitStr);
        this.rank = parseRank(rankStr);
    }

    private Suit parseSuit(String suitStr) {
        return switch (suitStr) {
            case "HEARTS" -> Suit.HEARTS;
            case "DIAMONDS" -> Suit.DIAMONDS;
            case "SPADES" -> Suit.SPADES;
            case "CLUBS" -> Suit.CLUBS;
            default -> throw new IllegalArgumentException("Unknown suit: " + suitStr);
        };
    }

    private Rank parseRank(String rankStr) {
        return switch (rankStr) {
            case "A" -> Rank.ACE;
            case "2" -> Rank.TWO;
            case "3" -> Rank.THREE;
            case "4" -> Rank.FOUR;
            case "5" -> Rank.FIVE;
            case "6" -> Rank.SIX;
            case "7" -> Rank.SEVEN;
            case "8" -> Rank.EIGHT;
            case "9" -> Rank.NINE;
            case "10" -> Rank.TEN;
            case "J" -> Rank.JACK;
            case "Q" -> Rank.QUEEN;
            case "K" -> Rank.KING;
            default -> throw new IllegalArgumentException("Unknown rank: " + rankStr);
        };
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getValue() {
        return rank.getValue();
    }

    @Override
    public String toString() {
        return suit.getShortName() + rank.toString();
    }
}