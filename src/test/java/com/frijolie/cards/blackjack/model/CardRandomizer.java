package com.frijolie.cards.blackjack.model;

import com.frijolie.cards.blackjack.model.cards.BlackjackCard;
import com.frijolie.cards.blackjack.model.cards.Rank;
import com.frijolie.cards.blackjack.model.cards.Suit;

public class CardRandomizer {

  private static Rank getRandomRank() {
    return Rank.values()[(int) (Math.random() * Rank.values().length)];
  }

  private static Suit getRandomSuit() {
    return Suit.values()[(int) (Math.random() * Suit.values().length)];
  }

  public static BlackjackCard getCard(Rank rank) {
    return new BlackjackCard(rank, getRandomSuit());
  }

  public static BlackjackCard getCard(Suit suit) {
    return new BlackjackCard(getRandomRank(), suit);
  }

  public static BlackjackCard getCard() {
    return new BlackjackCard(getRandomRank(), getRandomSuit());
  }
}