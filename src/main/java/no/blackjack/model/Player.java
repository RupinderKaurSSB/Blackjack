package no.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public int getPoints() {
        int points = 0;
        int aces = 0;

        points = hand.stream()
                .mapToInt(Card::getValue)
                .sum();

        aces = (int) hand.stream()
                .filter(card -> card.getValue() == Rank.ACE.getValue())
                .count();

        /*while (points > 21 && aces > 0) {
            points -= 10;
            aces--;
        }*/

        return points;
    }

    /*public int getPoints() {
        int points = 0;
        for (Card card : hand) {
            points += card.getValue();
        }
        return points;
    }*/

    public boolean isBust() {
        return getPoints() > 21;
    }
}
