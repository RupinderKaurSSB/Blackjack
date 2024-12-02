package no.blackjack.service;

import no.blackjack.model.Player;

public class GameResult {
    private final Player winner;
    private final String message;
    private final int winnerPoints;

    public GameResult(Player winner, String message, int winnerPoints) {
        this.winner = winner;
        this.message = message;
        this.winnerPoints = winnerPoints;
    }

    public Player getWinner() {
        return winner;
    }

    public String getMessage() {
        return message;
    }

    public int getWinnerPoints() {
        return winnerPoints;
    }
}
