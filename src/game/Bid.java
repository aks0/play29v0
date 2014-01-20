package game;

import output.Output;
import input.Input;
import interfaces.IBid;
import interfaces.IPlayer;

public class Bid implements IBid {

	private static final int PASS = -1;
	private static final IBid START_BID = new Bid(16);
	private static final int NULL = 0;
	private int bidValue;
	
	public static final IBid MAX_BID = new Bid(29);

	public Bid() {
		this.bidValue = NULL;
	}
	
	public Bid(int bid) {
		this.bidValue = bid;
	}

	@Override
	public IBid incrBid() {
		if (this.bidValue == PASS) {
			throw new IllegalArgumentException("This player has PASSed the bid.");
		}
		bidValue++;
		return this;
	}

	@Override
	public int getBidValue() {
		if (this.bidValue == PASS) {
			throw new IllegalArgumentException("This player has PASSed the bid.");
		}
		return this.bidValue;
	}

	@Override
	public IBid pass() {
		this.bidValue = PASS;
		return this;
	}

	@Override
	public IBid matchBid(IBid oBid) {
		this.bidValue = oBid.getBidValue();
		return this;
	}

	@Override
	public boolean isNull() {
		return bidValue == NULL;
	}

	private static IPlayer passAndClear(IPlayer passer, IPlayer winner,
			BidPasses bid_passes) {
		bid_passes.incrPass();
		passer.getBid().clearBid();
		return winner;
	}
	
	public static IPlayer resolveBids(IPlayer bidder1, IPlayer bidder2,
			BidPasses bid_passes) {
		if (bidder1.getBid().isNull()) {
			makeBid(bidder1, START_BID);
			if (bidder1.getBid().isPass()) {
				return passAndClear(bidder1, bidder2, bid_passes);
			}
		}
		if (bidder2.getBid().isNull()) {
			makeBid(bidder2, bidder1.getBid());
			if (bidder2.getBid().isPass()) {
				return passAndClear(bidder2, bidder1, bid_passes);
			}
		}
		bid_passes.reset();
		
		while(true) {
			makeOrMatchBid(bidder1, bidder2.getBid());
			if (bidder1.getBid().isPass()) {
				return passAndClear(bidder1, bidder2, bid_passes);
			}
			makeOrMatchBid(bidder2, bidder1.getBid());
			if (bidder2.getBid().isPass()) {
				return passAndClear(bidder2, bidder1, bid_passes);
			}
		}
	}

	private static void makeOrMatchBid(IPlayer bidder, IBid bid) {
		if (bidder.getBid().getBidValue() >= bid.getBidValue()) {
			makeBid(bidder, bid);
		} else {
			makeBid(bidder, new Bid(bid.getBidValue() - 1));
		}
	}

	private static void makeBid(IPlayer bidder, IBid minBid) {
		Input in = new Input();
		IBid new_bid = in.getBid(bidder, minBid);
		bidder.setBid(new_bid);
	}

	@Override
	public boolean isPass() {
		return this.bidValue == PASS;
	}

	@Override
	public int compareTo(IBid other) {
		return this.getBidValue() - other.getBidValue();
	}

	@Override
	public IBid clearBid() {
		this.bidValue = NULL;
		return this;
	}
}
