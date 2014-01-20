package interfaces;

import java.util.Comparator;

import enums.PlayingCardValue;
import enums.Suit;

public interface ICard extends Comparator<ICard>, Comparable<ICard> {

	public Suit getSuit();
	
	public int getRank();
	
	// String functions
	public String getSuitAsString();

	public String getValueAsString();
	
	public String toString();

	public int getPoints();
}
