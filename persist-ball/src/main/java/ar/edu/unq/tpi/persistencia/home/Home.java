package ar.edu.unq.tpi.persistencia.home;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.edu.unq.tpi.persistencia.bean.PersistentObject;
import ar.edu.unq.tpi.persistencia.exception.UserException;
import ar.edu.unq.tpi.persistencia.utils.PersistenceManager;

public class Home<T extends PersistentObject> {

    private Class<T> clazz;

    public Home(final Class<T> anClass) {
        this.clazz = anClass;
    }

    public void delete(final T pgo) {
        try {
            Session session = this.session();
            session.delete(pgo);
        } catch (final HibernateException e) {
            throw new UserException("Error eliminando el objeto " + pgo, e);
        }
    }

    public void update(final T pgo) {
        try {
            Session session = this.session();
            session.update(pgo);
        } catch (final HibernateException e) {
            throw new RuntimeException("Error actualizando el objeto " + pgo, e);
        }
    }

    public void save(final T pgo) {
        try {
            Session session = this.session();
            session.save(pgo);
        } catch (final HibernateException e) {
            throw new RuntimeException("Error guardando el objeto " + pgo, e);
        }
    }

    public void saveOrUpdate(final T pgo) {
        try {
            Session session = this.session();
            session.saveOrUpdate(pgo);
        } catch (final HibernateException e) {
            throw new RuntimeException("Error guardando/actualizando el objeto " + pgo, e);
        }
    }

    @SuppressWarnings("unchecked")
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
    public T getByName(final String name) {
        try {
            Query query = this.session().createQuery(
                    "FROM " + this.getPersistentClass().getName() + " where nombre = :name");
            query.setParameter("name", name);
            final T object = (T) query.uniqueResult();
            if (object == null)
                throw new UserException("No se encontro un objeto de la clase " + this.getPersistentClass().getName()
                        + " con nombre " + name);
            return object;
        } catch (final HibernateException e) {
            throw new UserException("Error cargando el objeto " + this.getPersistentClass().getName() + "#" + name, e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<T> getAll() {
        Session session = this.session();
        return  session.createQuery("from " + this.getPersistentClass()).list();
    }

    protected Session session() {
        return PersistenceManager.getInstance().getCurrentSession();
    }

    private Class<T> getPersistentClass() {
        return clazz;
    }

	public T getByNameAndDate(String equipo1, String equipo2, Date date) {
		try {
            Query query = this.session().createQuery(
                    "FROM " + this.getPersistentClass().getName() + " where (equipo1.nombre = :name1 and equipo2.nombre = :name2 or" +
                    												" equipo1.nombre = :name2 and equipo2.nombre = :name1) and" +
                    												" date = :date");
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
