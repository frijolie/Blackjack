package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {

  private Card tenOfHearts;
  private Card tenOfDiamonds;
  private Card eightOfHearts;

  @BeforeEach
  void setUp() {
    tenOfHearts = new BlackjackCard(Rank.TEN, Suit.HEARTS);
    tenOfDiamonds = new BlackjackCard(Rank.TEN, Suit.DIAMONDS);
    eightOfHearts = new BlackjackCard(Rank.EIGHT, Suit.HEARTS);
  }

  @AfterEach
  void tearDown() {
    tenOfHearts = null;
    tenOfDiamonds = null;
    eightOfHearts = null;
  }

  @Test
  void sameRank_TenOfHeartsAndTenOfDiamondsSameRankShouldBeTrue() {
    assertTrue(tenOfHearts.sameRank(tenOfDiamonds));
  }

  @Test
  void sameRank_TenOfHeartsAndEightOfHeartsSameRankShouldBeFalse() {
    assertFalse(tenOfHearts.sameRank(eightOfHearts));
  }

  @Test
  void sameRank_SendingNullCardShouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> tenOfDiamonds.sameRank(null));
  }

  @Test
  void sameValue_TenOfHeartsAndTenOfDiamondsSameValueShouldBeTrue() {
    assertTrue(tenOfHearts.sameValue(tenOfDiamonds));
  }

  @Test
  void sameValue_TenOfHeartsAndEightOfHeartsSameValueShouldBeFalse() {
    assertFalse(tenOfHearts.sameValue(eightOfHearts));
  }

  @Test
  void sameValue_SendingNullCardShouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> tenOfDiamonds.sameValue(null));
  }

  @Test
  void compareTo_TenOfHeartsComparedToTenOfDiamondsShouldReturn() {
    /*
     Ranks are equal.
     Hearts ordinal = 4.
     Diamonds ordinal = 2.
     4 - 2 = 2
    */
    assertEquals(2, tenOfHearts.compareTo(tenOfDiamonds));
  }

  @Test
  void compareTo_SendingNullCardShouldThrowNPE() {
    assertThrows(NullPointerException.class, () -> tenOfDiamonds.compareTo(null));
  }
}
