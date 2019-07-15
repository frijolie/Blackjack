package com.frijolie.cards.blackjack.model.players;

/**
 * This interface ensures behavior only applicable to those players whom may
 * place wagers (bets) in the game. This would only apply to the Human players
 * and not the dealer.
 *
 * <p>I am aware of BigDecimals in Java. However, I'm less concerned with the
 * precision of currency math in this simple game. Perhaps I'll enhance this to
 * include BigDecimal math a later date.
 */
interface BetActions {

  /**
   * Returns the total value of the players cash-on-hand. This money will be
   * used to place wagers on individual hands.
   * @return the total value of cash-on-hand.
   */
  double getCash();

  /**
   * Returns {@code true} if the player has zero or less available cash-on-hand.
   * @return {@code true} if the player is bankrupt.
   */
  boolean isBankrupt();

  /**
   * Allows cash to be added to the available cash-on-hand.
   * @param amount to be added
   */
  void addCash(double amount);

  /**
   * Allows cash to be removed from the available cash-on-hand.
   * @param amount to remove
   */
  void removeCash(double amount);
}