package game;
import input.Input;
import interfaces.IBid;
import interfaces.IDeck;
import interfaces.IPair;
import interfaces.IPlayer;
import interfaces.IRound;
import interfaces.ITeam;
import interfaces.ITrump;
import interfaces.input.IInput;

/**
 * 
 */

/**
 * @author hissar
 *
 */
public class Round implements IRound {

	private final IDeck deck;
	private ITrump trump = null;
	
	Round() {
		deck = new Deck(32);
	}
	
	/* (non-Javadoc)
	 * @see interfaces.IRound#getDeck()
	 */
	@Override
	public IDeck getDeck() {
		return deck;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRound#getTrump()
	 */
	@Override
	public ITrump getTrump() {
		if (this.trump == null) {
			throw new NullPointerException("Trump has not yet been set");
		}
		return this.trump;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRound#getBid()
	 */
	@Override
	public IBid getBid() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRound#getTeams()
	 */
	@Override
	public IPair<ITeam, ITeam> getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRound setTrump(IPlayer bid_winner) {
		if (this.trump == null) {
			IInput in = new Input();
			this.trump = in.getTrump(bid_winner);
		}
		return this;
	}
}
