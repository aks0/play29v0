package game;

import java.util.Arrays;

import output.Output;

import enums.Suit;

import interfaces.ICard;
import interfaces.ISubRound;
import interfaces.ITrump;

public class SubRound implements ISubRound {

	private int id;
	private ICard[] pot;
	
	SubRound(int id) {
		this.id = id;
		pot = new ICard[4];
	}
	
	@Override
	public void addPotCard(ICard card, int player_id) {
		pot[player_id] = card;
	}

	@Override
	public ICard[] getPotCards() {
		return pot;
	}

	@Override
	public void clearPot() {
		Arrays.fill(pot, null);
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public String toString() {
		return Arrays.toString(pot);
	}
	
	private int handleNoTrumpWinner(ITrump trump) {
		int winning_card_index = 0;
		Suit base_suit = pot[winning_card_index].getSuit();
		for (int i = 1; i < pot.length; i++) {
			if (!pot[i].getSuit().equals(base_suit)) {
				continue;
			} else if (trump.isReverse()) {
				if (pot[i].compareTo(pot[winning_card_index]) < 0) {
					winning_card_index = i;
				}
			} else if (pot[i].compareTo(pot[winning_card_index]) > 0) {
				winning_card_index = i;
			}
		}
		Output.println("Winning Card Index = " + winning_card_index);
		return winning_card_index;
	}
	
	private int handleTrumpWinner(ITrump trump) {
		// find if somebody played a trump
		int trump_played_index = -1;
		for (int i = 0; i < pot.length; i++) {
			if (pot[i].getSuit().equals(trump.getSuit())) {
				trump_played_index = i;
				break;
			}
		}
		// nobody played a trump
		if (trump_played_index == -1) {
			return this.handleNoTrumpWinner(trump);
		}
		
		// somebody played a trump
		int winning_card_index = trump_played_index;
		for (int i = trump_played_index + 1; i < pot.length; i++) {
			if (!pot[i].getSuit().equals(trump.getSuit())) {
				continue;
			} else if (pot[i].compareTo(pot[winning_card_index]) > 0) {
				winning_card_index = i;
			}
		}
		Output.println("Winning Card Index = " + winning_card_index);
		return winning_card_index;
	}
	
	@Override
	public int getPotWinner(ITrump trump) {
		if (!trump.isOpen() || !trump.isSuitTrump()) {
			return this.handleNoTrumpWinner(trump);
		} else {
			return this.handleTrumpWinner(trump);
		}
	}
}
