package ar.edu.unq.tpi.persit.ball.persistencia.criteria;

public interface Order<T>{
	
	public void asc(String property);
	
	public void desc(String property);
	
	public T getSpecificOrder();

}
