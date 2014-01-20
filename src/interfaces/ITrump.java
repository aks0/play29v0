package interfaces;

import enums.Suit;

public interface ITrump {

	public ITrump open();
	
	public boolean isOpen();
	
	public boolean isReverse();
	
	public boolean isNoTrump();
	
	public boolean isSuitTrump();
	
	public Suit getSuit();
}
