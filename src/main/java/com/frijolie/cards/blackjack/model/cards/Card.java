package com.frijolie.cards.blackjack.model.cards;

import static java.util.Comparator.comparing;

import java.util.Objects;

/**
 * This interface is used to ensure behavior of all cards in the application.
 *
 * <p>This ensures that all cards will have a {@link Rank}, {@link Suit}, and be
 * able to be compared with other cards in the collection.
 *
 * <p>All cards used in Blackjack should implement this interface.
 *
 * @see Rank
 * @see Suit
 */
public interface Card extends Comparable<Card> {

  /**
   * Returns the {@link Rank} of the card. For example, Rank.ACE
   * @return the card Rank
   */
  Rank getRank();

  /**
   * Returns the {@link Suit} of the card. For example, Suit.HEARTS
   * @return the card Suit
   */
  Suit getSuit();

  /**
   * Returns the {@code int} value of the card. This is derived from the
   * {@link Rank}
   * @return an {@code int} value
   */
  int getValue();

  /**
   * Returns {@code true} if this card and the one being compared have the same
   * {@link Rank}.
   * @param card the card used in the comparison
   * @return {@code true} if the cards have the same Rank
   * @throws NullPointerException if the card param is null
   */
  default boolean sameRank(Card card) {
    String nullError = "You must pass a non null card for comparison";
    Objects.requireNonNull(card, nullError);
    return comparing(Card::getRank).compare(this, card) == 0;
  }

  /**
   * Returns {@code true} if this card and the one being compared have the same
   * {@link Suit}.
   * @param card the card used in the comparison
   * @return {@code true} if the cards have the same Suit
   * @throws NullPointerException if the card param is null
   */
  default boolean sameValue(Card card) {
    String nullError = "You must pass a non null card for comparison";
    Objects.requireNonNull(card, nullError);
    return comparing(Card::getValue).compare(this, card) == 0;
  }

  /**
   * Compares {@code this} card with another passed as a {@code param}.
   *
   * <p>First, the {@link Rank} ordinals are used to compare cards. If they are
   * equal, it then proceeds to compare {@link Suit} ordinals. After which, it
   * will return the difference.
   * @param card to be used in comparison
   * @return in {@code }
   * @throws NullPointerException if the card param is null
   */
  @Override
  default int compareTo(Card card) {
    String nullError = "You must pass a non-null card for comparison";
    Objects.requireNonNull(card, nullError);
    return comparing(Card::getRank)
      .thenComparing(Card::getSuit)
      .compare(this, card);
  }
}