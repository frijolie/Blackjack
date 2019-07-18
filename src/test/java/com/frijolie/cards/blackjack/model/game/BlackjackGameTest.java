package com.frijolie.cards.blackjack.model.game;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

  private BlackjackGame game;

  @BeforeEach
  void setUp() {
    game = new BlackjackGame();
    GameRules.OFFER_INSURANCE = true;
    GameRules.OFFER_SURRENDER = false;
  }

  @AfterEach
  void tearDown() {
    game = null;
  }

  @Test
  void constructor_playerShouldNotBeNull() {
    assertNotNull(game.getPlayer());
  }

  @Test
  void constructor_dealerShouldNotBeNull() {
    assertNotNull(game.getDealer());
  }

  @Test
  void constructor_shoeShouldNotBeNull() {
    assertNotNull(game.getShoe());
  }

  @Test
  void constructor_playerHandShouldNotBeNull() {
    assertNotNull(game.getPlayer().getHand());
  }

  @Test
  void constructor_dealerHandShouldNotBeNull() {
    assertNotNull(game.getDealer().getHand());
  }

  @Test
  void dealInitialCards_bothPlayersShouldHaveTwoCards() {
    assertAll(
      "After initial deal, both players should have two cards",
      () -> assertEquals(2, game.getPlayer().getHand().getCards().size()),
      () -> assertEquals(2, game.getDealer().getHand().getCards().size()));
  }
}
