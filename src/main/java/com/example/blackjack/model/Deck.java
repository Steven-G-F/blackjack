package com.example.blackjack.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deckCards = new ArrayList<>();
    private final List<Card> discardPile = new ArrayList<>();

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deckCards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(deckCards);
    }

    public int size() {
        return deckCards.size();
    }

    public Card deal() {
        if (deckCards.size() == 0){
            Collections.shuffle(discardPile);
            deckCards.addAll(discardPile);
            discardPile.clear();
        }
        return deckCards.remove(deckCards.size() - 1);
    }

    public void addToDiscard(List<Card> discardedCards){
        discardPile.addAll(discardedCards);
    }
}
