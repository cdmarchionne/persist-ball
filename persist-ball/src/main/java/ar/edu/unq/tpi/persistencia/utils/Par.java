package ar.edu.unq.tpi.persistencia.utils;

public class Par<X ,Y> {
	private X x;
	private Y y;
	
	public Par(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	public void setY(Y y) {
		this.y = y;
	}
	public Y getY() {
		return y;
	}
	public void setX(X x) {
		this.x = x;
	}
	public X getX() {
		return x;
	}
}
