package enums;


public enum PlayingCardValue implements Comparable<PlayingCardValue> {
	SEVEN (0, 0),
	EIGHT (1, 0),
	QUEEN (2, 0),
	KING (3, 0),
	TEN (4, 1),
	ACE (5, 1),
	NINE (6, 2),
	JACK (7, 3);
	
	private final int rank;
	private final int points;
	
	/**
	 * Creating a new Rank is not allowed. Only those available are defined
	 * above.
	 * @param rank
	 */
	private PlayingCardValue(int rank, int points) {
		this.rank = rank;
		this.points = points;
	}
	
	@Override
	public String toString() {
        switch (this) {
        case ACE: return "Ace";
        case JACK: return "Jack";
        case QUEEN: return "Queen";
        case KING: return "King";
        case SEVEN: return "7";
        case EIGHT: return "8";
        case NINE: return "9";
        case TEN: return "10";
        default:
        	throw new IllegalStateException("Illegal playing card found.");
        }
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public int getPoints() {
		return this.points;
	}
}
