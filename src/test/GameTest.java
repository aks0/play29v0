package test;

import java.util.HashSet;

import game.Deck;
import game.Game;
import game.Player;
import interfaces.ICard;
import interfaces.IDeck;
import interfaces.IGame;
import interfaces.IHand;
import interfaces.IPlayer;

public class GameTest {
	
	public static void testDealCards() throws Exception {
		IPlayer[] players = new IPlayer[4];
		players[0] = new Player("Akshay");
		players[1] = new Player("Sayandeep");
		players[2] = new Player("Argha");
		players[3] = new Player("Mangal");
		
		IGame game = new Game(players, 0);
		game.dealCards(8);
		
		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < players.length; i++) {
			IHand hand = players[i].getHand();
			for (int j = 0; j < hand.getCardCount(); j++) {
				set.add(hand.getCard(j).toString());
			}
		}
		
		IDeck deck = new Deck(32);
		for (int i = 0; i < deck.numCardsLeft(); i++) {
			ICard card = deck.dealCard();
			if (!set.contains(card.toString())) {
				throw new Exception("Missing card: " + card.toString());
			}
		}
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		testDealCards();
		Util.println("PASS: GameTest");
	}
}
