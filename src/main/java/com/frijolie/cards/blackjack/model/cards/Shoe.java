package com.frijolie.cards.blackjack.model.cards;

import com.frijolie.cards.blackjack.model.game.GameRules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Shoe is a concrete class which represents the Shoe in a game of Blackjack.
 *
 * <p>All possible cards in the game originate in a {@link Deck}. The Shoe must contain {@link
 * GameRules#MIN_NUMBER_OF_DECKS} and not exceed the {@link GameRules#MAX_NUMBER_OF_DECKS}. After
 * all cards are populated in a deck, they are shuffled and placed in the Shoe.
 *
 * <p>The Dealer must deal all cards from the Shoe in First-In-First-Out (FIFO) order. After a
 * certain percentage of cards have been exhausted from the shoe it is repopulated and shuffled
 * again, stopping play to do so.
 *
 * @see Deck
 * @see BlackjackCard
 * @see Card
 * @see GameRules
 */
public class Shoe {

  private final List<Card> shoe;
  private final Deck deck;

  /**
   * Default no-arg constructor. Will populate the Shoe with the {@link
   * GameRules#DEFAULT_NUMBER_OF_DECKS} and shuffle the shoe.
   *
   * @see Deck
   * @see GameRules#DEFAULT_NUMBER_OF_DECKS
   */
  public Shoe() {
    this(GameRules.DEFAULT_NUMBER_OF_DECKS);
  }

  /**
   * Overloaded constructor. Populates the Shoe with a customizable number of decks. It must be more
   * than the {@link GameRules#MIN_NUMBER_OF_DECKS} and fewer than the {@link
   * GameRules#MAX_NUMBER_OF_DECKS}. After it has been populated, the shoe is shuffled.
   *
   * @param numberOfDecks the number of decks used to populate the shoe
   * @see Deck
   * @see GameRules
   */
  public Shoe(final int numberOfDecks) {
    shoe = new ArrayList<>();
    deck = new Deck();

    if (numberOfDecks < GameRules.MIN_NUMBER_OF_DECKS) {
      populate(GameRules.MIN_NUMBER_OF_DECKS);
    } else if (numberOfDecks > GameRules.MAX_NUMBER_OF_DECKS) {
      populate(GameRules.MAX_NUMBER_OF_DECKS);
    } else {
      populate(numberOfDecks);
    }

    shuffle();
  }

  /**
   * Populates the shoe with the requested number of decks.
   *
   * @param numberOfDecks to populate the shoe.
   */
  private void populate(final int numberOfDecks) {
    for (int i = 0; i < numberOfDecks; i++) {
      shoe.addAll(deck.getCards());
    }
  }

  /**
   * Shuffles the shoe.
   */
  private void shuffle() {
    Collections.shuffle(shoe);
  }

  /**
   * Removes and returns the first card at the bottom of the shoe. Will throw an exception if the
   * {@code Shoe} is empty.
   *
   * <p>Needs to be called each time a card is dealt to a player.
   *
   * @return a Card from the shoe.
   * @throws NoSuchElementException if the {@code Shoe} is empty
   */
  public final Card deal() {
    String errorMessage = "The shoe is empty. Therefore, a card cannot be dealt.";
    if (shoe.isEmpty()) {
      throw new NoSuchElementException(errorMessage);
    } else {
      return shoe.remove(0);
    }
  }

  /**
   * Returns the shoe.
   *
   * @return the shoe.
   */
  public final List<Card> getCards() {
    return shoe;
  }
}
