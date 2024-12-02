package no.blackjack;

import no.blackjack.service.BlackjackGame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BlackjackApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BlackjackApplication.class, args);

		BlackjackGame game = new BlackjackGame("John");
		game.play();

	}
}