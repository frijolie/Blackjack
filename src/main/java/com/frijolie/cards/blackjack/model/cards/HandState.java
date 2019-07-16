package com.frijolie.cards.blackjack.model.cards;

import com.frijolie.cards.blackjack.model.players.BlackjackPlayer;

/**
 * HandState is an enumeration of all possible states a Hand can be in. It will always be active
 * unless it is BUST, has BLACKJACK, or the player elects to stand
 *
 * @see Hand
 * @see HandResult
 * @see BlackjackPlayer#stand
 */
public enum HandState {
  ACTIVE,
  INACTIVE
}
