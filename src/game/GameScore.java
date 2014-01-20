package game;

import interfaces.IGameScore;
import interfaces.IPair;
import interfaces.ITeam;

public class GameScore implements IGameScore {

	private int points;
	private int redSets;
	private int blackSets;
	private ITeam team;
	
	public GameScore(ITeam team) {
		this.points = 0;
		this.redSets = 0;
		this.blackSets = 0;
		this.team = team;
	}
	
	@Override
	public int getPoints() {
		return this.points;
	}

	@Override
	public IPair<Integer, Integer> getSets() {
		return new Pair<Integer, Integer>(this.redSets, this.blackSets);
	}
	
	@Override
	public IGameScore addPoints(ITeam otherTeam, int p) {
		this.points += p;
		if (points <= -6) {
			this.incrBlackSets();
			this.reset();
			otherTeam.getGameScore().reset();
		} else if (points >= 6) {
			this.reset();
			otherTeam.getGameScore().incrRedSets();
			otherTeam.getGameScore().reset();
		}
		return this;
	}
	
	@Override
	public ITeam getTeam() {
		return this.team;
	}
	
	@Override
	public IGameScore reset() {
		this.points = 0;
		return this;
	}
	
	@Override
	public IGameScore incrBlackSets() {
		this.blackSets++;
		return this;
	}
	
	@Override
	public IGameScore incrRedSets() {
		this.redSets++;
		return this;
	}
}
