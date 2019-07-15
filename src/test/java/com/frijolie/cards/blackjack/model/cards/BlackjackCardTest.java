package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackCardTest {

  private Card tenOfClubs;
  private Card tenOfClubs2;
  private Card fourOfDiamonds;
  private Card tenOfDiamonds;
  private String message;

  @BeforeEach
  void setUp() {
    tenOfClubs = new BlackjackCard(Rank.TEN, Suit.CLUBS);
    tenOfClubs2 = new BlackjackCard(Rank.TEN, Suit.CLUBS);
    fourOfDiamonds = new BlackjackCard(Rank.FOUR, Suit.DIAMONDS);
    tenOfDiamonds = new BlackjackCard(Rank.TEN, Suit.DIAMONDS);
  }

  @AfterEach
  void tearDown() {
    tenOfClubs = null;
    tenOfClubs2 = null;
    fourOfDiamonds = null;
    tenOfDiamonds = null;
  }

  @Test
  void hashCode_CardsOfSameRankAndSuitShouldShareTheSameHashCode() {
    message = "Duplicate cards should have the same hashcode";
    assertEquals(tenOfClubs.hashCode(), tenOfClubs2.hashCode(), message);
  }

  @Test
  void hashCode_CardsOfDifferentRankAndSuitShouldNotShareTheSameHashCode() {
    message = "Cards that have different Rank and Suit should not have the same hashcode";
    assertNotEquals(tenOfClubs.hashCode(), fourOfDiamonds.hashCode(), message);
  }

  @Test
  void hashCode_CardsOfSameRankDifferentSuitShouldNnotShareTheSameHashCode() {
    message = "Cards that have the same Rank but different Suit should not have the same hashcode";
    assertNotEquals(tenOfClubs.hashCode(), tenOfDiamonds.hashCode(), message);
  }

  @Test
  void hashCode_CardsOfDifferentRankSameSuitShouldNotShareTheSameHashCode() {
    message = "Cards that have the same Suit but different Rank should not have the same hashcode";
    assertNotEquals(tenOfDiamonds.hashCode(), fourOfDiamonds.hashCode(), message);
  }

  @SuppressWarnings("SimplifiableJUnitAssertion")
  @Test
  void equals_CardsOfSameRankAndSuitShouldBeEqual() {
    message = "Duplicate cards should be equal";
    assertTrue(tenOfClubs.equals(tenOfClubs2), message);
  }

  @SuppressWarnings("SimplifiableJUnitAssertion")
  @Test
  void equals_CardsOfTheSameRankButDifferentSuitsShouldNotBeEqual() {
    message = "Cards that are the same Rank but different Suit should not be equal";
    assertFalse(tenOfClubs.equals(tenOfDiamonds), message);
  }

  @Test
  void equals_CardsOfTheSameSuitButDifferentRanksShouldNotBeEqual() {
    message = "Cards that are the same Suit but different Rank should not be equal";
    //noinspection SimplifiableJUnitAssertion
    assertFalse(tenOfDiamonds.equals(fourOfDiamonds), message);
  }

  @Test
  void toString_TenOfClubsToStringShouldReturn_TC() {
    assertEquals("TC", tenOfClubs.toString());
  }
}