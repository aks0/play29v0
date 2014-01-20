package interfaces;

public interface ISubRound {
	
	public int getID();
	
	public void addPotCard(ICard card, int player_id);

	public ICard[] getPotCards();
	
	public void clearPot();

	int getPotWinner(ITrump trump);

}
