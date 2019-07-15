package com.frijolie.cards.blackjack.model.players;

import com.frijolie.cards.blackjack.model.cards.BlackjackHand;
import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.cards.HandState;
import com.frijolie.cards.blackjack.model.game.Game;

public class BlackjackDealer implements Player {

  private final Hand hand;
  private boolean isActive;
  private Game game;

  /**
   * Default no-arg constructor.
   */
  public BlackjackDealer() {
    hand = new BlackjackHand();
    isActive = true;
  }

  @Override
  public final void hit() {
    hand.addCard(game.getShoe().deal());
  }

  @Override
  public final void stand() {
    isActive = false;
    hand.setHandState(HandState.INACTIVE);
  }

  @Override
  public final Hand getHand() {
    return hand;
  }

  @Override
  public final boolean isActive() {
    return isActive;
  }

  @Override
  public final void joinGame(Game game) {
    this.game = game;
  }
}