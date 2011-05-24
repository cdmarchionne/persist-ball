package ar.edu.unq.tpi.persit.ball.persistencia.home;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.impl.Log4jLoggerFactory;

import ar.edu.unq.tpi.persist.ball.domain.bean.PersistentObject;
import ar.edu.unq.tpi.persist.ball.domain.exception.UserException;
import ar.edu.unq.tpi.persist.ball.domain.home.Home;
import ar.edu.unq.tpi.persit.ball.persistencia.PersistenceManager;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate.CriteriaHibernateImpl;
import ar.edu.unq.tpi.persit.ball.persistencia.criteria.hibernate.CriteriaHibernateRestictions;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.Logger;

public class HomeHibernateImpl<T extends PersistentObject> implements Home<T> {

	private Class<T> clazz;

	public HomeHibernateImpl(final Class<T> anClass) {
		this.clazz = anClass;
	}

	@Override
	public void delete(final T pgo) {
		try {
			Session session = this.session();
			session.delete(pgo);
			logear("Deleting the object ", pgo);
		} catch (final HibernateException e) {
			throw new UserException("Error eliminando el objeto " + pgo, e);
		}
	}

	@Override
	public void update(final T pgo) {
		try {
			Session session = this.session();
			session.update(pgo);
			logear("Updating the object ", pgo);
		} catch (final HibernateException e) {
			throw new RuntimeException("Error actualizando el objeto " + pgo, e);
		}
	}

	@Override
	public void save(final T pgo) {
		try {
			Session session = this.session();
			session.save(pgo);
			logear("Saving the object ", pgo);
		} catch (final HibernateException e) {
			throw new RuntimeException("Error guardando el objeto " + pgo, e);
		}
	}

	@Override
	public void saveOrUpdate(final T pgo) {
		try {
			Session session = this.session();
			session.saveOrUpdate(pgo);
			logear("Saving or Updating the object ", pgo);
		} catch (final HibernateException e) {
			throw new RuntimeException(
					"Error guardando/actualizando el objeto " + pgo, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(final Serializable id) {
		try {
			final T object = (T) this.session().get(this.getPersistentClass(),
					id);
			if (object == null)
				throw new UserException("No se encontro un objeto de la clase "
						+ this.getPersistentClass().getName() + " con id " + id);
			logear("Looking for the id ", id);
			return object;
		} catch (final HibernateException e) {
			throw new UserException("Error cargando el objeto "
					+ this.getPersistentClass().getName() + "#" + id, e);
		}
	}

	@Override
	public T getByName(final String name) {
		try {
			T object = createCriteria().add(new CriteriaHibernateImpl().equals("nombre", name))
					.uniqueResult();
			if (object == null)
				throw new UserException("No se encontro un objeto de la clase "
						+ this.getPersistentClass().getName() + " con nombre "
						+ name);
			logear("Looking for the name ", name);
			return object;
		} catch (final HibernateException e) {
			throw new UserException("Error cargando el objeto "
					+ this.getPersistentClass().getName() + "#" + name, e);
		}
	}

	public CriteriaHibernateRestictions<T> createCriteria() {
		return new CriteriaHibernateRestictions<T>(session(),
				getPersistentClass());
	}

	@Override
	public List<T> getAll() {
		logear("Looking for the alll ", "");
		return createCriteria().list();
	}

	public List<T> getPag(int desde, int hasta) {
//		logear("Paged desde "+desde, "hasta "+ hasta);
		return createCriteria().setFirstResult(desde).setMaxresults(hasta)
				.list();
	}

	protected Session session() {
		return PersistenceManager.getInstance().getUnitOfWork().getSession();
	}

	protected Class<T> getPersistentClass() {
		return clazz;
	}
	
	protected void logear(String action, Object object){
		Logger.log(action +"   " + object);
	}

}
