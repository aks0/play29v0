package test;

import game.Game;
import game.Player;
import input.Input;
import interfaces.ICard;
import interfaces.IGame;
import interfaces.IPlayer;

public class GetCardFromPlayerTest {

	public static void testGetCardFromPlayer() {
		IPlayer[] players = new IPlayer[4];
		players[0] = new Player("Akshay");
		players[1] = new Player("Sayandeep");
		players[2] = new Player("Argha");
		players[3] = new Player("Mangal");
		
		IGame game = new Game(players, 0);
		game.dealCards(8);
		
		Util.println(players[0].getHand().toString());
		Input in = new Input();
		ICard card = in.getCardFromPlayer(players[0]);
		Util.println(card.toString());
		in.close();
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) {
		testGetCardFromPlayer();
		Util.println("PASS: GetCardFromPlayerTest");
	}

}
