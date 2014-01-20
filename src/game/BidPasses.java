package game;

public class BidPasses {

	private int numBidPasses;
	
	BidPasses (int num_bid_passes) {
		this.numBidPasses = num_bid_passes;
	}
	
	public BidPasses() {
		this.numBidPasses = 0;
	}

	public int getNumPasses() {
		return this.numBidPasses;
	}
	
	public BidPasses incrPass() {
		this.numBidPasses++;
		return this;
	}
	
	public BidPasses reset() {
		this.numBidPasses = 0;
		return this;
	}
}
