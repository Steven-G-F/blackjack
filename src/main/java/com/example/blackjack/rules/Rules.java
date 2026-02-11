package com.example.blackjack.rules;

import com.example.blackjack.model.Hand;

public interface Rules{
    int score(Hand hand);
    boolean isBusted(Hand hand);
}