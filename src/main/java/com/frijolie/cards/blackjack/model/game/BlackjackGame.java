package com.frijolie.cards.blackjack.model.game;

import com.frijolie.cards.blackjack.model.cards.Card;
import com.frijolie.cards.blackjack.model.cards.Hand;
import com.frijolie.cards.blackjack.model.cards.HandResult;
import com.frijolie.cards.blackjack.model.cards.Rank;
import com.frijolie.cards.blackjack.model.cards.Shoe;
import com.frijolie.cards.blackjack.model.players.BlackjackDealer;
import com.frijolie.cards.blackjack.model.players.BlackjackPlayer;
import com.frijolie.cards.blackjack.model.players.Player;

import java.util.List;

public class BlackjackGame implements Game {

  private final Player dealer;
  private final Player player;
  private final Hand dealerHand;
  private final Hand playerHand;
  private final Shoe shoe;
  private boolean offerInsurance;
  private boolean offerSurrender;
  private boolean gameIsOver;
  private final List<Card> dealerCards;
  private final List<Card> playerCards;

  /**
   * Default no-arg constructor. Instantiates objects and sets defaults.
   */
  public BlackjackGame() {
    shoe = new Shoe();
    player = new BlackjackPlayer();
    dealer = new BlackjackDealer();
    dealerHand = dealer.getHand();
    dealerCards = dealerHand.getCards();
    playerHand = player.getHand();
    playerCards = playerHand.getCards();
    player.joinGame(this);
    dealer.joinGame(this);
    playGame();
  }

  @Override
  public final void determineWinner() {
    int playerScore = playerHand.getScore();
    int dealerScore = dealerHand.getScore();

    if (playerScore > GameRules.MAX_SCORE
        && dealerScore <= GameRules.MAX_SCORE) {
      dealerHand.setHandResult(HandResult.WIN);
      playerHand.setHandResult(HandResult.BUST);
    }

    if (dealerScore > GameRules.MAX_SCORE
        && playerScore <= GameRules.MAX_SCORE) {
      dealerHand.setHandResult(HandResult.BUST);
      playerHand.setHandResult(HandResult.WIN);
    }

    if (playerScore > dealerScore
        && playerScore <= GameRules.MAX_SCORE) {
      dealerHand.setHandResult(HandResult.LOSE);
      playerHand.setHandResult(HandResult.WIN);
    }

    if (dealerScore > playerScore
        && dealerScore <= GameRules.MAX_SCORE) {
      dealerHand.setHandResult(HandResult.WIN);
      playerHand.setHandResult(HandResult.LOSE);
    }

    if (playerScore == dealerScore
        && playerScore <= GameRules.MAX_SCORE) {
      dealerHand.setHandResult(HandResult.PUSH);
      playerHand.setHandResult(HandResult.PUSH);
    }
  }

  @Override
  public final void playGame() {
    dealInitialCards();
    calculateOfferInsurance();
    calculateOfferSurrender();
  }

  @Override
  public final void newGame() {
    throw new UnsupportedOperationException("unimplemented method");
  }

  @Override
  public final Player getPlayer() {
    return player;
  }

  @Override
  public final Player getDealer() {
    return dealer;
  }

  @Override
  public final Shoe getShoe() {
    return shoe;
  }

  /**
   * Deals card to player, then one to dealer, the repeats. Ends with both the
   * player and dealer have two cards each.
   */
  private void dealInitialCards() {
    for (int i = 0; i < 2; i++) {
      dealTo(player);
      dealTo(dealer);
    }
  }

  /**
   * Deals a card from the {@link Shoe} to the {@link Player}.
   *
   * @param player to receive the card
   */
  private void dealTo(Player player) {
    player.getHand().addCard(shoe.deal());
  }

  /**
   * Calculates the value of offerInsurance. This will be {@code true} if the
   * dealer top (face-up) card is an Ace, the Dealer has two cards, and
   * {@link GameRules#OFFER_INSURANCE} is {@code true}. After the calculation
   * has been made the value of {@link #offerInsurance} is set.
   */
  private void calculateOfferInsurance() {
    offerInsurance = GameRules.OFFER_INSURANCE
        && dealerShowingAce()
        && dealerHand.hasTwoCards();
  }

  /**
   * Returns the value of offerInsurance.
   * @return {@code true} if insurance should be offered
   * @see #calculateOfferInsurance()
   */
  public final boolean getOfferInsurance() {
    return offerInsurance;
  }

  /**
   * Calculates the value of {@link #offerSurrender}. This will be {@code true}
   * if {@link GameRules#OFFER_SURRENDER} is {@code true}, the player has two
   * cards, and the players hand value is less than 21. After the calculation
   * has been made, the value is set in {@code offerSurrender}.
   */
  private void calculateOfferSurrender() {
    offerSurrender = GameRules.OFFER_SURRENDER
      && playerHand.hasTwoCards()
      && playerHand.getScore() < GameRules.MAX_SCORE;
  }

  /**
   * Returns the value of offerSurrender.
   * @return {@code true} if the option to surrender should be offered
   * @see #calculateOfferSurrender()
   */
  public final boolean getOfferSurrender() {
    return offerSurrender;
  }

  /**
   * Returns {@code true} if the dealer top (face-up) card is an Ace.
   * @return {@code true} if the dealer is showing an Ace.
   */
  private boolean dealerShowingAce() {
    String error = String.format("Dealer top card is not an Ace. It has: %d cards, or is empty: %b",
        dealerCards.size(), dealerCards.isEmpty());
    if (dealerCards.isEmpty() || dealerCards.size() < 2) {
      throw new IndexOutOfBoundsException(error);
    } else {
      return dealerCards.get(1).getRank() == Rank.ACE;
    }
  }

  /**
   * Returns the value of gameIsOver.
   * @return {@code true} if the game is over
   */
  public final boolean getGameIsOver() {
    return gameIsOver;
  }
}