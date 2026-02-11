package com.example.blackjack.rules;

import com.example.blackjack.model.Card;
import com.example.blackjack.model.Hand;

public class DealerRules implements Rules{
    @Override
    public int score(Hand hand){
        int total = 0;
        int aceCount = 0;

        for (Card card : hand.getHand()) {
            int value = card.rank().value();
            total += value;
            if (value == 11) aceCount++;
        }

        while (total > 21 && aceCount-- > 0) {
            total -= 10;
        }   

        return total;
    }

    @Override
    public boolean isBusted(Hand hand) { 
        return score(hand) > 21;
    }

    public boolean shouldHit(Hand hand){
        return score(hand) < 17;
    }
}
