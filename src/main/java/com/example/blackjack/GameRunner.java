package com.example.blackjack;

import com.example.blackjack.model.Deck;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GameRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        Deck deck = new Deck();
        System.out.println("Deck size: " + deck.size());
    }
}
