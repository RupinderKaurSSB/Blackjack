package no.blackjack.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards = new ArrayList<>();
    private static final String SHUFFLE_URL = "https://blackjack.ekstern.dev.nav.no/shuffle";

    public Deck() {
        fetchShuffledDeck();
    }

    private void fetchShuffledDeck() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(SHUFFLE_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();

            Card[] cardsArray = mapper.readValue(response.body(), Card[].class);


            cards.addAll(List.of(cardsArray));


        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch shuffled deck", e);
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}