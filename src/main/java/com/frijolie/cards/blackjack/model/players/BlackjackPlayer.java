package com.frijolie.cards.blackjack.model.players;

import com.frijolie.cards.blackjack.model.cards.BlackjackHand;
import com.frijolie.cards.blackjack.model.cards.Card;
import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.cards.HandState;
import com.frijolie.cards.blackjack.model.game.Game;
import com.frijolie.cards.blackjack.model.game.GameRules;

import java.math.BigDecimal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * A concrete class used to model a human player in the game.
 *
 * <p>The BlackjackPlayer should control a {@link Hand} which contains various {@link Card}s. They
 * may continue playing their turn until their hand {@link Hand#isBust}, the player {@link
 * #isBankrupt}, or they have elected to stand.
 *
 * <p>The player will begin the game with $1,000 cash to spend.
 *
 * @see GameRules#STARTING_CASH
 */
public class BlackjackPlayer implements HumanPlayerActions {

  private final BlackjackHand hand;
  private final BigDecimal cash;
  private BooleanProperty isBankrupt;
  private BooleanProperty isActive;
  private Game game;

  /**
   * Default no-arg constructor.
   */
  public BlackjackPlayer() {
    hand = new BlackjackHand();
    isBankrupt = new SimpleBooleanProperty(false);
    isActive = new SimpleBooleanProperty(true);
    cash = GameRules.STARTING_CASH;
    isActiveProperty().addListener((observable, oldValue, isActive) -> {
      if (!isActive) {
        hand.setHandState(HandState.INACTIVE);
        stand();
      }
    });
    isBankruptProperty().addListener((observable, oldValue, isBankrupt) -> {
      if (isBankrupt) {
        hand.setHandState(HandState.INACTIVE);
        stand();
      }
    });
    hand.hasBlackjackProperty().addListener((observable, oldValue, hasBlackjack) -> {
      if (hasBlackjack) {
        stand();
      }
    });
    hand.isBustProperty().addListener((observable, oldValue, isBust) -> {
      if (isBust) {
        stand();
      }
    });
    hand.canHitProperty().addListener((observable, oldValue, canHit) -> {
      if (!canHit) {
        stand();
      }
    });
  }

  @Override
  public final void doubleDown() {
    String error = "The player must be active to double down";
    if (isActive()) {
      hit();
      stand();
    } else {
      throw new IllegalStateException(error);
    }
  }

  @Override
  public final void surrender() {
    String error = "The player must be active to surrender";
    if (isActive()) {
      stand();
    } else {
      throw new IllegalStateException(error);
    }
  }

  @Override
  public final void buyInsurance() {
    throw new UnsupportedOperationException("unimplemented method");
  }

  @Override
  public final void split() {
    throw new UnsupportedOperationException("unimplemented method");
  }

  @Override
  public final void hit() {
    String error = "The player must be active to draw a card.";
    if (isActive()) {
      hand.addCard(game.getShoe().deal());
    } else {
      throw new IllegalStateException(error);
    }
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
  public final double getCash() {
    return cash.doubleValue();
  }

  @Override
  public final boolean isBankrupt() {
    return isBankrupt.get();
  }

  /**
   * Returns the BooleanProperty of isBankrupt.
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty isBankruptProperty() {
    return isBankrupt;
  }

  @Override
  public final void addCash(double amount) {
    cash.add(convert(amount));
  }

  @Override
  public final void removeCash(double amount) {
    cash.subtract(convert(amount));
    int result = cash.compareTo(BigDecimal.valueOf(0));
    if (result <= 0) {
      isBankrupt.set(true);
      isActive.set(false);
    }
  }

  @Override
  public final void joinGame(Game game) {
    this.game = game;
  }

  /**
   * Converts a double into a BigDecimal. Necessary for precision calculations.
   *
   * @param amount to be converted
   * @return a BigDecimal equal to the param
   */
  private BigDecimal convert(double amount) {
    return BigDecimal.valueOf(amount);
  }
}