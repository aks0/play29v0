package game;

import output.Output;
import interfaces.ICard;
import interfaces.IGameScore;
import interfaces.IPair;
import interfaces.IPlayer;
import interfaces.ITeam;

public class Team implements ITeam {

	private IPlayer player1;
	private IPlayer player2;
	private int roundPoints = 0;
	private IGameScore gameScore;
	
	Team(IPlayer player1, IPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
		gameScore = new GameScore(this);
	}
	
	@Override
	public IPair<IPlayer, IPlayer> getPlayers() {
		return new Pair<IPlayer, IPlayer>(player1, player2);
	}

	@Override
	public int getRoundPoints() {
		return this.roundPoints;
	}

	@Override
	public IGameScore getGameScore() {
		return this.gameScore;
	}

	@Override
	public ITeam addRoundPoints(ICard[] potCards) {
		int points = 0;
		for (int i = 0; i < potCards.length; i++) {
			points += potCards[i].getPoints();
		}
		this.addRoundPoints(points);
		return this;
	}

	@Override
	public String toString() {
		return "[" + player1.toString() + ", " + player2.toString() + "]";
	}

	@Override
	public ITeam addRoundPoints(int points) {
		this.roundPoints += points;
		return this;
	}
}
