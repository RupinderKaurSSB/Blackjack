package no.blackjack.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void playerShouldHaveCorrectNameAndEmptyHand() {
        Player player = new Player("Test Player");
        assertEquals("Test Player", player.getName());
        assertTrue(player.getHand().isEmpty());
    }

    @Test
    void playerShouldAddCardToHand() {
        Player player = new Player("Test Player");
        Card card = new Card(Suit.HEARTS, Rank.ACE);
        player.addCard(card);
        assertEquals(1, player.getHand().size());
        assertEquals(card, player.getHand().get(0));
    }

    @Test
    void playerShouldCalculateBlackjackPoints() {
        Player player = new Player("Test Player");
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.KING));
        assertEquals(21, player.getPoints());
    }

    @Test
    void playerShouldBustWithMoreThan21Points() {
        Player player = new Player("Test Player");
        player.addCard(new Card(Suit.HEARTS, Rank.KING));
        player.addCard(new Card(Suit.SPADES, Rank.QUEEN));
        player.addCard(new Card(Suit.DIAMONDS, Rank.JACK));
        assertTrue(player.isBust());
    }
}
