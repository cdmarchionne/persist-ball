package ar.edu.unq.tpi.persit.ball.persistencia.home;

import java.util.List;

import org.hibernate.cache.NoCacheProvider;
import org.hibernate.criterion.Projections;

import ar.edu.unq.tpi.persist.ball.domain.bean.PartidoCopa;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.Order;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate.CriteriaHibernateImpl;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate.HibernateOrder;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.RankingPartidoCopa;

public class PartidoCopaHome extends HomeHibernateImpl<PartidoCopa>{

	public PartidoCopaHome() {
		super(PartidoCopa.class);
	}
	
	public RankingPartidoCopa getRankingPartidoCopa(){
		CriteriaHibernateImpl criteria = new CriteriaHibernateImpl();
		List<?> list = createCriteria()
				.addProjection(Projections.groupProperty("ganador"))
				.addProjection(Projections.alias(Projections.rowCount(), "partidosGanados"))
				.addOrder(new HibernateOrder().desc("partidosGanados"))
				.buildProjections()
				.setCacheable(true)
				.list();
		return new RankingPartidoCopa((List<Object[]>) list);
	}

}
