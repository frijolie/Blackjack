package com.frijolie.cards.blackjack.model.game;

import com.frijolie.cards.blackjack.model.cards.Shoe;
import com.frijolie.cards.blackjack.model.players.BlackjackDealer;
import com.frijolie.cards.blackjack.model.players.BlackjackPlayer;
import com.frijolie.cards.blackjack.model.players.Player;

/**
 * An interface to ensure certain behavior in all concrete game classes in the
 * application.
 */
public interface Game {

  /**
   * Calculates the winner of the round. The winner is determined by the player
   * that has the hand with the closest value to 21 without going over.
   */
  void determineWinner();

  /**
   * The main method of the game. This is where the round logic should be. It
   * will check for proper amount of players, deal the cards, and know how to
   * cycle the turns between the human players and the dealer.
   *
   * <p>This method would need to be called at the start of each round.
   */
  void playGame();

  /**
   * This method clears both the dealer and player hands, resets the variables
   * and states, and makes a call to playGame after all things have been reset.
   *
   * <p>This method would need to be called at the end of a round.
   */
  void newGame();

  /**
   * Returns a reference to the human player in the game.
   * @return the {@link BlackjackPlayer}
   */
  Player getPlayer();

  /**
   * Returns a reference to the dealer in the game.
   * @return the {@link BlackjackDealer}
   */
  Player getDealer();

  /**
   * Returns a reference to the {@link Shoe} in the game.
   * @return a Shoe reference
   */
  Shoe getShoe();
}