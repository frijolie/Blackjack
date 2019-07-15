package com.frijolie.cards.blackjack.model.players;

import com.frijolie.cards.blackjack.model.cards.BlackjackHand;
import com.frijolie.cards.blackjack.model.cards.Card;
import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.cards.HandState;
import com.frijolie.cards.blackjack.model.game.Game;
import com.frijolie.cards.blackjack.model.game.GameRules;

import java.math.BigDecimal;

/**
 * A concrete class used to model a human player in the game.
 *
 * <p>The BlackjackPlayer should control a {@link Hand} which contains various
 * {@link Card}s. They may continue playing their turn until their hand
 * {@link Hand#isBust}, the player {@link #isBankrupt}, or they have elected to
 *  stand.
 *
 * <p>The player will begin the game with $1,000 cash to spend.
 *
 * @see GameRules#STARTING_CASH
 */
public class BlackjackPlayer implements HumanPlayerActions {

  private final Hand hand;
  private boolean isBankrupt;
  private boolean isActive;
  private final BigDecimal cash;
  private Game game;

  /**
   * Default no-arg constructor.
   */
  public BlackjackPlayer() {
    hand = new BlackjackHand();
    isBankrupt = false;
    isActive = true;
    cash = GameRules.STARTING_CASH;
  }

  @Override
  public final void doubleDown() {
    hit();
    stand();
  }

  @Override
  public final void surrender() {
    stand();
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
  public final double getCash() {
    return cash.doubleValue();
  }

  @Override
  public final boolean isBankrupt() {
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
      isBankrupt = true;
      isActive = false;
    }
  }

  @Override
  public final void joinGame(Game game) {
    this.game = game;
  }

  /**
   * Converts a double into a BigDecimal. Necessary for precision calculations.
   * @param amount to be converted
   * @return a BigDecimal equal to the param
   */
  private BigDecimal convert(double amount) {
    return BigDecimal.valueOf(amount);
  }
}