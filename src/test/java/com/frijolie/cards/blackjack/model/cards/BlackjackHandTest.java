package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.frijolie.cards.blackjack.model.CardRandomizer;
import com.frijolie.cards.blackjack.model.game.GameRules;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackHandTest {

  private BlackjackHand hand;
  private String message;

  @BeforeEach
  void setUp() {
    hand = new BlackjackHand();
    GameRules.SPLITS_ALLOWED = true;
    GameRules.SPLIT_ON_VALUE = false;
    GameRules.DOUBLE_DOWN_ALLOWED = true;
    GameRules.OFFER_INSURANCE = true;
    GameRules.OFFER_SURRENDER = false;
  }

  @AfterEach
  void tearDown() {
    hand = null;
  }

  @Test
  void addCard_ShouldThrowNPEWhenPassedANullArg() {
    message = "Should throw NullPointerException when a null arg is passed";
    assertThrows(NullPointerException.class, () -> hand.addCard(null), message);
  }

  @Test
  void addCard_AfterAddingCardSizeShouldIncreaseByOne() {
    message = "After adding a card, the hand.size() should increase by 1";
    hand.addCard(CardRandomizer.getCard());
    assertEquals(1, hand.getCards().size(), message);
  }

  @Test
  void calculateScore_AceKing_ShouldEqual_21() {
    message =
        String.format(
            "Having an Ace and a 10pt card should equal 21." + " Instead the score is: %d",
            hand.getScore());
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.KING));
    assertEquals(21, hand.getScore(), message);
  }

  @Test
  void calculateScore_AceAceFour_ShouldEqual_16() {
    message = String.format("A + A + 4 should equal 16. Instead it is: %d", hand.getScore());
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.FOUR));
    assertEquals(16, hand.getScore(), message);
  }

  @Test
  void calculateScore_KingThreeSevenSix_ShouldEqual_26() {
    message = String.format("K + 3 + 7 + 6 should equal 26. Instead it is: %d", hand.getScore());
    hand.addCard(CardRandomizer.getCard(Rank.KING));
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    hand.addCard(CardRandomizer.getCard(Rank.SEVEN));
    hand.addCard(CardRandomizer.getCard(Rank.SIX));
    assertEquals(26, hand.getScore(), message);
  }

  @Test
  void calculateScore_TenEightAceAce_ShouldEqual_20() {
    message = String.format("T + 8 + A + A should equal 20. Instead it is: %d", hand.getScore());
    hand.addCard(CardRandomizer.getCard(Rank.TEN));
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    assertEquals(20, hand.getScore(), message);
  }

  @Test
  void isBust_KingQueenJack_IsBustShouldBe_True() {
    message = "K + Q + J. isBust should be true";
    hand.addCard(CardRandomizer.getCard(Rank.KING));
    hand.addCard(CardRandomizer.getCard(Rank.QUEEN));
    hand.addCard(CardRandomizer.getCard(Rank.JACK));
    assertTrue(hand.isBust(), message);
  }

  @Test
  void isBust_EightEight_IsBustShouldBe_False() {
    message = "8 + 8. isBust should be false";
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    assertFalse(hand.isBust(), message);
  }

  @Test
  void isSoft_AceNine_IsSoftShouldBe_True() {
    message = "A + 9. isSoft should be true";
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.NINE));
    assertTrue(hand.isSoft(), message);
  }

  @Test
  void isSoft_AceNineTen_IsSoftShouldBe_False() {
    message = "A + 9 + T. isSoft should be false. Too many cards.";
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.NINE));
    hand.addCard(CardRandomizer.getCard(Rank.TEN));
    assertFalse(hand.isSoft(), message);
  }

  @Test
  void isSoft_AceAce_IsSoftShouldBe_True() {
    message = "A + A. isSoft should be true. Pair of Aces";
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    assertTrue(hand.isSoft(), message);
  }

  @Test
  void hasBlackjack_AceQueen_HasBlackjackShouldBe_True() {
    message = "A + Q. hasBlackjack should be true.";
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.QUEEN));
    assertTrue(hand.hasBlackjack(), message);
  }

  @Test
  void hasBlackjack_FiveFiveAce_HasBlackjackShouldBe_False() {
    message = "5 + 5 + A. hasBlackjack should be false. Too many cards";
    hand.addCard(CardRandomizer.getCard(Rank.FIVE));
    hand.addCard(CardRandomizer.getCard(Rank.FIVE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    assertFalse(hand.hasBlackjack(), message);
  }

  @Test
  void hasBlackjack_AceAce_HasBlackjackShouldBe_False() {
    message = "A + A. hasBlackjack should be false. Score does not equal 21.";
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    assertFalse(hand.hasBlackjack(), message);
  }

  @Test
  void numberOfAces_AceAce_ShouldReturn_2() {
    message = String.format("The hand should have 2 aces. Instead it has: %d", hand.numberOfAces());
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    assertEquals(2, hand.numberOfAces(), message);
  }

  @Test
  void numberOfAces_AceEight_ShouldReturn_1() {
    message = String.format("The hand has two cards. One of them an Ace. It should return one Ace");
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    assertEquals(1, hand.numberOfAces(), message);
  }

  @Test
  void canSplit_AceAce_CanSplitShouldBe_True() {
    message = "A + A. canSplit should be true";
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    assertAll(
        message,
        () -> assertTrue(hand.canSplit()),
        () -> assertTrue(GameRules.SPLITS_ALLOWED),
        () -> assertTrue(hand.hasTwoCards()),
        () -> assertFalse(GameRules.SPLIT_ON_VALUE));
  }

  @Test
  void canSplit_EightEight_CanSplitShouldBe_True() {
    message = "8 + 8. canSplit should be true. has a pair";
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    assertAll(
        message,
        () -> assertTrue(hand.canSplit()),
        () -> assertTrue(GameRules.SPLITS_ALLOWED),
        () -> assertTrue(hand.hasTwoCards()),
        () -> assertFalse(GameRules.SPLIT_ON_VALUE));
  }

  @Test
  void canSplit_EightEightThree_CanSplitShouldBe_False() {
    message = "8 + 8 + 3. canSplit should be false. Too many cards";
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    assertAll(
        message,
        () -> assertFalse(hand.canSplit()),
        () -> assertTrue(GameRules.SPLITS_ALLOWED),
        () -> assertFalse(GameRules.SPLIT_ON_VALUE),
        () -> assertFalse(hand.hasTwoCards()));
  }

  @Test
  void canSplit_EightThree_CanSplitShouldBe_False() {
    message = "8 + 3. canSplit should be false. Different ranks.";
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    assertAll(
        message,
        () -> assertFalse(hand.canSplit()),
        () -> assertTrue(GameRules.SPLITS_ALLOWED),
        () -> assertFalse(GameRules.SPLIT_ON_VALUE),
        () -> assertTrue(hand.hasTwoCards()));
  }

  @Test
  void canSplit_TenKing_SplitOnValueIsTrue_CanSplitShouldBe_True() {
    GameRules.SPLIT_ON_VALUE = true;
    hand.addCard(CardRandomizer.getCard(Rank.TEN));
    hand.addCard(CardRandomizer.getCard(Rank.KING));
    String message =
        "SplitOnValue = true, has two cards of same value." + " canSplit should be true";
    assertAll(
        message,
        () -> assertTrue(GameRules.SPLIT_ON_VALUE),
        () -> assertTrue(GameRules.SPLITS_ALLOWED),
        () -> assertTrue(hand.hasTwoCards()),
        () -> assertTrue(hand.canSplit()));
  }

  @Test
  void canSplit_AceAce_SplitsAllowedIsFalseCanSplitShouldBe_False() {
    GameRules.SPLITS_ALLOWED = false;
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    hand.addCard(CardRandomizer.getCard(Rank.ACE));
    String message = "Hand has pair. splits allowed is false. canSplit = false";
    assertAll(
        message,
        () -> assertFalse(hand.canSplit()),
        () -> assertFalse(GameRules.SPLITS_ALLOWED),
        () -> assertTrue(hand.hasTwoCards()),
        () -> assertFalse(GameRules.SPLIT_ON_VALUE));
  }

  @Test
  void canDouble_FiveFour_CanDoubleShouldBe_True() {
    message = "5 + 4. canDouble should be true.";
    hand.addCard(CardRandomizer.getCard(Rank.FIVE));
    hand.addCard(CardRandomizer.getCard(Rank.FOUR));
    assertAll(
        message,
        () -> assertTrue(hand.canDouble()),
        () -> assertTrue(GameRules.DOUBLE_DOWN_ALLOWED),
        () -> assertTrue(hand.hasTwoCards()));
  }

  @Test
  void canDouble_ThreeThree_CanDoubleShouldBe_False() {
    message = "3 + 3. canDouble should be false. score too low.";
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    assertAll(
        message,
        () -> assertTrue(GameRules.DOUBLE_DOWN_ALLOWED),
        () -> assertTrue(hand.hasTwoCards()),
        () -> assertFalse(hand.canDouble()));
  }

  @Test
  void canDouble_EightThreeFive_CanDoubleShouldBe_False() {
    message = "8 + 3 + 5. canDouble should be false. too many cards";
    hand.addCard(CardRandomizer.getCard(Rank.EIGHT));
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    hand.addCard(CardRandomizer.getCard(Rank.FIVE));
    assertAll(
        message,
        () -> assertTrue(GameRules.DOUBLE_DOWN_ALLOWED),
        () -> assertFalse(hand.hasTwoCards()),
        () -> assertFalse(hand.canDouble()));
  }

  @Test
  void canDouble_FiveFour_DoubleDownAllowedIsFalse_CanDoubleShouldBe_False() {
    GameRules.DOUBLE_DOWN_ALLOWED = false;
    hand.addCard(CardRandomizer.getCard(Rank.FIVE));
    hand.addCard(CardRandomizer.getCard(Rank.FOUR));
    String message = "DoubleDownAllowed = false, can double should be false";
    assertAll(
        message,
        () -> assertTrue(hand.hasTwoCards()),
        () -> assertFalse(GameRules.DOUBLE_DOWN_ALLOWED),
        () -> assertFalse(hand.canDouble()));
  }

  @Test
  void canHit_ThreeThree_CanHitShouldBe_True() {
    message = "3 + 3. canHit should be true.";
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    hand.addCard(CardRandomizer.getCard(Rank.THREE));
    assertTrue(hand.canHit(), message);
  }

  @Test
  void canHit_QueenQueenQueen_CanHitShouldBe_False() {
    message = "Q + Q + Q. canHit should be false. Score too high";
    hand.addCard(CardRandomizer.getCard(Rank.QUEEN));
    hand.addCard(CardRandomizer.getCard(Rank.QUEEN));
    hand.addCard(CardRandomizer.getCard(Rank.QUEEN));
    assertFalse(hand.canHit(), message);
  }

  @Test
  void setHandState_IfInactiveCanHitShouldBe_False() {
    message = "if the handstate is inactive, canHit should be false";
    hand.setHandState(HandState.INACTIVE);
    assertFalse(hand.canHit(), message);
  }

  @Test
  void setHandState_IfInactiveCanDoubleShouldBe_False() {
    message = "if the handstate is inactive, canDouble should be false";
    hand.setHandState(HandState.INACTIVE);
    assertFalse(hand.canDouble(), message);
  }
}
