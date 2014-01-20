package interfaces;

public interface ITeam {

	public IPair<IPlayer, IPlayer> getPlayers();
	
	public int getRoundPoints();
	
	public IGameScore getGameScore();

	public ITeam addRoundPoints(ICard[] potCards);

	public ITeam addRoundPoints(int i);
}
