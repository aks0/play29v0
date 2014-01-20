package game;

import enums.PlayingCardValue;
import enums.Suit;
import enums.TrumpCardValue;
import interfaces.ICard;
import interfaces.ITrump;

public class TrumpCard implements ICard, ITrump {

	private Suit suit;
	private TrumpCardValue rank;
	private boolean isOpen;

	public TrumpCard(TrumpCardValue rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
		this.isOpen = false;
	}

	@Override
	public boolean isReverse() {
		return rank.equals(TrumpCardValue.THREE);
	}

	@Override
	public boolean isNoTrump() {
		return rank.equals(TrumpCardValue.TWO);
	}

	@Override
	public boolean isSuitTrump() {
		return !(this.isNoTrump() || this.isReverse());
	}

	@Override
	public Suit getSuit() {
		return suit;
	}

	@Override
	public int compare(ICard card1, ICard card2) {
		TrumpCard tcard1 = (TrumpCard) card1;
		TrumpCard tcard2 = (TrumpCard) card2;
		return tcard1.rank.compareTo(tcard2.rank);
	}

	@Override
	public int getRank() {
		return rank.getRank();
	}

	@Override
	public String getSuitAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValueAsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITrump open() {
		this.isOpen = true;
		return this;
	}

	@Override
	public boolean isOpen() {
		return this.isOpen;
	}

	@Override
	public int compareTo(ICard o) {
		throw new UnsupportedOperationException("TrumpCards cannot be compared");
	}

	/**
	 * Constructs a new TrumpCard given the suit and value of the card as
	 * Strings
	 * @param suit
	 * @param value
	 * @return a new TrumpCard
	 */
	public static TrumpCard fromString(String sSuit, String sValue) {
		TrumpCardValue value = null;
		Suit suit = null;
		for (Suit iter_suit : Suit.values()) {
			if (sSuit.equals(iter_suit.toString().substring(0, sSuit.length()))) {
				suit = iter_suit;
				break;
			}
		}
		for (TrumpCardValue iter_value : TrumpCardValue.values()) {
			String str_value = iter_value.toString();
			if (str_value.length() >= sValue.length() &&
				sValue.equals(str_value.substring(0, sValue.length()))) {
				value = iter_value;
				break;
			}
		}
		if (value == null || suit == null) {
			throw new IllegalArgumentException("Invalid trump value and suit.");
		}
		return new TrumpCard(value, suit);
	}

	@Override
	public int getPoints() {
		throw new UnsupportedOperationException("Trump cards do not have points");
	}
}
