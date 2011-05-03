package ar.edu.unq.tpi.persistencia.home;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import ar.edu.unq.tpi.persistencia.bean.PersistentObject;
import ar.edu.unq.tpi.persistencia.exception.UserException;
import ar.edu.unq.tpi.persistencia.persistence.PersistenceManager;

public class HomeHibernateImpl<T extends PersistentObject> implements Home<T>{

    private Class<T> clazz;

    public HomeHibernateImpl(final Class<T> anClass) {
        this.clazz = anClass;
    }

    @Override
    public void delete(final T pgo) {
        try {
            Session session = this.session();
            session.delete(pgo);
        } catch (final HibernateException e) {
            throw new UserException("Error eliminando el objeto " + pgo, e);
        }
    }

    @Override
    public void update(final T pgo) {
        try {
            Session session = this.session();
            session.update(pgo);
        } catch (final HibernateException e) {
            throw new RuntimeException("Error actualizando el objeto " + pgo, e);
        }
    }

    @Override
    public void save(final T pgo) {
        try {
            Session session = this.session();
            session.save(pgo);
        } catch (final HibernateException e) {
            throw new RuntimeException("Error guardando el objeto " + pgo, e);
        }
    }

    @Override
    public void saveOrUpdate(final T pgo) {
        try {
            Session session = this.session();
            session.saveOrUpdate(pgo);
        } catch (final HibernateException e) {
            throw new RuntimeException("Error guardando/actualizando el objeto " + pgo, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
	public T getById(final Serializable id) {
        try {
            final T object = (T) this.session().get(this.getPersistentClass(), id);
            if (object == null)
                throw new UserException("No se encontro un objeto de la clase " + this.getPersistentClass().getName()
                        + " con id " + id);
            return object;
        } catch (final HibernateException e) {
            throw new UserException("Error cargando el objeto " + this.getPersistentClass().getName() + "#" + id, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getByName(final String name) {
        try {
        	T object = (T) session().createCriteria(getPersistentClass()).add(Restrictions.eq("nombre", name)).uniqueResult();        	
            if (object == null)
                throw new UserException("No se encontro un objeto de la clase " + this.getPersistentClass().getName()
                        + " con nombre " + name);
            return object;
        } catch (final HibernateException e) {
            throw new UserException("Error cargando el objeto " + this.getPersistentClass().getName() + "#" + name, e);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
	public List<T> getAll() {
        Session session = this.session();
        return  session.createQuery("from " + this.getPersistentClass()).list();
    }

    protected Session session() {
        return PersistenceManager.getInstance().getUnitOfWork().getSession();
    }

    private Class<T> getPersistentClass() {
        return clazz;
    }

	public T getByNameAndDate(String equipo1, String equipo2, GregorianCalendar date) {
		try {
            Query query = this.session().createQuery(
                    "FROM " + this.getPersistentClass().getName() + " where (equipo1.nombre = :name1 and equipo2.nombre = :name2 or" +
                    												" equipo1.nombre = :name2 and equipo2.nombre = :name1) and" +
                    												" fecha = :date");
            query.setParameter("name1", equipo1);
            query.setParameter("name2", equipo2);
            query.setParameter("date", date);
            final T object = (T) query.uniqueResult();
            if (object == null)
                throw new UserException("No se encontro el objeto de la clase " + this.getPersistentClass().getName());
            return object;
        } catch (final HibernateException e) {
            throw new UserException("Error cargando el objeto " + this.getPersistentClass().getName(), e);
        }
	}

}
