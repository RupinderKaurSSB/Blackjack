package no.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand = new ArrayList<>();
    private int points = 0;

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        hand.add(card);
        points += card.getPoints();
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public List<Card> getHand() {
        return hand;
    }

    public boolean isBust() {
        return points > 21;
    }
}
