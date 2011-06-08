package ar.edu.unq.tpi.persit.ball.persistencia.criteria;

import java.util.List;

public interface Restictions<T, C extends Criteria<?>> {
	
	
	public Restictions<T,C> add(C criterion);
	
	public Restictions<T,C> addOrder(Order<?> order);
	public Restictions<T,C> setMaxresults(int maxResults);
	public Restictions<T,C> setFirstResult(int firstResult);
	
	public Restictions<T,C> setCacheable(boolean cacheable);
	
	public T uniqueResult();
	
	public List<T> list();

}
