package no.blackjack.service;

import no.blackjack.model.Card;
import no.blackjack.model.Deck;
import no.blackjack.model.Player;

import java.util.logging.Logger;

public class BlackjackGame {
    Logger logger = Logger.getLogger(getClass().getName());

    private static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_MIN_POINTS = 17;

    private final Deck deck;
    private final Player dealer;
    private final Player player;

    public BlackjackGame(String playerName) {
        deck = new Deck();
        dealer = new Player("Dealer");
        player = new Player(playerName);
    }

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

        GameResult result;
        if (dealer.isBust()) {
            result = new GameResult(player, player.getName() + " wins! Dealer went bust!", player.getPoints());
        } else if (player.isBust()) {
            result = new GameResult(dealer, "Dealer wins! " + player.getName() + " went bust!", dealer.getPoints());
        } else if (dealer.getPoints() > player.getPoints()) {
            result = new GameResult(dealer, "Dealer wins!", dealer.getPoints());
        } else if (player.getPoints() > dealer.getPoints()) {
            result = new GameResult(player, player.getName() + " wins!", player.getPoints());
        } else {
            result = new GameResult(null, "It's a tie!", dealer.getPoints());
        }

        logger.info(result.getMessage());
        return result;
    }

    private void printPlayerStatus(Player currentPlayer) {
        logger.info(currentPlayer.getName() + "| " + currentPlayer.getPoints() +
                " | " + currentPlayer.getHand());
    }
}