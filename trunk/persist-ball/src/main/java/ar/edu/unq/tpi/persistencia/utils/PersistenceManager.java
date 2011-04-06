package ar.edu.unq.tpi.persistencia.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class PersistenceManager {

    private static PersistenceManager INSTANCE;
    private  org.hibernate.classic.Session session;
    private  AnnotationConfiguration cfg = new AnnotationConfiguration().configure("hibernate.cfg.xml");
    private SessionFactory sessionFactory;


    public static PersistenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersistenceManager();
        }
        return INSTANCE;
    }

    private PersistenceManager() {
    }
    

	protected void build() {
		sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
	}

    /**
     * Returns the Hibernate configuration object
     */
    public AnnotationConfiguration getConfiguration() {
        return this.cfg;
    }

    public SessionFactory getDefaultSessionFactory() {
        return this.sessionFactory;
    }

    /**
     * This destroys the sessionFactory and forces hibernate to a full reload.
     */
    public void destroy() {
        INSTANCE = null;
    }

    public Session getCurrentSession() {
    	if(!session.isOpen())
    		session = sessionFactory.openSession();
		return session;
    }

	public void close() {
		this.session.close(); 
	} 


} 
