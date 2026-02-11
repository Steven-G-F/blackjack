package com.example.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void newHandStartsEmpty() {
        Hand hand = new Hand();
        assertEquals(0, hand.size());
    }

    @Test
    void addingACardIncreasesHandSize() {
        Hand hand = new Hand();
        hand.add(new Card(Rank.TWO, Suit.HEARTS));
        assertEquals(1, hand.size());
    }
}
