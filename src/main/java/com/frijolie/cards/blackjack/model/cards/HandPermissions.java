package com.frijolie.cards.blackjack.model.cards;

/**
 * An interface to enforce behavior of all human controlled hands in the game.
 * Not all of these methods will be available to the dealer and therefore only
 * applicable to a Human player.
 *
 * @see BlackjackHand
 */
public interface HandPermissions {

  /**
   * Returns {@code true} if the player can double down on the hand.
   * @return {@code true} if can doubleDown, otherwise {@code false}
   */
  boolean canDouble();

  /**
   * Returns {@code true} if the player can split the hand.
   * @return {@code true} if the hand can be split, otherwise {@code false}
   */
  boolean canSplit();

  /**
   * Returns {@code true} if the player can hit (draw an additional card).
   * @return {@code true} if canHit. Otherwise, {@code false}
   */
  boolean canHit();
}