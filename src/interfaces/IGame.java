package interfaces;

public interface IGame {

	public IGame dealCards(int n);
	
	public IGame playRound();
	
	public IGame printPlayersCards();
	
	public IPlayer getDealer();
	
	public IGame changeDealer();
}
