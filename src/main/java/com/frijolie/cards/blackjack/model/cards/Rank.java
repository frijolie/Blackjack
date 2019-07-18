package com.frijolie.cards.blackjack.model.cards;

/**
 * An enumeration to establish all possible {@link Card} ranks.
 *
 * <p>Each Card in the game will be required to have a specific {@code Rank}. Every rank will
 * identify the card as a letter and a numeric value.
 *
 * <p>The order in which these Ranks are declared is important. The ordinals are used to sort and
 * compare to other cards. The higher ordinal values are considered to be superior to those with a
 * lower value.
 *
 * @see Card
 */
public enum Rank {
  TWO("2", 2),
  THREE("3", 3),
  FOUR("4", 4),
  FIVE("5", 5),
  SIX("6", 6),
  SEVEN("7", 7),
  EIGHT("8", 8),
  NINE("9", 9),
  TEN("T", 10),
  JACK("J", 10),
  QUEEN("Q", 10),
  KING("K", 10),
  ACE("A", 11);

  private final String letter;
  private final int value;

  /**
   * Constructor. Must provide a {@link #letter}, and {@link #value}
   *
   * @param letter represents the Rank as a letter. For example, 7
   * @param value  represents the Rank as a numerical value. For example, 10
   */
  Rank(final String letter, final int value) {
    this.letter = letter;
    this.value = value;
  }

  /**
   * Returns a representation of the Rank as a word. For example, "Queen"
   *
   * @return the Rank as a word
   */
  @Override
  public String toString() {
    return name().charAt(0) + name().substring(1).toLowerCase();
  }

  /**
   * Returns the numerical value of the Rank. For example, 8
   *
   * @return the numerical value of the Rank.
   */
  public final int getValue() {
    return value;
  }

  /**
   * Returns the Rank as a single letter. For example, 'J'
   *
   * @return the Rank as a single letter
   */
  public final String getLetter() {
    return letter;
  }

  /**
   * Returns the Rank enumeration constant value. For example, Rank.TEN
   *
   * @return the constant value
   */
  public final Rank getRank() {
    return this;
  }
}
