package com.frijolie.cards.blackjack.model.cards;

import com.frijolie.cards.blackjack.model.game.GameRules;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * BlackjackHand is a concrete class used to represent a Players
 *  {@link Hand} in the game of Blackjack. This class will provide behavior
 * for all {@link Card}s in the Hand.
 *
 * <p>A {@code BlackjackHand} contains two or more cards.  These Cards are dealt
 * by the Dealer from a {@link Shoe} and are placed in the hand.
 *
 * <p>Every Hand will have a cumulative value, a {@link HandResult}, and other
 * various states and behaviors. The values of these states will be dependent on
 * the strength of the cards in the Hand.
 *
 * @see Hand
 * @see Card
 * @see Shoe
 * @see HandResult
 */
public class BlackjackHand implements Hand, HandPermissions {

  private final List<Card> hand;
  private int score;
  private boolean canSplit;
  private boolean canDouble;
  private boolean canHit;
  private boolean isBust;
  private boolean isSoft;
  private boolean hasBlackjack;
  private HandResult handResult;
  private HandState handState;

  /**
   * No-arg constructor. Sets variable defaults.
   */
  public BlackjackHand() {
    hand = new ArrayList<>();
    handResult = HandResult.TBD;
    handState = HandState.ACTIVE;
  }

  @Override
  public final void addCard(Card card) {
    String message = "The card could not be added to the hand. It is null.";
    Objects.requireNonNull(card, message);
    hand.add(card);
    update();
  }

  @Override
  public final int getScore() {
    return score;
  }

  /**
   * Calculates the highest possible score of all cards in the Hand. After the
   * calculation has been made the value is set in {@link #score}.
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
    score = highScore;
  }

  @Override
  public final boolean isBust() {
    return isBust;
  }

  /**
   * Calculates the value of {@link #isBust}. This is determined by the score of
   * the hand. Will be {@code true} if the score is greater than 21. After the
   * calculation has been made, the value is set in isBust.
   */
  private void calculateIsBust() {
    isBust = score > GameRules.MAX_SCORE;
  }

  @Override
  public final boolean isSoft() {
    return isSoft;
  }

  /**
   * Calculates the value of {@link #isSoft}. Will be {@code true} if the hand has
   * exactly two cards and has contains one or two Aces. After the calculation has
   * been made, the value is set in isSoft.
   */
  private void calculateIsSoft() {
    isSoft = hasTwoCards() && numberOfAces() <= 2;
  }

  @Override
  public final boolean hasBlackjack() {
    return hasBlackjack;
  }

  /**
   * Calculates if the hand has blackjack. This will be {@code true} if the hand
   * has exactly two cards and their score equals 21. After the calculation has
   * been made, the value is set in {@link #hasBlackjack}.
   */
  private void calculateHasBlackjack() {
    hasBlackjack = hasTwoCards() && score == GameRules.MAX_SCORE;
  }

  @Override
  public final List<Card> getCards() {
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
  public final void setHandState(HandState state) {
    handState = state;
    if (state == HandState.INACTIVE) {
      canHit = false;
      canDouble = false;
    }
  }

  @Override
  public final HandState getHandState() {
    return handState;
  }

  /**
   * Returns the count of Aces in the Hand.
   *
   * @return a count of all Aces currently in the Hand.
   */
  final int numberOfAces() {
    return (int) hand.stream()
      .filter(card -> card.getRank() == Rank.ACE)
      .count();
  }

  @Override
  public final boolean canSplit() {
    return canSplit;
  }

  /**
   * Calculates the value of {@link #canSplit}. A hand may be split if the hand
   * has exactly two cards and they are of the same {@link Rank}. After the
   * calculation has been made, the value is set.
   */
  private void calculateCanSplit() {
    if (GameRules.SPLITS_ALLOWED && hasTwoCards()) {
      Card firstCard = getCards().get(0);
      Card secondCard = getCards().get(1);

      if (GameRules.SPLIT_ON_VALUE) {
        canSplit = firstCard.sameValue(secondCard);
      } else {
        canSplit = firstCard.sameRank(secondCard);
      }
    } else {
      canSplit = false;
    }
  }

  @Override
  public final boolean canDouble() {
    return canDouble;
  }

  /**
   * Calculates the value of {@link #canDouble}. A player may double down if the
   * hand has exactly two cards and the score is 9, 10, or 11 points. After the
   * calculation has been made, the value is set in {@link #canDouble}.
   */
  private void calculateCanDouble() {
    if (GameRules.DOUBLE_DOWN_ALLOWED && hasTwoCards()) {
      canDouble = score > 8 && score < 12;
    } else {
      canDouble = false;
    }
  }

  /**
   * Makes several calls to recalculate the various states of the hand. Should be
   * called each time a card has been added.
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
    return canHit;
  }

  /**
   * Calculates if the hand can allow additional cards to be added. This is
   * determined to be {@code true} as long as the {@code score} is less than 21.
   * After the calculation has been made, the value is set in {@link #canHit}.
   */
  private void calculateCanHit() {
    canHit = score < GameRules.MAX_SCORE;
  }

  /**
   * Returns a string representation of all cards in the hand. This method
   * utilizes {@link BlackjackCard#toString()} to display the value of each
   * individual card. The cards are then separated by a space.
   *
   * @return a String representation of all cards in the Hand.
   */
  @Override
  public final String toString() {
    return hand
        .stream()
        .map(Object::toString)
        .collect(Collectors.joining(" "));
  }

  @Override
  public final boolean hasTwoCards() {
    return hand.size() == 2;
  }
}