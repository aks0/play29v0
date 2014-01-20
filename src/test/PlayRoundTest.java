package test;

import game.Game;
import game.Player;
import interfaces.IGame;
import interfaces.IPlayer;

public class PlayRoundTest {

	public static void testPlayRound() {
		IPlayer[] players = new IPlayer[4];
		players[0] = new Player("Akshay");
		players[1] = new Player("Sayandeep");
		players[2] = new Player("Argha");
		players[3] = new Player("Mangal");
		
		IGame game = new Game(players, 0);
		game.playRound();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testPlayRound();
		Util.println("PASS: PlayRoundTest");
	}
}
