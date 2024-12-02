package no.blackjack.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BlackjackGameTest {
    @Test
    void gameShouldInitializeSuccessfully() {
        BlackjackGame game = new BlackjackGame("TestPlayer");
        assertNotNull(game);
    }
}
