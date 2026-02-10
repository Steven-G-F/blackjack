package com.example.blackjack.model;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Object> cards = new ArrayList<>();

    public Deck() {
        for (int i = 0; i < 52; i++) {
            cards.add(new Object());
        }
    }

    public int size() {
        return cards.size();
    }

    public void deal() {
        cards.remove(cards.size() - 1);
    }
}
