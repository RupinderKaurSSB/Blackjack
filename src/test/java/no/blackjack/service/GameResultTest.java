package no.blackjack.service;

import no.blackjack.model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameResultTest {

    @Test
    void testGameResultConstructorAndGetters() {
        /*
        Tests the basic functionality of creating a GameResult object and verifying all getters work correctly with typical values.
        */
        // Arrange
        Player player = new Player("Test Player");
        String message = "Player wins!";
        int points = 21;

        // Act
        GameResult gameResult = new GameResult(player, message, points);

        // Assert
        assertEquals(player, gameResult.getWinner());
        assertEquals(message, gameResult.getMessage());
        assertEquals(points, gameResult.getWinnerPoints());
    }

    @Test
    void testGameResultWithNullWinner() {
        /*
        Tests the scenario where there might be no winner (like in a tie situation), ensuring the class handles null winners properly.
         */
        // Arrange
        String message = "It's a tie!";
        int points = 0;

        // Act
        GameResult gameResult = new GameResult(null, message, points);

        // Assert
        assertNull(gameResult.getWinner());
        assertEquals(message, gameResult.getMessage());
        assertEquals(points, gameResult.getWinnerPoints());
    }

    @Test
    void testGameResultWithEmptyMessage() {
        /*
        Tests the scenario with an empty message string, ensuring the class handles this case correctly.
         */
        // Arrange
        Player player = new Player("Test Player");
        String message = "";
        int points = 18;

        // Act
        GameResult gameResult = new GameResult(player, message, points);

        // Assert
        assertEquals(player, gameResult.getWinner());
        assertEquals(message, gameResult.getMessage());
        assertEquals(points, gameResult.getWinnerPoints());
    }
}
