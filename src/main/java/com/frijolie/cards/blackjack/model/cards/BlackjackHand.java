package com.frijolie.cards.blackjack.model.cards;

import com.frijolie.cards.blackjack.model.game.GameRules;

import java.util.Objects;
import java.util.stream.Collectors;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

/**
 * BlackjackHand is a concrete class used to represent a Players {@link Hand} in the game of
 * Blackjack. This class will provide behavior for all {@link Card}s in the Hand.
 *
 * <p>A {@code BlackjackHand} contains two or more cards. These Cards are dealt by the Dealer from a
 * {@link Shoe} and are placed in the hand.
 *
 * <p>Every Hand will have a cumulative value, a {@link HandResult}, and other various states and
 * behaviors. The values of these states will be dependent on the strength of the cards in the Hand.
 *
 * @see Hand
 * @see Card
 * @see Shoe
 * @see HandResult
 */
public class BlackjackHand implements Hand, HandPermissions {

  private ObservableList<Card> hand;
  private IntegerProperty score;
  private BooleanProperty canSplit;
  private BooleanProperty canDouble;
  private BooleanProperty canHit;
  private BooleanProperty isBust;
  private BooleanProperty isSoft;
  private BooleanProperty hasBlackjack;
  private HandResult handResult;
  private HandState handState;

  /** No-arg constructor. Sets variable defaults. */
  public BlackjackHand() {
    hand = FXCollections.observableArrayList();
    score = new SimpleIntegerProperty(0);
    canSplit = new SimpleBooleanProperty(false);
    canDouble = new SimpleBooleanProperty(false);
    canHit = new SimpleBooleanProperty(false);
    isBust = new SimpleBooleanProperty(false);
    isSoft = new SimpleBooleanProperty(false);
    hasBlackjack = new SimpleBooleanProperty(false);
    handResult = HandResult.TBD;
    handState = HandState.ACTIVE;
    hand.addListener((ListChangeListener<? super Card>) this::onChanged);
    isBustProperty().addListener(this::onBust);
    hasBlackjackProperty().addListener(this::onBlackjack);
  }

  @Override
  public final void addCard(Card card) {
    String message = "The card could not be added to the hand. It is null.";
    Objects.requireNonNull(card, message);
    hand.add(card);
  }

  @Override
  public final int getScore() {
    return score.get();
  }

  /**
   * Returns the IntegerProperty of score
   *
   * @return an IntegerProperty
   */
  public final IntegerProperty scoreProperty() {
    return score;
  }

  /**
   * Calculates the highest possible score of all cards in the Hand. After the calculation has been
   * made the value is set in {@link #score}.
   *
   * <p>It adjusts for Aces being 1 or 11 points.
   */
  private void calculateScore() {
    /*
     determine the maximum total value of all cards in the hand.
    */
    int highScore = hand.stream().mapToInt(Card::getValue).sum();

    /*
     need to know how many aces are in the hand. Ace may equal 1 or 11 pts.
    */
    int numberOfAces = numberOfAces();

    /*
     decrement the value of an Ace if it would cause BUST. It is possible to have
     more than one Ace in the Hand.
    */
    while (highScore > GameRules.MAX_SCORE && numberOfAces > 0) {
      /*
       reduce the value of an Ace by 10 points. An Ace is now worth 1 pt.
      */
      highScore -= 10;
      numberOfAces--;
    }
    score.set(highScore);
  }

  @Override
  public final boolean isBust() {
    return isBust.get();
  }

  /**
   * Returns the BooleanProperty of isBust
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty isBustProperty() {
    return isBust;
  }

  /**
   * Calculates the value of {@link #isBust}. This is determined by the score of the hand. Will be
   * {@code true} if the score is greater than 21. After the calculation has been made, the value is
   * set in isBust.
   */
  private void calculateIsBust() {
    isBust.set(getScore() > GameRules.MAX_SCORE);
  }

  @Override
  public final boolean isSoft() {
    return isSoft.get();
  }

  /**
   * Returns the BooleanProperty of isSoft
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty isSoftProperty() {
    return isSoft;
  }

  /**
   * Calculates the value of {@link #isSoft}. Will be {@code true} if the hand has exactly two cards
   * and has contains one or two Aces. After the calculation has been made, the value is set in
   * isSoft.
   */
  private void calculateIsSoft() {
    isSoft.set(hasTwoCards() && numberOfAces() <= 2);
  }

  @Override
  public final boolean hasBlackjack() {
    return hasBlackjack.get();
  }

  /**
   * Returns the BooleanProperty of hasBlackjack
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty hasBlackjackProperty() {
    return hasBlackjack;
  }

  /**
   * Calculates if the hand has blackjack. This will be {@code true} if the hand has exactly two
   * cards and their score equals 21. After the calculation has been made, the value is set in
   * {@link #hasBlackjack}.
   */
  private void calculateHasBlackjack() {
    hasBlackjack.set(hasTwoCards() && getScore() == GameRules.MAX_SCORE);
  }

