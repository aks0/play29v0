package interfaces;

public interface IHand {

	public void clear();
	
	public boolean containsCard(ICard c);
	
	public void addCard(ICard c);

	public void removeCard(ICard c);
	
	public void removeCard(int position);
	
	public int getCardCount();
	
	public ICard getCard(int position);
	
	public void sortBySuit();
	
	public void sortByValue();
	
	public String toString();
	
	public int size();
}
