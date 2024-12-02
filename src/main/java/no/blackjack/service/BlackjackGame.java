package no.blackjack.service;

import no.blackjack.model.Card;
import no.blackjack.model.Deck;
import no.blackjack.model.Player;

import java.util.logging.Logger;

public class BlackjackGame {
    Logger logger = Logger.getLogger(getClass().getName());
    private final Deck deck;
    private final Player dealer;
    private final Player player;

    public BlackjackGame(String playerName) {
        deck = new Deck();
        dealer = new Player("Dealer");
        player = new Player(playerName);
    }

    public void play() {
        // Initial deal
        dealInitialCards();

        // Check for initial blackjack
        if (checkBlackjack()) {
            return;
        }

        // Dealer's turn
        playerTurn(dealer, 17);

        // Player's turn
        if (!dealer.isBust()) {
            playerTurn(player, dealer.getPoints());
        }

        determineWinner();
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
        if (dealer.getPoints() == 21 || player.getPoints() == 21) {
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

    private void determineWinner() {
        logger.info("\nGame Over!");
        printPlayerStatus(dealer);
        printPlayerStatus(player);

        if (dealer.isBust()) {
            logger.info(player.getName() + " wins! Dealer went bust!");
        } else if (player.isBust()) {
            logger.info("Dealer wins! " + player.getName() + " went bust!");
        } else if (dealer.getPoints() > player.getPoints()) {
            logger.info("Dealer wins!");
        } else if (player.getPoints() > dealer.getPoints()) {
            logger.info(player.getName() + " wins!");
        } else {
            logger.info("It's a tie!");
        }
    }

    private void printPlayerStatus(Player currentPlayer) {
        logger.info(currentPlayer.getName() + "| " + currentPlayer.getPoints() +
                " | " + currentPlayer.getHand());
    }
}