  @Override
  public final ObservableList<Card> getCards() {
    return hand;
  }

  @Override
  public final HandResult getHandResult() {
    return handResult;
  }

  @Override
  public final void setHandResult(HandResult result) {
    handResult = result;
  }

  @Override
  public final HandState getHandState() {
    return handState;
  }

  @Override
  public final void setHandState(HandState state) {
    handState = state;
    if (state == HandState.INACTIVE) {
      canHit.set(false);
      canDouble.set(false);
    }
  }

  /**
   * Returns the count of Aces in the Hand.
   *
   * @return a count of all Aces currently in the Hand.
   */
  final int numberOfAces() {
    return (int) hand.stream().filter(card -> card.getRank() == Rank.ACE).count();
  }

  @Override
  public final boolean canSplit() {
    return canSplit.get();
  }

  /**
   * Returns the BooleanProperty of canSplit
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty canSplitProperty() {
    return canSplit;
  }

  /**
   * Calculates the value of {@link #canSplit}. A hand may be split if the hand has exactly two
   * cards and they are of the same {@link Rank}. After the calculation has been made, the value is
   * set.
   */
  private void calculateCanSplit() {
    if (GameRules.SPLITS_ALLOWED && hasTwoCards()) {
      Card firstCard = getCards().get(0);
      Card secondCard = getCards().get(1);

      if (GameRules.SPLIT_ON_VALUE) {
        canSplit.set(firstCard.sameValue(secondCard));
      } else {
        canSplit.set(firstCard.sameRank(secondCard));
      }
    } else {
      canSplit.set(false);
    }
  }

  @Override
  public final boolean canDouble() {
    return canDouble.get();
  }

  /**
   * Returns the BooleanProperty of canDouble
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty canDoubleProperty() {
    return canDouble;
  }

  /**
   * Calculates the value of {@link #canDouble}. A player may double down if the hand has exactly
   * two cards and the score is 9, 10, or 11 points. After the calculation has been made, the value
   * is set in {@link #canDouble}.
   */
  private void calculateCanDouble() {
    if (GameRules.DOUBLE_DOWN_ALLOWED && hasTwoCards()) {
      canDouble.set(getScore() > 8 && getScore() < 12);
    } else {
      canDouble.set(false);
    }
  }

  /**
   * Makes several calls to recalculate the various states of the hand. Should be called each time a
   * card has been added.
   */
  private void update() {
    calculateScore();
    calculateCanSplit();
    calculateCanDouble();
    calculateCanHit();
    calculateHasBlackjack();
    calculateIsSoft();
    calculateIsBust();
  }

  @Override
  public final boolean canHit() {
    return canHit.get();
  }

  /**
   * Returns the BooleanProperty of canHit
   *
   * @return a BooleanProperty
   */
  public final BooleanProperty canHitProperty() {
    return canHit;
  }

  /**
   * Calculates if the hand can allow additional cards to be added. This is determined to be {@code
   * true} as long as the {@code score} is less than 21. After the calculation has been made, the
   * value is set in {@link #canHit}.
   */
  private void calculateCanHit() {
    canHit.set(getScore() < GameRules.MAX_SCORE);
  }

  /**
   * Returns a string representation of all cards in the hand. This method utilizes {@link
   * BlackjackCard#toString()} to display the value of each individual card. The cards are then
   * separated by a space.
   *
   * @return a String representation of all cards in the Hand.
   */
  @Override
  public final String toString() {
    return hand.stream().map(Object::toString).collect(Collectors.joining(" "));
  }

  @Override
  public final boolean hasTwoCards() {
    return hand.size() == 2;
  }

  /**
   * Will trigger a call to {@link #update()} anytime a card is added to the hand
   * @param c the change that was made to the collection
   */
  private void onChanged(ListChangeListener.Change c) {
    while (c.next()) {
      if (c.wasAdded()) {
        update();
      }
    }
  }

  /**
   * Will trigger the {@link HandState} to become inactive if the player isBust.
   * @param observable the object that triggered the change
   * @param oldValue the previous value
   * @param isBust the new value
   */
  private void onBust(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean isBust) {
    if (isBust) {
      setHandState(HandState.INACTIVE);
    }
  }

  /**
   * Will trigger the {@link HandState} to be come inactive if the player has Blackjack
   * @param observable the object which was modified
   * @param oldValue the previous value
   * @param hasBlackjack the new value
   */
  private void onBlackjack(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean hasBlackjack) {
    if (hasBlackjack) {
      setHandState(HandState.INACTIVE);
    }
  }
}
