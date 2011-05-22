package ar.edu.unq.tpi.persit.ball.persistencia.home;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;

import ar.edu.unq.tpi.persist.ball.domain.bean.Equipo;
import ar.edu.unq.tpi.persist.ball.domain.bean.PartidoSimple;
import ar.edu.unq.tpi.persist.ball.domain.exception.UserException;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate.CriteriaHibernateImpl;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.DatosHistoricos;

public class PartidoSimpleHome extends HomeHibernateImpl<PartidoSimple>{

	public PartidoSimpleHome() {
		super(PartidoSimple.class);
	}
	
	
	public PartidoSimple getByNameAndDate(String equipo1, String equipo2,
			GregorianCalendar date) {
		try {
			CriteriaHibernateImpl criteria = new CriteriaHibernateImpl();
			final PartidoSimple object = createCriteria()
				.add(criteria.or(
					criteria.and(
						criteria.equals("equipo1.nombre", equipo1),
						criteria.equals("equipo2.nombre", equipo2)), 
					criteria.and(
						criteria.equals("equipo1.nombre", equipo2),
						criteria.equals("equipo2.nombre", equipo1)))).uniqueResult();
			if (object == null)
				throw new UserException("No se encontro el objeto de la clase "
						+ this.getPersistentClass().getName());
			return object;
		} catch (final HibernateException e) {
			throw new UserException("Error cargando el objeto "
					+ this.getPersistentClass().getName(), e);
		}
	}
	
	public DatosHistoricos getDatosHistoricos(Equipo equipo1, Equipo equipo2){
		CriteriaHibernateImpl criteria = new CriteriaHibernateImpl();
		List<?> list = createCriteria()
				.add(criteria.or(
						criteria.and(
								criteria.equals("equipo1", equipo1),
								criteria.equals("equipo2", equipo2)),
						criteria.and(
								criteria.equals("equipo1", equipo2),
								criteria.equals("equipo2", equipo1))))
								
				.addProjection(Projections.groupProperty("ganador"))
				.addProjection(Projections.count("equipo1"))
				.buildProjections()
				.list();
		return new DatosHistoricos((List<Object[]>) list);
	}
	


}
