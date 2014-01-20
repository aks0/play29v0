package game;
import java.util.Random;

import enums.Suit;
import enums.TrumpCardValue;
import output.Output;
import test.Util;
import input.Input;
import interfaces.IBid;
import interfaces.ICard;
import interfaces.IDeck;
import interfaces.IGame;
import interfaces.IHand;
import interfaces.IPair;
import interfaces.IPlayer;
import interfaces.IRound;
import interfaces.ITeam;
import interfaces.ITrump;


public class Game implements IGame {

	private IRound round;
	private IPlayer[] players;
	private IPlayer dealer;
	private IPlayer chanceToken;
	private IPair<ITeam, ITeam> teams = null;
	
	public Game (IPlayer[] players, int dealer) {
		this.round = new Round();
		this.players = players;
		// change after bidding is done
		this.dealer = players[new Random().nextInt(players.length)];
		this.chanceToken = null;
		this.setPlayerIDs();
		this.setNextPlayers();
		this.setTeams();
	}
	
	private void setTeams() {
		ITeam team1 = new Team(players[0], players[2]);
		ITeam team2 = new Team(players[1], players[3]);
		this.teams = new Pair<ITeam, ITeam>(team1, team2);
		players[0].setTeam(team1);
		players[1].setTeam(team2);
		players[2].setTeam(team1);
		players[3].setTeam(team2);
	}

	private void setPlayerIDs() {
		for (int i = 0; i < players.length; i++) {
			players[i].setID(i);
		}
	}

	private IGame setNextPlayers() {
		for (int i = 0; i < players.length - 1; i++) {
			players[i].setNextPlayer(players[i+1]);
		}
		players[players.length-1].setNextPlayer(players[0]);
		return this;
	}
	
	/**
	 * Fails the following case:
	 * A:17, S:P, G:18, A:P, M:19, G:P, A:again, it should be S's turn.
	 * @return
	 */
	private IPlayer resolveBids() {
		IPlayer bidder1 = dealer.getNextPlayer();
		IPlayer bidder2 = bidder1.getNextPlayer();
		BidPasses bid_passes = new BidPasses();
		
		while(bid_passes.getNumPasses() < 3) {
			IPlayer bidder3 = bidder2.getNextPlayer();
			bidder1 = Bid.resolveBids(bidder1, bidder2, bid_passes);
			bidder2 = bidder3;
		}
		return bidder1;
	}

	@Override
	public IGame dealCards(int n) {
		IDeck deck = this.round.getDeck();
		for (int i = 0; i < players.length; i++) {
			IHand hand = players[i].getHand();
			for (int j = 0; j < n; j++) {
				hand.addCard(deck.dealCard());
			}
		}
		return this;
	}
	
	@Override
	public IGame playRound() {
		this.dealCards(4);
		IPlayer bid_winner = this.resolveBids();
		Output.println(String.format("BidWinner: %s (%d)",
				bid_winner.getName(), bid_winner.getBid().getBidValue()));
		this.chanceToken = bid_winner;
		
		// set new trump
		this.round.setTrump(bid_winner);
		this.setRoundForPlayers();
		
		this.dealCards(4);
		this.printPlayersCards();
		
		SubRound sub_round = new SubRound(0);
		while (sub_round.getID() < 8) {
			Output.println(String.format("ChanceToken with: %s",
					this.chanceToken.getName()));
			this.playSubRound(sub_round);
			sub_round = new SubRound(sub_round.getID() + 1);
		}
		
		this.addRoundPoints(bid_winner);
		
		// comments to print round/game points
		Output.println(String.format("Team: %s, RoundPts: %d, GamePts: %d",
				teams.getFirst().toString(), teams.getFirst().getRoundPoints(),
				teams.getFirst().getGameScore().getPoints()));
		Output.println(String.format("Team: %s, RoundPts: %d, GamePts: %d",
				teams.getSecond().toString(), teams.getSecond().getRoundPoints(),
				teams.getSecond().getGameScore().getPoints()));
		return this;
	}
	
	private void addRoundPoints(IPlayer bid_winner) {
		int bidding = bid_winner.getBid().getBidValue();
		ITeam defendTeam = bid_winner.getTeam();
		ITeam attackTeam = bid_winner.getNextPlayer().getTeam();
		int factor = bidding <= 20 ? 1 : 2;
		
		// team defended it's bid
		if (defendTeam.getRoundPoints() >= bidding) {
			defendTeam.getGameScore().addPoints(attackTeam, 1 * factor);
		// team failed to make even half the points of the bid
		} else if (defendTeam.getRoundPoints() < Math.ceil(bidding/2.0)) {
			defendTeam.getGameScore().addPoints(attackTeam, -2 * factor);
		// team made at least half the points of the bid
		} else {
			defendTeam.getGameScore().addPoints(attackTeam, -1 * factor);
		}
	}

	private void setRoundForPlayers() {
		for (int i = 0; i < players.length; i++) {
			players[i].setRound(this.round);
		}
	}

	private void playSubRound(SubRound sub_round) {
		Input in = new Input();
		IPlayer currentPlayer = this.chanceToken;
		for (int i = 0; i < players.length; i++) {
			ICard card = in.getCardFromPlayer(currentPlayer, true);
			sub_round.addPotCard(card, i);
			currentPlayer.getHand().removeCard(card);
			currentPlayer = currentPlayer.getNextPlayer();
		}
		// rm: comments for testing
		Output.println(String.format("Pot#%d: %s",
				sub_round.getID(), sub_round.toString()));
		int winner = sub_round.getPotWinner(this.round.getTrump().open());

		// update the chanceToken
		int i = 0;
		while (i < winner) {
			this.chanceToken = this.chanceToken.getNextPlayer();
			i++;
		}
		Output.println(String.format("PotWinner: %s(%d)\n",
				this.chanceToken.getName(), winner));
		
		// add points to the winning team
		chanceToken.getTeam().addRoundPoints(sub_round.getPotCards());
		// add last sub-round 1 point
		if (sub_round.getID() == 7) {
			chanceToken.getTeam().addRoundPoints(1);
		}
	}

	@Override
	public IGame printPlayersCards() {
		for (int i = 0; i < players.length; i++) {
			Output.println(String.format("%s:\n%s",
					players[i].getName(),players[i].getHand().toString()));
		}
		return this;
	}

	@Override
	public IPlayer getDealer() {
		return this.dealer;
	}

	@Override
	public IGame changeDealer() {
		this.dealer = this.dealer.getNextPlayer();
		return this;
	}
}
