package com.example.blackjack;

import com.example.blackjack.game.BlackjackGame;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameRunner implements CommandLineRunner {

    private final BlackjackGame game;

    public GameRunner(BlackjackGame game) {
        this.game = game;
    }

    @Override
    public void run(String... args) {
        game.play();
    }
}
