package com.frijolie.cards.blackjack.model.cards;

import java.util.Objects;

/**
 * BlackjackCard is a concrete class used to model a single playing card in the game of Blackjack.
 *
 * <p>Each card will have a {@link Rank} and {@link Suit}. Along with the card {@code Rank} is a
 * numerical value. This value is used to calculate a sum of all cards in the players possession.
 * This sum is then used to determine a winner of the game.
 *
 * @see Card
 * @see Rank
 * @see Suit
 */
public class BlackjackCard implements Card {

  private final Rank rank;
  private final Suit suit;

  /**
   * Constructor. Must supply a {@link Rank} and {@link Suit}.
   *
   * @param rank the cards {@code Rank}
   * @param suit the cards {@code Suit}
   */
  public BlackjackCard(final Rank rank, final Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }

  @Override
  public final Rank getRank() {
    return rank;
  }

  @Override
  public final Suit getSuit() {
    return suit;
  }

  @Override
  public final int getValue() {
    return rank.getValue();
  }

  /**
   * Generates then returns the hash of the cards {@link Rank} and {@link Suit}.
   *
   * @return an int value which is comprised of the hash of Rank and Suit.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getRank(), getSuit());
  }

  /**
   * Returns a String to represent the card Rank and Suit. The string will be in the format of
   * Rank.letter and Suit.letter. For example, "5H" for Five of Hearts
   *
   * @return a string representing the Rank and Suit of the Card.
   */
  @Override
  public String toString() {
    return String.format("%s%s", getRank().getLetter(), getSuit().getLetter());
  }

  /**
   * Compares this card to the Object {@code param}. Will return {@code true} if both objects are
   * equal.
   *
   * <p>To make the comparison, a call is made to {@link #compareTo(Card)}. That method first
   * compares {@link Rank} then {@link Suit}. If both are equal the {@code Card} is then considered
   * to be equal.
   *
   * @param o the object to be compared with {@code this}
   * @return {@code true} if both objects in the comparison are equal
   * @throws NullPointerException if the Object param is {@code null}
   */
  @Override
  public boolean equals(Object o) {
    String nullError = "You must pass a non-null card for comparison";
    Objects.requireNonNull(o, nullError);

    if (this == o) {
      return true;
    }

    if (getClass() != o.getClass()) {
      return false;
    }

    Card card = (Card) o;
    return compareTo(card) == 0;
  }
}
