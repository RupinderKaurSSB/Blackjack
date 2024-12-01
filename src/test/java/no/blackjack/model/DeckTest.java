package no.blackjack.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void deckShouldBeCreatedSuccessfully() {
        Deck deck = new Deck();
        assertNotNull(deck);
    }

    @Test
    void shouldDrawCardFromDeck() {
        Deck deck = new Deck();
        Card card = deck.drawCard();
        assertNotNull(card);
    }
}
