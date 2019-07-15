package com.frijolie.cards.blackjack.model.cards;

import java.util.List;
import java.util.Objects;

/**
 * Hand is an interface to ensure behavior of all hands in the game.
 *
 * <p>Each player in the game will need to implement this interface
 *
 * @see Card
 */
public interface Hand extends Comparable<Hand> {

  /**
   * Adds a {@link Card} to the Hand. The argument must not be {@code null}.
   * @param card to add to the Hand
   */
  void addCard(Card card);

  /**
   * Returns the sum of all card values in the Hand.
   * @return the point total of the hand.
   */
  int getScore();

  /**
   * Returns {@code true} if the total score exceeds 21.
   * @return {@code true} if the hand isBust, otherwise {@code false}
   */
  boolean isBust();

  /**
   * Returns {@code true} if the hand contains two cards, one of which is an
   * Ace, and the hand value does not exceed 21.
   * @return {@code true} if the hand is soft, otherwise {@code false}.
   */
  boolean isSoft();

  /**
   * Returns {@code true} if the hand contains 2 cards and the score equals 21.
   * @return {@code true} if the Hand has blackjack, otherwise {@code false}
   */
  boolean hasBlackjack();

  /**
   * This will return {@code true} if the hand has exactly two cards.
   * @return {@code true} if size is equal to two
   */
  boolean hasTwoCards();

  /**
   * Returns a collection of all cards in the Hand.
   * @return a collection of cards.
   */
  List<Card> getCards();

  /**
   * Returns the result of the Hand at the end of the round. For example, WIN.
   */
  HandResult getHandResult();

  /**
   * Allows the result of the hand, at the end of the round, to be set. For
   * example, HandResult.WIN
   * @param result to bet set
   */
  void setHandResult(HandResult result);

  /**
   * This allows the handstate to be set. For example, HandState.ACTIVE
   * @param state to set
   */
  void setHandState(HandState state);

  /**
   * Returns the value of handState. Valid values are: ACTIVE and INACTIVE
   * @return the value of handState
   */
  HandState getHandState();

  @Override
  default int compareTo(Hand hand) {
    var nullError = "You must pass a non-null Hand for comparison";
    Objects.requireNonNull(hand, nullError);
    return getScore() - hand.getScore();
  }
}