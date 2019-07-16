package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuitTest {

  private Card queenOfSpades;
  private Card eightOfHearts;
  private Card jackOfDiamonds;
  private Card aceOfClubs;

  @BeforeEach
  void setUp() {
    queenOfSpades = new BlackjackCard(Rank.QUEEN, Suit.SPADES);
    eightOfHearts = new BlackjackCard(Rank.EIGHT, Suit.HEARTS);
    jackOfDiamonds = new BlackjackCard(Rank.JACK, Suit.DIAMONDS);
    aceOfClubs = new BlackjackCard(Rank.ACE, Suit.CLUBS);
  }

  @AfterEach
  void tearDown() {
    queenOfSpades = null;
    eightOfHearts = null;
    jackOfDiamonds = null;
    aceOfClubs = null;
  }

  @Test
  void toString_QueenOfSpadesToStringShouldEqual_S() {
    String message =
        String.format(
            "Queen of Spades suit.toString should return 'S', Instead it is: %s",
            queenOfSpades.getSuit().toString());
    assertEquals("Spades", queenOfSpades.getSuit().toString(), message);
  }

  @Test
  void getLetter_AllSuitLettersAreCorrect() {
    assertAll(
        "All Suit letters should be correct",
        () -> assertEquals("S", queenOfSpades.getSuit().getLetter()),
        () -> assertEquals("H", eightOfHearts.getSuit().getLetter()),
        () -> assertEquals("D", jackOfDiamonds.getSuit().getLetter()),
        () -> assertEquals("C", aceOfClubs.getSuit().getLetter()));
  }

  @Test
  void getSymbol_AllSuitSymbolsAreCorrect() {
    assertAll(
        "All Suit symbols are correct",
        () -> assertEquals('♠', queenOfSpades.getSuit().getSymbol()),
        () -> assertEquals('♥', eightOfHearts.getSuit().getSymbol()),
        () -> assertEquals('♦', jackOfDiamonds.getSuit().getSymbol()),
        () -> assertEquals('♣', aceOfClubs.getSuit().getSymbol()));
  }
}
