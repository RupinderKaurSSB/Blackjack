package no.blackjack.service;

import no.blackjack.model.Card;
import no.blackjack.model.Deck;
import no.blackjack.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlackjackGameTest {

    @Mock
    private Deck mockDeck;

    private BlackjackGame game;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        game = new BlackjackGame("TestPlayer");
        // Use reflection to inject mock deck
        try {
            var deckField = BlackjackGame.class.getDeclaredField("deck");
            deckField.setAccessible(true);
            deckField.set(game, mockDeck);
        } catch (Exception e) {
            fail("Failed to inject mock deck");
        }
    }

    @Test
    void testPlayerBlackjack() {
        // Setup cards for blackjack (21)
        when(mockDeck.drawCard())
                .thenReturn(new Card("HEARTS", "A"))
                .thenReturn(new Card("SPADES", "10"))
                .thenReturn(new Card("DIAMONDS", "5"))
                .thenReturn(new Card("CLUBS", "7"));

        GameResult result = game.play();

        assertEquals("Dealer wins!", result.getMessage());
        assertEquals(21, result.getWinnerPoints());
        verify(mockDeck, times(4)).drawCard();
    }

    @Test
    void testDealerBlackjack() {
        // Setup dealer blackjack
        when(mockDeck.drawCard())
                .thenReturn(new Card("HEARTS", "10"))
                .thenReturn(new Card("SPADES", "A"))
                .thenReturn(new Card("DIAMONDS", "8"))
                .thenReturn(new Card("CLUBS", "7"));

        GameResult result = game.play();

        assertEquals("Dealer wins!", result.getMessage());
        assertEquals(21, result.getWinnerPoints());
        verify(mockDeck, times(4)).drawCard();
    }

    @Test
    void testPlayerBust() {
        // Setup player bust scenario
        when(mockDeck.drawCard())
                .thenReturn(new Card("HEARTS", "10"))
                .thenReturn(new Card("SPADES", "6"))
                .thenReturn(new Card("DIAMONDS", "10"))
                .thenReturn(new Card("CLUBS", "8"))
                .thenReturn(new Card("HEARTS", "5"));

        GameResult result = game.play();

        assertTrue(result.getMessage().contains("went bust"));
        assertEquals("Dealer", result.getWinner().getName());
        verify(mockDeck, atLeast(4)).drawCard();
    }

    @Test
    void testDealerBust() {
        // Setup dealer bust scenario
        when(mockDeck.drawCard())
                .thenReturn(new Card("HEARTS", "10"))
                .thenReturn(new Card("SPADES", "6"))
                .thenReturn(new Card("DIAMONDS", "5"))
                .thenReturn(new Card("CLUBS", "8"))
                .thenReturn(new Card("HEARTS", "10"));

        GameResult result = game.play();

        assertTrue(result.getMessage().contains("Dealer went bust"));
        assertEquals("TestPlayer", result.getWinner().getName());
        verify(mockDeck, atLeast(4)).drawCard();
    }
    @Test
    void testPlayerWinsHigherScore() {
        // Setup player winning with higher score
        when(mockDeck.drawCard())
                .thenReturn(new Card("HEARTS", "10"))
                .thenReturn(new Card("SPADES", "7"))
                .thenReturn(new Card("DIAMONDS", "10"))
                .thenReturn(new Card("CLUBS", "8"));

        GameResult result = game.play();

        assertEquals("TestPlayer wins!", result.getMessage());
        assertEquals(18, result.getWinnerPoints());
        verify(mockDeck, times(4)).drawCard();
    }

    @Test
    void testTieGame() {
        // Setup tie scenario
        when(mockDeck.drawCard())
                .thenReturn(new Card("HEARTS", "10"))
                .thenReturn(new Card("SPADES", "7"))
                .thenReturn(new Card("DIAMONDS", "10"))
                .thenReturn(new Card("CLUBS", "7"));

        GameResult result = game.play();

        assertEquals("It's a tie!", result.getMessage());
        assertNull(result.getWinner());
        assertEquals(17, result.getWinnerPoints());
        verify(mockDeck, times(4)).drawCard();
    }
}