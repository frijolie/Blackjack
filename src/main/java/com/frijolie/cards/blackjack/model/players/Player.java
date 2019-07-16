package com.frijolie.cards.blackjack.model.players;

import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.game.Game;

/**
 * An interface to ensure behavior of all players in the game. This includes the human player and
 * the dealer. All players in the game should implement this interface.
 */
public interface Player {

  /** Allows the Player to draw a card. */
  void hit();

  /**
   * This effectively will end the Player turn. No additional moves can be made after a player
   * stands.
   */
  void stand();

  /**
   * Returns a reference to the player {@link Hand}.
   *
   * @return the players Hand
   */
  Hand getHand();

  /**
   * Returns {@code true} if the player can take an action. For example, Hit.
   *
   * @return {@code true} if the player is active.
   */
  boolean isActive();

  /**
   * Injects a game reference for the player.
   *
   * @param game to inject
   */
  void joinGame(Game game);
}
