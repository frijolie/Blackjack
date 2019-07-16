package com.frijolie.cards.blackjack.model.cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Deck is a concrete class used to populate all possible {@link BlackjackCard}s in the game.
 *
 * <p>A {@code Deck} should contain all permutations of {@link Rank} and {@link Suit}. A single deck
 * should contain 52 cards.
 *
 * <p>Populating more than one deck is not necessary in this implementation. It is possible to
 * simply call {@link #getCards()} multiple times as it will pass a copy of the original deck.
 *
 * @see BlackjackCard
 * @see Rank
 * @see Suit
 */
class Deck {

  private final List<Card> deck;

  /** No-arg constructor. Creates a collection and populates the deck. */
  public Deck() {
    deck = new ArrayList<>();
    populateDeck();
  }

  /**
   * Populates the deck will all permutations of Rank and Suit. Loads the deck with 52 cards total.
   */
  private void populateDeck() {
    Arrays.stream(Suit.values())
        .forEach(
            suit ->
                Arrays.stream(Rank.values())
                    .map(rank -> new BlackjackCard(rank, suit))
                    .forEach(deck::add));
  }

  /**
   * Returns a copy of the deck which contains 52 cards.
   *
   * @return a copy of the full deck
   */
  public final List<Card> getCards() {
    return List.copyOf(deck);
  }
}
