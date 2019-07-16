package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RankTest {

  private Card tenOfHearts;

  @BeforeEach
  void setUp() {
    tenOfHearts = new BlackjackCard(Rank.TEN, Suit.HEARTS);
  }

  @AfterEach
  void tearDown() {
    tenOfHearts = null;
  }

  @Test
  void toString_TenOfHeartsToStringShouldEqual_TH() {
    String message =
        String.format("Was expecting to receive 'Ten', instead it was: %s", tenOfHearts.toString());
    assertEquals("Ten", tenOfHearts.getRank().toString(), message);
  }

  @Test
  void getValue_TenOfHeartsGetValueShouldReturn_10() {
    String message =
        String.format(
            "Was expecting the value to equal 10. instead it is: %d", tenOfHearts.getValue());
    assertEquals(10, tenOfHearts.getValue(), message);
  }

  @Test
  void getLetter_TenOfHeartsGetLetterShouldReturn_T() {
    String message =
        String.format(
            "Was expecting letter to be 'T', instead it is: %s", tenOfHearts.getRank().getLetter());
    assertEquals("T", tenOfHearts.getRank().getLetter(), message);
  }

  @Test
  void getRank_TenOfHeartsGetRankShouldReturn_Rank_TEN() {
    String message =
        String.format(
            "Was expecting the Rank to equal Rank.TEN. Instead it is: %s", tenOfHearts.getRank());
    assertEquals(Rank.TEN, tenOfHearts.getRank(), message);
  }

  @Test
  void getValue_AllRanksShouldReturnCorrectValue() {
    assertAll(
        "All Ranks should return the correct value",
        () -> assertEquals(2, Rank.TWO.getValue()),
        () -> assertEquals(3, Rank.THREE.getValue()),
        () -> assertEquals(4, Rank.FOUR.getValue()),
        () -> assertEquals(5, Rank.FIVE.getValue()),
        () -> assertEquals(6, Rank.SIX.getValue()),
        () -> assertEquals(7, Rank.SEVEN.getValue()),
        () -> assertEquals(8, Rank.EIGHT.getValue()),
        () -> assertEquals(9, Rank.NINE.getValue()),
        () -> assertEquals(10, Rank.TEN.getValue()),
        () -> assertEquals(10, Rank.JACK.getValue()),
        () -> assertEquals(10, Rank.QUEEN.getValue()),
        () -> assertEquals(10, Rank.KING.getValue()),
        () -> assertEquals(11, Rank.ACE.getValue()));
  }

  @Test
  void getLetter_AllRanksShouldReturnCorrectLetter() {
    assertAll(
        "All Ranks should return the correct letter",
        () -> assertEquals("2", Rank.TWO.getLetter()),
        () -> assertEquals("3", Rank.THREE.getLetter()),
        () -> assertEquals("4", Rank.FOUR.getLetter()),
        () -> assertEquals("5", Rank.FIVE.getLetter()),
        () -> assertEquals("6", Rank.SIX.getLetter()),
        () -> assertEquals("7", Rank.SEVEN.getLetter()),
        () -> assertEquals("8", Rank.EIGHT.getLetter()),
        () -> assertEquals("9", Rank.NINE.getLetter()),
        () -> assertEquals("T", Rank.TEN.getLetter()),
        () -> assertEquals("J", Rank.JACK.getLetter()),
        () -> assertEquals("Q", Rank.QUEEN.getLetter()),
        () -> assertEquals("K", Rank.KING.getLetter()),
        () -> assertEquals("A", Rank.ACE.getLetter()));
  }
}
