package enums;


public enum TrumpCardValue {
	TWO (2),
	THREE (3),
	FOUR (4),
	FIVE (5);
	
	private final int rank;
	
	/**
	 * Creating a new Rank is not allowed. Only those available are defined
	 * above.
	 * @param rank
	 */
	private TrumpCardValue(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return String.valueOf(rank);
	}

	public int getRank() {
		return this.rank;
	}
}
