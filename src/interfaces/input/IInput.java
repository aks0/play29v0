package interfaces.input;

import game.Player;
import interfaces.IBid;
import interfaces.ICard;
import interfaces.IPlayer;
import interfaces.ITrump;

public interface IInput {

	public String getNext();
	
	public int getNextInt();
	
	public void close();

	ICard getCardFromPlayer(IPlayer players);

	IBid getBid(IPlayer bidder, IBid minBid);

	public ITrump getTrump(IPlayer player);
}
