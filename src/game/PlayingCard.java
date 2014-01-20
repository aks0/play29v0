package game;

import test.Util;
import enums.PlayingCardValue;
import enums.Suit;
import interfaces.ICard;

/**
 * http://math.hws.edu/javanotes/c5/s4.html
 * 
 * An object of type Card represents a playing card from a
 * standard Poker deck, including Jokers.  The card has a suit, which
 * can be spades, hearts, diamonds, clubs, or joker.  A spade, heart,
 * diamond, or club has one of the 13 values: ace, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, jack, queen, or king.  Note that "ace" is considered to be
 * the smallest value.  A joker can also have an associated value; 
 * this value can be anything and can be used to keep track of several
 * different jokers.
 */

public class PlayingCard implements ICard, Comparable<ICard> {
   
   /**
    * This card's suit, one of the constants SPADES, HEARTS, DIAMONDS,
    * or CLUBS.  The suit cannot be changed after the card is
    * constructed.
    */
   private final Suit suit; 
   
   /**
    * The card's value.  For a normal card, this is one of the values
    * 1 through 13, with 1 representing ACE. The value cannot be changed after
    * the card is constructed.
    */
   private final PlayingCardValue rank;
   
   /**
    * Creates a card with a specified suit and value.
    * @param theValue the value of the new card.  For a regular card (non-joker),
    * the value must be in the range 1 through 13, with 1 representing an Ace.
    * You can use the constants Card.ACE, Card.JACK, Card.QUEEN, and Card.KING.  
    * @param theSuit the suit of the new card.
    */
   public PlayingCard(PlayingCardValue rank, Suit suit) {
      this.rank = rank;
      this.suit = suit;
   }

   /**
    * Returns the suit of this card.
    * @returns the suit
    */
   public Suit getSuit() {
      return suit;
   }
   
   /**
    * Returns the rank of this card.
    * @return the rank
    */
   public int getRank() {
      return rank.getRank();
   }
   
   /**
    * Returns a String representation of the card's suit.
    * @return one of the strings "Spades", "Hearts", "Diamonds", "Clubs"
    */
   public String getSuitAsString() {
	   return suit.toString();
   }
   
   /**
    * Returns a String representation of the card's value.
    * @return for a regular card, one of the strings "Ace", "2",
    * "3", ..., "10", "Jack", "Queen", or "King".
    */
   public String getValueAsString() {
	   return rank.toString();
   }
   
   /**
    * Returns a string representation of this card, including both
    * its suit and its value.  Sample return values
    * are: "Queen of Hearts", "10 of Diamonds", "Ace of Spades".
    */
   public String toString() {
         return getValueAsString() + " of " + getSuitAsString();
   }
   
   @Override
   public boolean equals(Object other) {
	   PlayingCard other_card = (PlayingCard) other;
	   return this.suit == other_card.suit && this.rank == other_card.rank;
   }

   /**
    * Note: this comparator imposes orderings that are inconsistent with equals
    */
	@Override
	public int compare(ICard card1, ICard card2) {
		PlayingCard pcard1 = (PlayingCard) card1;
		PlayingCard pcard2 = (PlayingCard) card2;
		return pcard1.rank.compareTo(pcard2.rank);
	}
	
	@Override
	public int compareTo(ICard other) {
		return compare(this, other);
	}

	/**
	 * Constructs a new PlayingCard given the suit and value of the card as
	 * Strings
	 * @param suit
	 * @param value
	 * @return a new PlayingCard
	 */
	public static PlayingCard fromString(String sSuit, String sValue) {
		PlayingCardValue value = null;
		Suit suit = null;
		for (Suit iter_suit : Suit.values()) {
			if (sSuit.equals(iter_suit.toString().substring(0, sSuit.length()))) {
				suit = iter_suit;
				break;
			}
		}
		for (PlayingCardValue iter_value : PlayingCardValue.values()) {
			String str_value = iter_value.toString();
			if (str_value.length() >= sValue.length() &&
				sValue.equals(str_value.substring(0, sValue.length()))) {
				value = iter_value;
				break;
			}
		}
		return new PlayingCard(value, suit);
	}

	@Override
	public int getPoints() {
		return this.rank.getPoints();
	}
} // end class Card
