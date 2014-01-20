package input;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import output.Output;

import game.Bid;
import game.PlayingCard;
import game.TrumpCard;
import interfaces.IBid;
import interfaces.ICard;
import interfaces.IHand;
import interfaces.IPlayer;
import interfaces.ITrump;
import interfaces.input.IInput;

public class Input implements IInput {

	private Scanner in;

	public Input() {
		in = new Scanner(System.in);
	}
	
	@Override
	public String getNext() {
		if (in.hasNext()) {
			return in.next();
		} else {
			throw new NullPointerException("No more values to read.");
		}
	}

	@Override
	public int getNextInt() {
		if (in.hasNextInt()) {
			return in.nextInt();
		} else {
			throw new NullPointerException("No more integers to read.");
		}
	}

	@Override
	public IBid getBid(IPlayer bidder, IBid minBid) {
		if (minBid.compareTo(Bid.MAX_BID) == 0) {
			return new Bid().pass();
		}
		
		do {
			Output.print(String.format("%s, enter your bid (> %d, 0 to pass):",
					bidder.getName(), minBid.getBidValue()));
			int bid_value = in.nextInt();
			if (bid_value == 0) {
				return new Bid().pass();
			}
			IBid bid = new Bid(bid_value);
			if (bid.compareTo(minBid) <= 0 || bid.compareTo(Bid.MAX_BID) > 0) {
				Output.println(String.format("ERROR: enter bid greater than " +
					"%d and less than or equal to %d.", minBid.getBidValue(),
					Bid.MAX_BID.getBidValue()));
				continue;
			}
			return bid;
		} while (true);
	}

	@Override
	public ICard getCardFromPlayer(IPlayer player) {
		String format = "[Suit:Value] - [S, H, D, C : 7, 8, 9, 10, J, Q, K, A]";
		do {
			Output.println(String.format("%s, enter a card to play (%s). [0:0] " +
				"to open the Trump.", player.getName(), format));
			String str_card = in.next();
			StringTokenizer data = new StringTokenizer(str_card, ":");
			if (data.countTokens() != 2) {
				Output.println("ERROR: Invalid card seen.");
				continue;
			}
			
			String sSuit = data.nextToken();
			String sValue = data.nextToken();
			if (sSuit.equals("0") && sValue.equals("0")) {
				player.getRound().getTrump().open();
				continue;
			}
			ICard card = PlayingCard.fromString(sSuit, sValue);
			if (!player.getHand().containsCard(card)) {
				Output.println("ERROR: You do not have this card.");
				continue;
			}
			return card;
		} while (true);
	}

	/**
	 * For testing purposes.
	 */
	public ICard getCardFromPlayer(IPlayer player, boolean isRandom){
		if (!isRandom) {
			return this.getCardFromPlayer(player);
		}
		IHand hand = player.getHand();
		return hand.getCard(new Random().nextInt(hand.size()));
	}

	@Override
	public void close() {
		in.close();
	}

	@Override
	public ITrump getTrump(IPlayer player) {
		do {
			Output.println(String.format("%s, enter a trump to set [Suit:Value].",
					player.getName()));
			String str_card = in.next();
			StringTokenizer data = new StringTokenizer(str_card, ":");
			if (data.countTokens() != 2) {
				throw new IllegalArgumentException("Enter a valid card.");
			}
			
			try {
				ITrump trump = TrumpCard.fromString(data.nextToken(), data.nextToken());
				return trump;
			} catch (Exception e) {
				Output.println("ERROR: Invalid trump being set.");
			}
		} while(true);
	}
}
