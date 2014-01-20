package interfaces;

import game.TrumpCard;

public interface IPlayer {

	public String getName();
	
	public int getID();
	
	public IPlayer setID(int id);
	
	public IHand getHand();
	
	public IPlayer setHand(IHand hand);

	public ICard playCard();
	
	public IBid getBid();
	
	public IPlayer getNextPlayer();
	
	public IPlayer setNextPlayer(IPlayer next_player);

	public IPlayer setBid(IBid bid);

	public IPlayer setRound(IRound round);

	public IRound getRound();

	ITeam getTeam();

	IPlayer setTeam(ITeam team);
}
