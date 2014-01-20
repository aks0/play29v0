package game;
import interfaces.IBid;
import interfaces.ICard;
import interfaces.IHand;
import interfaces.IPlayer;
import interfaces.IRound;
import interfaces.ITeam;
import interfaces.ITrump;

/**
 * 
 */

/**
 * @author hissar
 *
 */
public class Player implements IPlayer {

	private final String name;
	private IHand hand;
	private IPlayer nextPlayer;
	private IBid bid = null;
	private int id = -1;
	private IRound round = null;
	private ITeam team = null;

	public Player(String name) {
		this.name = name;
		this.hand = new Hand();
	}
	
	/* (non-Javadoc)
	 * @see interfaces.IPlayer#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see interfaces.IPlayer#getHand()
	 */
	@Override
	public IHand getHand() {
		return hand;
	}

	/* (non-Javadoc)
	 * @see interfaces.IPlayer#setHand()
	 */
	@Override
	public IPlayer setHand(IHand hand) {
		this.hand = hand;
		return this;
	}

	/* (non-Javadoc)
	 * @see interfaces.IPlayer#playCard()
	 */
	@Override
	public ICard playCard() {
		return null;
	}

	/* (non-Javadoc)
	 * @see interfaces.IPlayer#getBid(interfaces.IBid)
	 */
	@Override
	public IBid getBid() {
		if (this.bid == null) {
			this.bid = new Bid();
		}
		return bid;
	}

	@Override
	public IPlayer getNextPlayer() {
		return this.nextPlayer;
	}

	@Override
	public IPlayer setNextPlayer(IPlayer next_player) {
		this.nextPlayer = next_player;
		return this;
	}

	@Override
	public IPlayer setBid(IBid bid) {
		this.bid = bid;
		return this;
	}

	@Override
	public IPlayer setID(int id) {
		this.id = id;
		return this;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public IPlayer setRound(IRound round) {
		this.round = round;
		return this;
	}

	@Override
	public IRound getRound() {
		return this.round;
	}
	
	@Override
	public IPlayer setTeam(ITeam team) {
		this.team = team;
		return this;
	}
	
	@Override
	public ITeam getTeam() {
		return this.team;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
}
