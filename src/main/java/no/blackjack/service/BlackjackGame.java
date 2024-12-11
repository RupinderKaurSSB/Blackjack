package no.blackjack.service;

import no.blackjack.model.Card;
import no.blackjack.model.Deck;
import no.blackjack.model.Player;

import java.util.logging.Logger;

/*
 * This class represents a Blackjack game.
 */
public class BlackjackGame {
    Logger logger = Logger.getLogger(getClass().getName());

    private static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_MIN_POINTS = 17;

    /*
     * The deck of cards used in the game.
     */
    private final Deck deck;
    /*
     * The players in the game.
     */
    private final Player dealer;
    private final Player player;

    public BlackjackGame(String playerName) {
        deck = new Deck();
        dealer = new Player("Dealer");
        player = new Player(playerName);
    }

    /**
     * Plays the game.
     * @return GameResult object containing the result of the game.
     */
    public GameResult play() {
        dealInitialCards();

        if (checkBlackjack()) {
            return determineWinner();
        }

        playerTurn(dealer, DEALER_MIN_POINTS);

        if (!dealer.isBust()) {
            playerTurn(player, dealer.getPoints());
        }

        return determineWinner();
    }

    /**
     * Deals the initial cards to the players.
     */
    private void dealInitialCards() {
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());

        logger.info("Initial hands:");
        printPlayerStatus(dealer);
        printPlayerStatus(player);
    }

    private boolean checkBlackjack() {
        if (dealer.getPoints() == BLACKJACK_VALUE || player.getPoints() == BLACKJACK_VALUE) {
            logger.info("\nBlackjack!");
            determineWinner();
            return true;
        }
        return false;
    }

    private void playerTurn(Player currentPlayer, int targetPoints) {
        logger.info("\n" + currentPlayer.getName() + "'s turn:");
        while (currentPlayer.getPoints() < targetPoints && !currentPlayer.isBust()) {
            Card card = deck.drawCard();
            currentPlayer.addCard(card);
            printPlayerStatus(currentPlayer);
        }
    }

    private GameResult determineWinner() {
        logger.info("\nGame Over!");
        printPlayerStatus(dealer);
        printPlayerStatus(player);

        GameResult result = evaluateGameOutcome();

        logger.info(result.getMessage());
        return result;
    }

    private GameResult evaluateGameOutcome() {
        if (dealer.isBust()) {
            return createPlayerWinResult("Dealer went bust!");
        }

        if (player.isBust()) {
            return createDealerWinResult("Player went bust!");
        }

        return comparePlayerPoints();
    }

    private GameResult comparePlayerPoints() {
        if (dealer.getPoints() > player.getPoints()) {
            return new GameResult(dealer, "Dealer wins!", dealer.getPoints());
        }

        if (player.getPoints() > dealer.getPoints()) {
            return createPlayerWinResult("wins!");
        }

        return new GameResult(null, "It's a tie!", dealer.getPoints());
    }

    private GameResult createPlayerWinResult(String message) {
        return new GameResult(player, player.getName() + " " + message, player.getPoints());
    }

    private GameResult createDealerWinResult(String message) {
        return new GameResult(dealer, "Dealer wins! " + player.getName() + " " + message, dealer.getPoints());
    }

    private void printPlayerStatus(Player currentPlayer) {
        logger.info(currentPlayer.getName() + "| " + currentPlayer.getPoints() +
                " | " + currentPlayer.getHand());
    }
}