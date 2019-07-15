package com.frijolie.cards.blackjack.model.cards;

/**
 * An enumeration of all possible {@link Card} {@code Suit} values.
 *
 * <p>Each card in the game will be required to have a specific {@code Suit}.
 * The Suit will identify each card with a {@code name}, {@code letter}, and
 * {@code symbol}.
 *
 * <p>The order in which these constants are declared is important. The ordinal
 * values are used to compare one {@code Card} with another.
 *
 * @see Card
 */
public enum Suit {
  CLUBS('♣', "C"),
  DIAMONDS('♦', "D"),
  SPADES('♠', "S"),
  HEARTS('♥', "H");

  private final String letter;
  private final char symbol;

  /**
   * Constructor. Need to supply a {@link #symbol}, and {@link #letter}
   * @param symbol the symbol of the Suit, as a single character graphic
   * @param letter the letter of the Suit, as a single letter
   */
  Suit(final char symbol, final String letter) {
    this.letter = letter;
    this.symbol = symbol;
  }

  /**
   * Returns a representation of the Suit as a single letter. For example, "C"
   */
  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }

  /**
   * Returns the Suit, represented as a single letter. For example, "C"
   * @return a String representation of the Suit
   */
  public final String getLetter() {
    return letter;
  }

  /**
   * Returns the Suit, represented as a single character, a symbol. For example,
   * '♣'
   * @return a character, a symbol, representation of the Suit
   */
  public final char getSymbol() {
    return symbol;
  }
}