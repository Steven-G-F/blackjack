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

    // TODO Break out some of this logic to helper functions/services
    public void play() {

        Deck deck = new Deck();
        PlayerRules playerRules = new PlayerRules();
        DealerRules dealerRules = new DealerRules();

        int stack = 100;

        io.println("Welcome to Blackjack!");
        io.println("");

        while (stack > 0) {

            io.println("==================================");
            io.println("Your stack: " + stack);

            Hand playerHand = new Hand();
            Hand dealerHand = new Hand();

            // Initial deal
            playerHand.add(deck.deal());
            playerHand.add(deck.deal());
            dealerHand.add(deck.deal());
            dealerHand.add(deck.deal());

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
                        io.println("You busted! Dealer wins.");
                        stack--;
                        break;
                    }

                } else if (input.equals("S")) {
                    break;
                } else {
                    io.println("Invalid input.");
                }
            }

            // If player busted, skip dealer + showdown
            if (playerRules.isBusted(playerHand)) {
                deck.addToDiscard(playerHand.getHand());
                deck.addToDiscard(dealerHand.getHand());
                continue;
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

            // Dealer bust
            if (dealerRules.isBusted(dealerHand)) {
                io.println("Dealer busted! You win!");
                stack++;
            } else {
                // SHOWDOWN
                int playerScore = playerRules.score(playerHand);
                int dealerScore = dealerRules.score(dealerHand);

                io.println("");
                io.println("Final hands:");
                io.println("Your hand: " + playerHand.getHand()
                        + " (score: " + playerScore + ")");
                io.println("Dealer hand: " + dealerHand.getHand()
                        + " (score: " + dealerScore + ")");

                if (playerScore > dealerScore) {
                    stack++;
                    io.println("You win!");
                } else if (playerScore < dealerScore) {
                    stack--;
                    io.println("Dealer wins!");
                } else {
                    io.println("Push (tie).");
                }
            }

            // Discard round cards
            deck.addToDiscard(playerHand.getHand());
            deck.addToDiscard(dealerHand.getHand());
        }

        io.println("");
        io.println("Game over. You are out of chips.");
    }


}
