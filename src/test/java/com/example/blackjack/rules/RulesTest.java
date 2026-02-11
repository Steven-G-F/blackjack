package com.example.blackjack.rules;

import com.example.blackjack.model.Card;
import com.example.blackjack.model.Hand;
import com.example.blackjack.model.Rank;
import com.example.blackjack.model.Suit;
import com.example.blackjack.rules.PlayerRules;
import com.example.blackjack.rules.DealerRules;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {

    @Test
    void playerHandScoresCorrectlyAndBustsOver21() {
        PlayerRules rules = new PlayerRules();
        Hand hand = new Hand();

        hand.add(new Card(Rank.TEN, Suit.HEARTS));
        hand.add(new Card(Rank.KING, Suit.SPADES));
        hand.add(new Card(Rank.FIVE, Suit.CLUBS));

        assertEquals(25, rules.score(hand));
        assertTrue(rules.isBusted(hand));
    }

    @Test
    void dealerHitsBelow17AndStandsAt17OrMore() {
        DealerRules rules = new DealerRules();
        Hand hand = new Hand();

        hand.add(new Card(Rank.TEN, Suit.HEARTS));
        hand.add(new Card(Rank.SIX, Suit.DIAMONDS));

        assertTrue(rules.shouldHit(hand));

        hand.add(new Card(Rank.ACE, Suit.SPADES));

        assertFalse(rules.shouldHit(hand));
    }
}
