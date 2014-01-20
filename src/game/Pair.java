package game;

import interfaces.IPair;

public class Pair<T1, T2> implements IPair<T1, T2> {

	private T1 e1;
	private T2 e2;
	
	Pair(T1 e1, T2 e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public T1 getFirst() {
		return e1;
	}

	@Override
	public T2 getSecond() {
		// TODO Auto-generated method stub
		return e2;
	}

}
