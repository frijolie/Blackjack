package com.frijolie.cards.blackjack.model.players;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.frijolie.cards.blackjack.model.cards.HandState;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackPlayerTest {

  private BlackjackPlayer player;

  @BeforeEach
  void setUp() {
    player = new BlackjackPlayer();
  }

  @AfterEach
  void tearDown() {
    player = null;
  }

  @Test
  void constructor_afterCreationIsActiveShouldBe_True() {
    String message = "After creation, player should be active";
    assertTrue(player.isActive(), message);
  }

  @Test
  void constructor_afterCreationIsBankruptShouldBe_False() {
    String message = "After creation, player should not be bankrupt";
    assertFalse(player.isBankrupt(), message);
  }

  @Test
  void constructor_afterCreationPlayerShouldHaveCashEqualTo_1000() {
    String message = "Player should start with 1,000 in cash";
    assertEquals(1000, player.getCash(), message);
  }

  @Test
  void stand_afterStandingHandStateShouldBeInactive() {
    String message = "After standing, the players hand should be inactive";
    player.stand();
    assertEquals(HandState.INACTIVE, player.getHand().getHandState(), message);
  }

  @Test
  void stand_afterStandingIsActiveShouldBe_False() {
    String message = "After standing, the player should not be active";
    player.stand();
    assertFalse(player.isActive(), message);
  }
}