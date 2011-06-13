package ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;

import ar.edu.unq.tpi.persit.ball.persistencia.criteria.Order;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.Restictions;

public class CriteriaHibernateRestictions<T> implements Restictions<T, CriteriaHibernateImpl>{
	
	private Session session;
	private org.hibernate.Criteria hibernateCriteria;
	private ProjectionList projectionList;

	public CriteriaHibernateRestictions(Session session, Class<T> clazz) {
		this.session = session;
		hibernateCriteria = this.session.createCriteria(clazz);
	}
	
	public CriteriaHibernateRestictions<T> add(CriteriaHibernateImpl criterion){
		hibernateCriteria.add(criterion.build());		
		return this;
	}
	
	public CriteriaHibernateRestictions<T> addOrder(Order<?> order){
		hibernateCriteria.addOrder((org.hibernate.criterion.Order) order.getSpecificOrder());		
		return this;
	}
	public CriteriaHibernateRestictions<T> setMaxresults(int maxResults){
		hibernateCriteria.setMaxResults(maxResults);		
		return this;
	}
	public CriteriaHibernateRestictions<T> setFirstResult(int firstResult){
		hibernateCriteria.setFirstResult(firstResult);		
		return this;
	}
	
	public CriteriaHibernateRestictions<T> setCacheable(boolean cacheable){
		hibernateCriteria.setCacheable(cacheable);		
		return this;
	}
	
	public CriteriaHibernateRestictions<T> setResultTransformer(ResultTransformer resultProcessor) {
		hibernateCriteria.setResultTransformer(resultProcessor);
		return this;
	}

	public CriteriaHibernateRestictions<T> addProjection(Projection projection) {
		if(projectionList == null){
			projectionList = Projections.projectionList();
		}
		projectionList.add(projection);
		return this;
	}
	
	public CriteriaHibernateRestictions<T> buildProjections(){
		hibernateCriteria.setProjection(projectionList);
		return this;
	}
	
	
	@SuppressWarnings("unchecked")
	public T uniqueResult(){
		return (T) hibernateCriteria.uniqueResult();		
	}
	
	@SuppressWarnings("unchecked")
	public List<T> list(){
		return hibernateCriteria.list();		
	}

	
}
