package ar.edu.unq.tpi.persistencia.home;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ar.edu.unq.tpi.persistencia.bean.PersistentObject;
import ar.edu.unq.tpi.persistencia.exception.UserException;
import ar.edu.unq.tpi.persistencia.utils.PersistenceManager;

public class Home<T extends PersistentObject> {

    private final static SessionFactory SESSION_FACTORY = PersistenceManager.getInstance().getDefaultSessionFactory();

    private Class<T> clazz;

    public Home(final Class<T> anClass) {
        this.clazz = anClass;
    }

    public void delete(final T pgo) {
        try {
            Session session = this.session();
            session.beginTransaction();
            session.delete(pgo);
            session.getTransaction().commit();
        } catch (final HibernateException e) {
            throw new UserException("Error removing object " + pgo, e);
        }
    }

    public void update(final T pgo) {
        try {
            Session session = this.session();
            session.beginTransaction();
            session.update(pgo);
            session.getTransaction().commit();
        } catch (final HibernateException e) {
            throw new UserException("Error updating object " + pgo, e);
        }
    }

    public void save(final T pgo) {
        try {
            Session session = this.session();
            session.beginTransaction();
            session.save(pgo);
            session.getTransaction().commit();
        } catch (final HibernateException e) {
            throw new RuntimeException("Error updating object " + pgo, e);
        }
    }

    public void saveOrUpdate(final T pgo) {
        try {
            Session session = this.session();
            session.beginTransaction();
            session.saveOrUpdate(pgo);
            session.getTransaction().commit();
        } catch (final HibernateException e) {
            throw new RuntimeException("Error updating object " + pgo, e);
        }
    }

    public T getById(final Serializable id) {
        try {
            final T object = (T) this.session().get(this.getPersistentClass(), id);
            if (object == null)
                throw new UserException("No object was found of class " + this.getPersistentClass().getName()
                        + " with identifier " + id);
            return object;
        } catch (final HibernateException e) {
            throw new UserException("Error loading object " + this.getPersistentClass().getName() + "#" + id, e);
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
                throw new UserException("No object was found of class " + this.getPersistentClass().getName()
                        + " with name " + name);
            return object;
        } catch (final HibernateException e) {
            throw new UserException("Error loading object " + this.getPersistentClass().getName() + "#" + name, e);
        }
    }

    public List<T> getAll() {
        Session session = this.session();
        session.beginTransaction();
        List<T> list = session.createQuery("from " + this.getPersistentClass()).list();
        session.getTransaction().commit();
        return list;
    }

    protected Session session() {
        return PersistenceManager.getInstance().getCurrentSession();
    }

    private Class<T> getPersistentClass() {
        return clazz;
    }

}
