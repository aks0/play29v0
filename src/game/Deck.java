package game;
import enums.PlayingCardValue;
import enums.Suit;
import interfaces.IDeck;

/**
 *  An object of type Deck represents a deck of playing cards.  The deck
 *  is a regular poker deck that contains 52 regular cards and that can
 *  also optionally include two Jokers.
 */
public class Deck implements IDeck {
   
   /**
    * An array of 52 or 54 cards.  A 54-card deck contains two Jokers,
    * in addition to the 52 cards of a regular poker deck.
    */
   private PlayingCard[] deck;
   
   /**
    * Keeps track of the number of cards that have been dealt from
    * the deck so far.
    */
   private int cardsUsed;
   
   /**
    * Constructs a poker deck of playing cards, The deck of 29 contains
    * the usual 32 cards.   Initially the cards
    * are in a sorted order.  The shuffle() method can be called to
    * randomize the order.
    */
   public Deck(int deck_size) {
	   deck = new PlayingCard[deck_size];
	   int cardCt = 0; // How many cards have been created so far.
	   for (Suit suit : Suit.values()) {
		   for(PlayingCardValue rank : PlayingCardValue.values()) {
			   deck[cardCt] = new PlayingCard(rank, suit);
			   cardCt++;
		   }
	   }
	   this.shuffle();
	   cardsUsed = 0;
   }
   
   /**
    * Put all the used cards back into the deck (if any), and
    * shuffle the deck into a random order.
    */
   public void shuffle() {
      for ( int i = deck.length-1; i > 0; i-- ) {
         int rand = (int)(Math.random()*(i+1));
         PlayingCard temp = deck[i];
         deck[i] = deck[rand];
         deck[rand] = temp;
      }
      cardsUsed = 0;
   }
   
   /**
    * As cards are dealt from the deck, the number of cards left
    * decreases.  This function returns the number of cards that
    * are still left in the deck.  The return value would be
    * 52 or 54 (depending on whether the deck includes Jokers)
    * when the deck is first created or after the deck has been
    * shuffled.  It decreases by 1 each time the dealCard() method
    * is called.
    */
   public int numCardsLeft() {
      return deck.length - cardsUsed;
   }
   
   /**
    * Removes the next card from the deck and return it.  It is illegal
    * to call this method if there are no more cards in the deck.  You can
    * check the number of cards remaining by calling the cardsLeft() function.
    * @return the card which is removed from the deck.
    * @throws IllegalStateException if there are no cards left in the deck
    */
   public PlayingCard dealCard() {
      if (cardsUsed == deck.length)
         throw new IllegalStateException("No cards are left in the deck.");
      cardsUsed++;
      return deck[cardsUsed - 1];
      // Programming note:  Cards are not literally removed from the array
      // that represents the deck.  We just keep track of how many cards
      // have been used.
   }
} // end class Deck
