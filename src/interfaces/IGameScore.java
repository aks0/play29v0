package interfaces;

public interface IGameScore {

	// -6 to +6
	public int getPoints();
	
	// <# red sets, # black sets>
	public IPair<Integer, Integer> getSets();

	ITeam getTeam();

	IGameScore reset();

	IGameScore incrBlackSets();

	IGameScore incrRedSets();

	IGameScore addPoints(ITeam otherTeam, int p);
}
