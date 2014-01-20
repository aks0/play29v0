package interfaces;

public interface IBid extends Comparable<IBid> {

	public IBid incrBid();
	
	public int getBidValue();
	
	public IBid pass();
	
	public boolean isPass();
	
	public IBid matchBid(IBid oBid);
	
	public boolean isNull();
	
	public IBid clearBid();
}
