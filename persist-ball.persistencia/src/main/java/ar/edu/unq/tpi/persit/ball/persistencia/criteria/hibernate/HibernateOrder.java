package ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate;

import ar.edu.unq.tpi.persit.ball.persistencia.criteria.Order;

public class HibernateOrder implements Order<org.hibernate.criterion.Order>{
	
	private org.hibernate.criterion.Order order;

	@Override
	public HibernateOrder asc(String property) {
		this.order =  org.hibernate.criterion.Order.asc(property);
		return this;
	}

	@Override
	public HibernateOrder desc(String property) {
		this.order =  org.hibernate.criterion.Order.desc(property);
		return this;
	}

	@Override
	public org.hibernate.criterion.Order getSpecificOrder() {
		return this.order;
	}
	

}
