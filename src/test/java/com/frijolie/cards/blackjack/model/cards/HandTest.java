package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.frijolie.cards.blackjack.model.CardRandomizer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {

  private Hand dealerHand;
  private Hand playerHand;

  @BeforeEach
  void setUp() {
    dealerHand = new BlackjackHand();
    playerHand = new BlackjackHand();
  }

  @AfterEach
  void tearDown() {
    playerHand = null;
    dealerHand = null;
  }

  @Test
  void compareTo_DealerHandCompareToPlayerHandShouldReturnNegativeNumber() {
    dealerHand.addCard(CardRandomizer.getCard(Rank.TWO));
    dealerHand.addCard(CardRandomizer.getCard(Rank.TWO));
    playerHand.addCard(CardRandomizer.getCard(Rank.TEN));
    playerHand.addCard(CardRandomizer.getCard(Rank.ACE));

    /*
      Dealer score = 4 (2 + 2)
      Player score = 21 (10 + 11)
      4 - 21 = -17
     */
    assertEquals(-17, dealerHand.compareTo(playerHand));
  }

  @Test
  void compareTo_PlayerHandComparedToDealerHandShouldReturnPositiveNumber() {
    dealerHand.addCard(CardRandomizer.getCard(Rank.TWO));
    dealerHand.addCard(CardRandomizer.getCard(Rank.TWO));
    playerHand.addCard(CardRandomizer.getCard(Rank.TEN));
    playerHand.addCard(CardRandomizer.getCard(Rank.ACE));

    /*
      Dealer score = 4 (2 + 2)
      Player score = 21 (10 + 11)
      21 - 4 = 17
     */
    assertEquals(17, playerHand.compareTo(dealerHand));
  }

  @Test
  void compareTo_HandsWithSameScoreShouldReturnZero() {
    playerHand.addCard(CardRandomizer.getCard(Rank.FIVE));
    playerHand.addCard(CardRandomizer.getCard(Rank.NINE));
    dealerHand.addCard(CardRandomizer.getCard(Rank.ACE));
    dealerHand.addCard(CardRandomizer.getCard(Rank.THREE));

    /*
      Dealer score = 14 (11 + 3)
      Player score = 14 (5 + 9)
      14 - 14 = 0
     */
    assertEquals(0, playerHand.compareTo(dealerHand));
  }
}