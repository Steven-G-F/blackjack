package com.example.blackjack.game;

import org.springframework.stereotype.Component;

import com.example.blackjack.rules.DealerRules;

import com.example.blackjack.rules.PlayerRules;

import com.example.blackjack.model.Hand;
import com.example.blackjack.cli.GameIO;
import com.example.blackjack.model.Deck;


@Component
public class BlackjackGame {

    private final GameIO io;

    public BlackjackGame(GameIO io) {
        this.io = io;
    }

    public void play() {
        Deck deck = new Deck();

        Hand playerHand = new Hand();
        Hand dealerHand = new Hand();

        PlayerRules playerRules = new PlayerRules();
        DealerRules dealerRules = new DealerRules();

        // initial deal
        playerHand.add(deck.deal());
        playerHand.add(deck.deal());

        dealerHand.add(deck.deal());
        dealerHand.add(deck.deal());

        io.println("Welcome to Blackjack!");
        io.println("");

        // PLAYER TURN
        while (true) {
            io.println("Your hand: " + playerHand.getHand()
                    + " (score: " + playerRules.score(playerHand) + ")");
            io.println("Dealer shows: " + dealerHand.getHand().get(0));
            io.println("(H)it or (S)tand?");

            String input = io.readLine().trim().toUpperCase();

            if (input.equals("H")) {
                playerHand.add(deck.deal());

                if (playerRules.isBusted(playerHand)) {
                    io.println("You busted!");
                    return;
                }
            } else if (input.equals("S")) {
                break;
            } else {
                io.println("Invalid input. Please enter H or S.");
            }
        }

        // DEALER TURN
        io.println("");
        io.println("Dealer's turn...");
        io.println("Dealer hand: " + dealerHand.getHand()
                + " (score: " + dealerRules.score(dealerHand) + ")");

        while (dealerRules.shouldHit(dealerHand)) {
            io.println("Dealer hits.");
            dealerHand.add(deck.deal());
            io.println("Dealer hand: " + dealerHand.getHand()
                    + " (score: " + dealerRules.score(dealerHand) + ")");
        }

        if (dealerRules.isBusted(dealerHand)) {
            io.println("Dealer busted! You win!");
            return;
        }

        int playerScore = playerRules.score(playerHand);
        int dealerScore = dealerRules.score(dealerHand);

        io.println("");
        io.println("Final hands:");
        io.println("Your hand: " + playerHand.getHand()
                + " (score: " + playerScore + ")");
        io.println("Dealer hand: " + dealerHand.getHand()
                + " (score: " + dealerScore + ")");

        if (playerScore > dealerScore) {
            io.println("You win!");
        } else if (playerScore < dealerScore) {
            io.println("Dealer wins!");
        } else {
            io.println("Push (tie).");
        }


    }

}
