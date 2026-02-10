package com.example.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeckTest {

    @Test
    void dealingACardReducesDeckSizeByOne() {
    Deck deck = new Deck();
    int startingSize = deck.size();

    deck.deal();

    assertEquals(startingSize - 1, deck.size());
}

}
