package com.frijolie.cards.blackjack.model.players;

import com.frijolie.cards.blackjack.model.cards.BlackjackHand;
import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.cards.HandState;
import com.frijolie.cards.blackjack.model.game.Game;
import com.frijolie.cards.blackjack.model.game.GameRules;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class BlackjackDealer implements Player {

  private final Hand hand;
  private BooleanProperty isActive;
  private Game game;

  /**
   * Default no-arg constructor.
   */
  public BlackjackDealer() {
    hand = new BlackjackHand();
    isActive = new SimpleBooleanProperty(true);
  }

  @Override
  public final void hit() {
    hand.addCard(game.getShoe().deal());
  }

  @Override
  public final void stand() {
    isActive.set(false);
    hand.setHandState(HandState.INACTIVE);
  }

  @Override
  public final Hand getHand() {
    return hand;
  }

  @Override
  public final boolean isActive() {
    return isActive.get();
  }

  /**
   * Returns the BooleanProperty of isActive.
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty isActiveProperty() {
    return isActive;
  }

  @Override
  public final void joinGame(Game game) {
    this.game = game;
  }

  /**
   * Contains the logic for how the Dealer takes their turn. According to the rules of Blackjack,
   * the dealer must hit any score below 17. Also, if {@link GameRules#DEALER_HITS_SOFT_17} is
   * {@code true} the dealer may also have to {@link #hit()}. Otherwise, the dealer will {@link
   * #stand()}
   */
  public final void takeTurn() {
    int score = hand.getScore();
    if (isActive()) {
      while (score < 18) {
        if (score == 17) {
          if (GameRules.DEALER_HITS_SOFT_17 && hand.isSoft()) {
            hit();
          } else {
            break;
          }
        } else {
          hit();
        }
        score = hand.getScore();
      }
      stand();
    }
  }
}
