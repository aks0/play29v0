package game;

/**
 * An object of type Hand represents a hand of cards.  The
 * cards belong to the class Card.  A hand is empty when it
 * is created, and any number of cards can be added to it.
 */

import interfaces.ICard;
import interfaces.IHand;

import java.util.ArrayList;

public class Hand implements IHand {

   private ArrayList<ICard> hand;   // The cards in the hand.

   /**
    * Create a hand that is initially empty.
    */
   public Hand() {
      hand = new ArrayList<ICard>();
   }
   
   /**
    * Remove all cards from the hand, leaving it empty.
    */
   public void clear() {
      hand.clear();
   }
   
   /**
    * Add a card to the hand.  It is added at the end of the current hand.
    * @param c the non-null card to be added.
    * @throws NullPointerException if the parameter c is null.
    */
   public void addCard(ICard c) {
      if (c == null)
         throw new NullPointerException("Can't add a null card to a hand.");
      hand.add(c);
   }
   
   /**
    * Remove a card from the hand, if present.
    * @param c the card to be removed.  If c is null or if the card is not in 
    * the hand, then nothing is done.
    */
   public void removeCard(ICard c) {
      hand.remove(c);
   }
   
   /**
    * Remove the card in a specified position from the hand.
    * @param position the position of the card that is to be removed, where
    * positions are starting from zero.
    * @throws IllegalArgumentException if the position does not exist in
    * the hand, that is if the position is less than 0 or greater than
    * or equal to the number of cards in the hand.
    */
   public void removeCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand.remove(position);
   }

   /**
    * Returns the number of cards in the hand.
    */
   public int getCardCount() {
      return hand.size();
   }
   
   /**
    * Gets the card in a specified position in the hand.  (Note that this card
    * is not removed from the hand!)
    * @param position the position of the card that is to be returned
    * @throws IllegalArgumentException if position does not exist in the hand
    */
   public PlayingCard getCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return (PlayingCard)hand.get(position);
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same suit are
    * grouped together, and within a suit the cards are sorted by value.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortBySuit() {
      ArrayList<ICard> newHand = new ArrayList<ICard>();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         PlayingCard c = (PlayingCard)hand.get(0);  // Minimal card.
         for (int i = 1; i < hand.size(); i++) {
            PlayingCard c1 = (PlayingCard)hand.get(i);
            if (c1.getSuit().compareTo(c.getSuit()) < 0 ||
                (c1.getSuit() == c.getSuit() &&
                c1.getRank() < c.getRank())) {
                pos = i;
                c = c1;
            }
         }
         hand.remove(pos);
         newHand.add(c);
      }
      hand = newHand;
   }
   
   /**
    * Sorts the cards in the hand so that cards of the same value are
    * grouped together.  Cards with the same value are sorted by suit.
    * Note that aces are considered to have the lowest value, 1.
    */
   public void sortByValue() {
      ArrayList<ICard> newHand = new ArrayList<ICard>();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         PlayingCard c = (PlayingCard)hand.get(0);  // Minimal card.
         for (int i = 1; i < hand.size(); i++) {
            PlayingCard c1 = (PlayingCard)hand.get(i);
            if (c1.getRank() < c.getRank() ||
                (c1.getRank() == c.getRank() &&
                c1.getSuit().compareTo(c.getSuit()) < 0)) {
                pos = i;
                c = c1;
            }
         }
         hand.remove(pos);
         newHand.add(c);
      }
      hand = newHand;
   }
   
   @Override
   public String toString() {
	   StringBuffer hand_buffer = new StringBuffer();
	   for (ICard card : this.hand) {
		   hand_buffer.append(card.toString());
		   hand_buffer.append("\n");
	   }
	   return hand_buffer.toString();
   }

	@Override
	public boolean containsCard(ICard card) {
		return hand.contains(card);
	}

	@Override
	public int size() {
		return this.hand.size();
	}
}
