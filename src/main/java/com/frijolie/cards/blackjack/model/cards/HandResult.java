package com.frijolie.cards.blackjack.model.cards;

public enum HandResult {

  /**
   * When the player has two cards and a score of 21 points. Also known as a
   * "Natural".
   */
  BLACKJACK,

  /**
   * When the Hand score exceeds 21 points.
   */
  BUST,

  /**
   * When the score does not exceed 21, but is less than the dealer score.
   */
  LOSE,

  /**
   * When the score is equal to the dealer score.
   */
  PUSH,

  /**
   * When the score is less than 21 and greater than the dealer score.
   */
  WIN,

  /**
   * The default value. This is assigned until the round is over and a result
   * has been determined.
   */
  TBD
}