package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {

  private Deck deck;
  private Map<String, Long> cardFrequency;

  @BeforeEach
  void setUp() {
    deck = new Deck();
    cardFrequency =
        deck.getCards().stream()
            .collect(Collectors.groupingBy(Card::toString, Collectors.counting()));
  }

  @AfterEach
  void tearDown() {
    deck = null;
  }

  int numberOfRanks(Rank rank) {
    return (int) deck.getCards().stream().filter(card -> card.getRank() == rank).count();
  }

  int numberOfSuits(Suit suit) {
    return (int) deck.getCards().stream().filter(card -> card.getSuit() == suit).count();
  }

  long getFrequency(String cardString) {
    return cardFrequency.get(cardString);
  }

  @Test
  void fullDeckShouldContain52Cards() {
    String message =
        String.format(
            "A full deck should contain 52 cards. instead it has: %s", deck.getCards().size());
    assertEquals(52, deck.getCards().size(), message);
  }

  @Test
  void deckShouldContain4OfEveryRank() {
    assertAll(
        "There should be 4 of every Rank",
        () -> assertEquals(4, numberOfRanks(Rank.ACE), "There are not 4 Aces"),
        () -> assertEquals(4, numberOfRanks(Rank.TWO), "There are not 4 Twos"),
        () -> assertEquals(4, numberOfRanks(Rank.THREE), "There are not 4 Threes"),
        () -> assertEquals(4, numberOfRanks(Rank.FOUR), "There are not 4 Fours"),
        () -> assertEquals(4, numberOfRanks(Rank.FIVE), "There are not 4 Fives"),
        () -> assertEquals(4, numberOfRanks(Rank.SIX), "There are not 4 Sixes"),
        () -> assertEquals(4, numberOfRanks(Rank.SEVEN), "There are not 4 Sevens"),
        () -> assertEquals(4, numberOfRanks(Rank.EIGHT), "There are not 4 Eights"),
        () -> assertEquals(4, numberOfRanks(Rank.NINE), "There are not 4 Nines"),
        () -> assertEquals(4, numberOfRanks(Rank.TEN), "There are not 4 Tens"),
        () -> assertEquals(4, numberOfRanks(Rank.JACK), "There are not 4 Jacks"),
        () -> assertEquals(4, numberOfRanks(Rank.QUEEN), "There are not 4 Queens"),
        () -> assertEquals(4, numberOfRanks(Rank.KING), "There are not 4 Kings"));
  }

  @Test
  void deckShouldContain13CardsForEverySuit() {
    assertAll(
        "There should be 13 cards for every Suit",
        () -> assertEquals(13, numberOfSuits(Suit.HEARTS), "There are not 13 Hearts"),
        () -> assertEquals(13, numberOfSuits(Suit.CLUBS), "There are not 13 Clubs"),
        () -> assertEquals(13, numberOfSuits(Suit.DIAMONDS), "There are not 13 Diamonds"),
        () -> assertEquals(13, numberOfSuits(Suit.SPADES), "There are not 13 Spades"));
  }

  @Test
  void deckShouldContainEveryRankInHearts() {
    assertAll(
        "There should be every Rank in Hearts",
        () -> assertEquals(1, getFrequency("AH"), "There should only be 1 Ace of Hearts"),
        () -> assertEquals(1, getFrequency("2H"), "There should only be 1 Two of Hearts"),
        () -> assertEquals(1, getFrequency("3H"), "There should only be 1 Three of Hearts"),
        () -> assertEquals(1, getFrequency("4H"), "There should only be 1 Four of Hearts"),
        () -> assertEquals(1, getFrequency("5H"), "There should only be 1 Five of Hearts"),
        () -> assertEquals(1, getFrequency("6H"), "There should only be 1 Six of Hearts"),
        () -> assertEquals(1, getFrequency("7H"), "There should only be 1 Seven of Hearts"),
        () -> assertEquals(1, getFrequency("8H"), "There should only be 1 Eight of Hearts"),
        () -> assertEquals(1, getFrequency("9H"), "There should only be 1 Nine of Hearts"),
        () -> assertEquals(1, getFrequency("TH"), "There should only be 1 Ten of Hearts"),
        () -> assertEquals(1, getFrequency("JH"), "There should only be 1 Jack of Hearts"),
        () -> assertEquals(1, getFrequency("QH"), "There should only be 1 Queen of Hearts"),
        () -> assertEquals(1, getFrequency("KH"), "There should only be 1 King of Hearts"));
  }

  @Test
  void deckShouldContainEveryRankInClubs() {
    assertAll(
        "There should be every Rank in Clubs",
        () -> assertEquals(1, getFrequency("AC"), "There should only be 1 Ace of Clubs"),
        () -> assertEquals(1, getFrequency("2C"), "There should only be 1 Two of Clubs"),
        () -> assertEquals(1, getFrequency("3C"), "There should only be 1 Three of Clubs"),
        () -> assertEquals(1, getFrequency("4C"), "There should only be 1 Four of Clubs"),
        () -> assertEquals(1, getFrequency("5C"), "There should only be 1 Five of Clubs"),
        () -> assertEquals(1, getFrequency("6C"), "There should only be 1 Six of Clubs"),
        () -> assertEquals(1, getFrequency("7C"), "There should only be 1 Seven of Clubs"),
        () -> assertEquals(1, getFrequency("8C"), "There should only be 1 Eight of Clubs"),
        () -> assertEquals(1, getFrequency("9C"), "There should only be 1 Nine of Clubs"),
        () -> assertEquals(1, getFrequency("TC"), "There should only be 1 Ten of Clubs"),
        () -> assertEquals(1, getFrequency("JC"), "There should only be 1 Jack of Clubs"),
        () -> assertEquals(1, getFrequency("QC"), "There should only be 1 Queen of Clubs"),
        () -> assertEquals(1, getFrequency("KC"), "There should only be 1 King of Clubs"));
  }

  @Test
  void deckShouldContainEveryRankInDiamonds() {
    assertAll(
        "There should be every Rank in Diamonds",
        () -> assertEquals(1, getFrequency("AD"), "There should only be 1 Ace of Diamonds"),
        () -> assertEquals(1, getFrequency("2D"), "There should only be 1 Two of Diamonds"),
        () -> assertEquals(1, getFrequency("3D"), "There should only be 1 Three of Diamonds"),
        () -> assertEquals(1, getFrequency("4D"), "There should only be 1 Four of Diamonds"),
        () -> assertEquals(1, getFrequency("5D"), "There should only be 1 Five of Diamonds"),
        () -> assertEquals(1, getFrequency("6D"), "There should only be 1 Six of Diamonds"),
        () -> assertEquals(1, getFrequency("7D"), "There should only be 1 Seven of Diamonds"),
        () -> assertEquals(1, getFrequency("8D"), "There should only be 1 Eight of Diamonds"),
        () -> assertEquals(1, getFrequency("9D"), "There should only be 1 Nine of Diamonds"),
        () -> assertEquals(1, getFrequency("TD"), "There should only be 1 Ten of Diamonds"),
        () -> assertEquals(1, getFrequency("JD"), "There should only be 1 Jack of Diamonds"),
        () -> assertEquals(1, getFrequency("QD"), "There should only be 1 Queen of Diamonds"),
        () -> assertEquals(1, getFrequency("KD"), "There should only be 1 King of Diamonds"));
  }

  @Test
  void deckShouldContainEveryRankInSpades() {
    assertAll(
        "There should be every Rank in Spades",
        () -> assertEquals(1, getFrequency("AS"), "There should only be 1 Ace of Spades"),
        () -> assertEquals(1, getFrequency("2S"), "There should only be 1 Two of Spades"),
        () -> assertEquals(1, getFrequency("3S"), "There should only be 1 Three of Spades"),
        () -> assertEquals(1, getFrequency("4S"), "There should only be 1 Four of Spades"),
        () -> assertEquals(1, getFrequency("5S"), "There should only be 1 Five of Spades"),
        () -> assertEquals(1, getFrequency("6S"), "There should only be 1 Six of Spades"),
        () -> assertEquals(1, getFrequency("7S"), "There should only be 1 Seven of Spades"),
        () -> assertEquals(1, getFrequency("8S"), "There should only be 1 Eight of Spades"),
        () -> assertEquals(1, getFrequency("9S"), "There should only be 1 Nine of Spades"),
        () -> assertEquals(1, getFrequency("TS"), "There should only be 1 Ten of Spades"),
        () -> assertEquals(1, getFrequency("JS"), "There should only be 1 Jack of Spades"),
        () -> assertEquals(1, getFrequency("QS"), "There should only be 1 Queen of Spades"),
        () -> assertEquals(1, getFrequency("KS"), "There should only be 1 King of Spades"));
  }
}
