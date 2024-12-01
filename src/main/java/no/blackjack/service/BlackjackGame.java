package no.blackjack.service;

import no.blackjack.model.Card;
import no.blackjack.model.Deck;
import no.blackjack.model.Player;

import java.util.logging.Logger;

public class BlackjackGame {
    Logger logger = Logger.getLogger(getClass().getName());
    private final Deck deck;
    private final Player dealer;
    private final Player magnus;

    public BlackjackGame() {
        deck = new Deck();
        dealer = new Player("Dealer");
        magnus = new Player("Magnus");
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

        // Magnus's turn
        if (!dealer.isBust()) {
            playerTurn(magnus, dealer.getPoints());
        }

        determineWinner();
    }

    private void dealInitialCards() {
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        magnus.addCard(deck.drawCard());
        magnus.addCard(deck.drawCard());

        logger.info("Initial hands:");
        printPlayerStatus(dealer);
        printPlayerStatus(magnus);
    }

    private boolean checkBlackjack() {
        if (dealer.getPoints() == 21 || magnus.getPoints() == 21) {
            logger.info("\nBlackjack!");
            determineWinner();
            return true;
        }
        return false;
    }

    private void playerTurn(Player player, int targetPoints) {
        logger.info("\n" + player.getName() + "'s turn:");
        while (player.getPoints() < targetPoints && !player.isBust()) {
            Card card = deck.drawCard();
            player.addCard(card);
            printPlayerStatus(player);
        }
    }

    private void determineWinner() {
        logger.info("\nGame Over!");
        printPlayerStatus(dealer);
        printPlayerStatus(magnus);

        if (dealer.isBust()) {
            logger.info("Magnus wins! Dealer went bust!");
        } else if (magnus.isBust()) {
            logger.info("Dealer wins! Magnus went bust!");
        } else if (dealer.getPoints() > magnus.getPoints()) {
            logger.info("Dealer wins!");
        } else if (magnus.getPoints() > dealer.getPoints()) {
            logger.info("Magnus wins!");
        } else {
            logger.info("It's a tie!");
        }
    }

    private void printPlayerStatus(Player player) {
        logger.info(player.getName() + "| " + player.getPoints() +
                         " | " + player.getHand());
    }
}