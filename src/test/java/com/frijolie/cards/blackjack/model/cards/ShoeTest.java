package com.frijolie.cards.blackjack.model.cards;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.frijolie.cards.blackjack.model.game.GameRules;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShoeTest {

  private Shoe shoe;
  private String message;

  @BeforeEach
  void setUp() {
    shoe = new Shoe();
  }

  @AfterEach
  void tearDown() {
    shoe = null;
  }

  @Test
  void noArgConstructor_ShoeShouldContain156Cards() {
    message = String.format("The no-arg constructor should populate the deck with 156 cards. Instead it has: %d", shoe.getCards().size());
    assertAll(message,
      ()-> assertEquals(3*52, shoe.getCards().size(), message),
      ()-> assertEquals(3, GameRules.DEFAULT_NUMBER_OF_DECKS)
    );
  }

  @Test
  void argConstructor_PopulatingShoeWithFiveDecksShouldContain260Cards() {
    message = "A shoe with five decks should contain 260 cards";
    assertEquals(5*52, new Shoe(5).getCards().size(), message);
  }

  @Test
  void argConstructor_PopulatingDeckExceedsMaxShouldPopulateWith416Cards() {
    message = "Attempting to populate deck with more than the max allowed decks, should be populated with max.";
    assertAll(message,
      ()-> assertEquals(8*52, new Shoe(10).getCards().size()),
      ()-> assertEquals(8, GameRules.MAX_NUMBER_OF_DECKS)
    );
  }

  @Test
  void argConstructor_PopulatingDeckWithLessThanMinPopulatesWith52Cards() {
    message = "Attempting to populate the deck with less than the min allowed decks, should populate with the min number of decks.";
    assertAll(message,
      ()-> assertEquals(52, new Shoe(0).getCards().size()),
      ()-> assertEquals(1, GameRules.MIN_NUMBER_OF_DECKS)
    );
  }

  @Test
  void deal_AfterDealingCardShoeSizeShouldHaveDecreasedByOne() {
    message = "After dealing a card one should have been removed";
    shoe.deal();
    /* populates shoe with 3 decks, 156 cards. Deals one. Should now be 155 */
    assertEquals(155, shoe.getCards().size(), message);
  }

  @Test
  void deal_AfterShoeIsEmptyTryingToDealThrowsNSE() {
    message = "If the Shoe is empty, and you try to deal, should throw NSE";
    assertThrows(NoSuchElementException.class, () -> {
      shoe.getCards().clear();
      shoe.deal();
    }, message);
  }
}