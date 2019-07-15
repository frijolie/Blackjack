package com.frijolie.cards.blackjack.model.players;

/**
 * This interface ensures behavior only applicable to the human player. These
 * actions are not applicable to the Dealer.
 *
 * @see Player
 */
interface HumanPlayerActions extends Player, BetActions {

  /**
   * This action will require the player to make an additional equal bet, draw
   *  one additional card, then stand.
   */
  void doubleDown();

  /**
   * This action will require the player to forfeit half their current bet then
   * stand. This action essentially folds their hand.
   */
  void surrender();

  /**
   * This action will require the player to make an additional 50% bet. This
   * will pay out if the dealer has blackjack.
   */
  void buyInsurance();

  /**
   * This action will require the player to split their hand. The player will
   * take one of their two cards and form a new hand with an equal bet as the
   * first. The player may then do actions on each hand independently.
   */
  void split();
}