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

/*    @Test
    void getPoints_SingleAce_ShouldCount11() {
        Player player = new Player("TestPlayer");
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));

        assertEquals(11, player.getPoints());
    }

    @Test
    void getPoints_AceWithNine_ShouldCount20() {
        Player player = new Player("TestPlayer");
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.CLUBS, Rank.NINE));

        assertEquals(20, player.getPoints());
    }

    @Test
    void getPoints_TwoAces_ShouldCount12() {
        Player player = new Player("TestPlayer");
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.ACE));

        assertEquals(12, player.getPoints());
    }

    @Test
    void getPoints_AceWithKingAndQueen_ShouldCount21() {
        Player player = new Player("TestPlayer");
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.DIAMONDS, Rank.KING));
        player.addCard(new Card(Suit.CLUBS, Rank.QUEEN));

        assertEquals(21, player.getPoints());
    }

    @Test
    void getPoints_ThreeAces_ShouldCount13() {
        Player player = new Player("TestPlayer");
        player.addCard(new Card(Suit.HEARTS, Rank.ACE));
        player.addCard(new Card(Suit.DIAMONDS, Rank.ACE));
        player.addCard(new Card(Suit.SPADES, Rank.ACE));

        assertEquals(13, player.getPoints());
    }*/
}
