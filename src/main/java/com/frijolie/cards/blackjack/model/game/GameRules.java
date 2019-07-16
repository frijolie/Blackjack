package com.frijolie.cards.blackjack.model.game;

import com.frijolie.cards.blackjack.model.cards.Card;
import com.frijolie.cards.blackjack.model.cards.HandPermissions;
import com.frijolie.cards.blackjack.model.cards.Rank;
import com.frijolie.cards.blackjack.model.cards.Shoe;
import com.frijolie.cards.blackjack.model.players.BlackjackPlayer;

import java.math.BigDecimal;

public abstract class GameRules {

  /**
   * The minimum number of decks required to play the game.
   *
   * @see Shoe
   */
  public static final int MIN_NUMBER_OF_DECKS = 1;

  /**
   * The default number of decks required to play the game.
   *
   * @see Shoe
   */
  public static final int DEFAULT_NUMBER_OF_DECKS = 3;

  /**
   * The maximum number of decks required to play the game.
   *
   * @see Shoe
   */
  public static final int MAX_NUMBER_OF_DECKS = 8;

  /** The highest score a hand can have without a BUST. */
  public static final int MAX_SCORE = 21;

  /**
   * The starting funds for the player. This allows the player place bets and receive proceeds from
   * winning bets.
   *
   * @see BlackjackPlayer
   */
  public static final BigDecimal STARTING_CASH = BigDecimal.valueOf(1000d);

  /**
   * This is a flag to indicate whether the dealer must hit a soft 17.
   *
   * @see BlackjackGame
   */
  public static boolean DEALER_HITS_SOFT_17 = true;

  /**
   * This is a flag to indicate whether the dealer must offer insurance to a player. Insurance is
   * offered if the dealer up-card is an Ace and before the dealer checks for blackjack.
   *
   * @see BlackjackGame
   */
  public static boolean OFFER_INSURANCE = true;

  /**
   * This is a flag to indicate whether the player may elect to surrender their hand. This must be
   * done after the dealer checks for blackjack but before the player makes any other move.
   * Surrendering must be the first, and only, move the player makes.
   *
   * @see BlackjackGame
   */
  public static boolean OFFER_SURRENDER = false;

  /**
   * This sets the minimum amount that can be wagered.
   *
   * @see BlackjackGame
   */
  public static BigDecimal MIN_BET = BigDecimal.valueOf(25d);

  /**
   * This is a flag that will allow a player to double down on their hand. A player can double down
   * on their hand if their hand value is 9, 10, or 11.
   *
   * @see HandPermissions
   */
  public static boolean DOUBLE_DOWN_ALLOWED = true;

  /**
   * This is a flag which will allow a player to split their hand based on {@link Rank}. This will
   * only be an option when the player has only a pair of Ranks.
   *
   * <p>To split, the player removes one card from their hand and forms a new Hand. They then will
   * play their hands independently.
   *
   * <p>For example, if {@code #SPLITS_ALLOWED} is {@code true}, a player could split their hand if
   * they were dealt Ace and Ace.
   *
   * @see HandPermissions
   * @see Card#getRank
   */
  public static boolean SPLITS_ALLOWED = true;

  /**
   * This is a flag which will allow a player to split their hand based on the card value. This will
   * only be an option if {@link #SPLITS_ALLOWED} is {@code true} and the player is dealt cards of
   * equal value.
   *
   * <p>To split, the player removes one card from their hand and forms a new Hand. They then will
   * play their hands independently.
   *
   * <p>For example, if {@link GameRules#SPLITS_ALLOWED} is {@code true} and {@code SPLIT_ON_VALUE}
   */
  public static boolean SPLIT_ON_VALUE = false;
}
