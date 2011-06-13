package ar.edu.unq.tpi.persit.ball.persistencia.criteria;

public interface Order<T>{
	
	public Order<T> asc(String property);
	
	public Order<T> desc(String property);
	
	public T getSpecificOrder();

}
