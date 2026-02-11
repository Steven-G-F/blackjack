package com.example.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> cards = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card deal() {
        return cards.remove(cards.size() - 1);
    }
}
