package interfaces;

public interface IRound {

	public IDeck getDeck();
	
	public ITrump getTrump();
	
	public IBid getBid();
	
	public IPair<ITeam, ITeam> getTeams();
	
	public IRound setTrump(IPlayer bid_winner);
}
