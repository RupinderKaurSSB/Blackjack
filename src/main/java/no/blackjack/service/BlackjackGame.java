package no.blackjack.service;

import no.blackjack.model.Card;
import no.blackjack.model.Deck;
import no.blackjack.model.Player;

public class BlackjackGame {
    private final Deck deck;
    private final Player you;
    private final Player magnus;

    public BlackjackGame() {
        deck = new Deck();
        you = new Player("You");
        magnus = new Player("Magnus");
    }

    public void play() {
        // Initial deal
        dealInitialCards();

        // Check for initial blackjack
        if (checkBlackjack()) {
            return;
        }

        // Your turn
        playerTurn(you, 17);

        // Magnus's turn
        if (!you.isBust()) {
            playerTurn(magnus, you.getPoints());
        }

        determineWinner();
    }

    private void dealInitialCards() {
        you.addCard(deck.drawCard());
        you.addCard(deck.drawCard());
        magnus.addCard(deck.drawCard());
        magnus.addCard(deck.drawCard());

        System.out.println("Initial hands:");
        printPlayerStatus(you);
        printPlayerStatus(magnus);
    }

    private boolean checkBlackjack() {
        if (you.getPoints() == 21 || magnus.getPoints() == 21) {
            System.out.println("\nBlackjack!");
            determineWinner();
            return true;
        }
        return false;
    }

    private void playerTurn(Player player, int targetPoints) {
        System.out.println("\n" + player.getName() + "'s turn:");
        while (player.getPoints() < targetPoints && !player.isBust()) {
            Card card = deck.drawCard();
            player.addCard(card);
            printPlayerStatus(player);
        }
    }

    private void determineWinner() {
        System.out.println("\nGame Over!");
        printPlayerStatus(you);
        printPlayerStatus(magnus);

        if (you.isBust()) {
            System.out.println("Magnus wins! You went bust!");
        } else if (magnus.isBust()) {
            System.out.println("You win! Magnus went bust!");
        } else if (you.getPoints() > magnus.getPoints()) {
            System.out.println("You win!");
        } else if (magnus.getPoints() > you.getPoints()) {
            System.out.println("Magnus wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void printPlayerStatus(Player player) {
        System.out.println(player.getName() + ": points=" + player.getPoints() +
                         ", cards=" + player.getHand());
    }
}
