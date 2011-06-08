package ar.edu.unq.tpi.persist.ball.domain.utils;

public class Par<X ,Y> {
	private X first;
	private Y second;
	
	public Par(X x, Y y) {
		this.first = x;
		this.second = y;
	}
	
	public void setFirst(X x) {
		this.first = x;
	}
	public X getFirst() {
		return first;
	}
	
	public void setSecond(Y y) {
		this.second = y;
	}
	public Y getSecond() {
		return second;
	}
}
