package ar.edu.unq.tpi.persistencia.persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class PersistenceManager {

    private static PersistenceManager INSTANCE;

    private org.hibernate.classic.Session session;
    private UnitOfWork unitOfWork;

    private AnnotationConfiguration cfg = new AnnotationConfiguration().configure("hibernate.cfg.xml");

    private SessionFactory sessionFactory;

    public static PersistenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersistenceManager();
        }
        return INSTANCE;
    }

    private PersistenceManager() {
         this.initializeSchema();
        this.build();
    }

    protected void initializeSchema() {
        SchemaExport exporter = new SchemaExport(cfg);
        exporter.setOutputFile("script-hibernate.sql");
        exporter.drop(true, true);
        exporter.create(true, true);
    }

    protected void build() {
        sessionFactory = cfg.buildSessionFactory();
//        session = sessionFactory.openSession();
    }

    /**
     * Returns the Hibernate configuration object
     */
    public AnnotationConfiguration getConfiguration() {
        return cfg;
    }

    public SessionFactory getDefaultSessionFactory() {
        return sessionFactory;
    }

    public Session getCurrentSession() {
        if (session == null || !session.isOpen()) {
            openSession();
        }
        return session;
    }
    
    public UnitOfWork initUnitOfWork(TransactionManager transactionManager){
    	unitOfWork = new UnitOfWork(getNewSession(), transactionManager);
    	return unitOfWork;
    }


	/**
     * This destroys the sessionFactory and forces hibernate to a full reload.
     */
    public void destroy() {
        INSTANCE = null;
    }

    public void openSession() {
    	session = getNewSession();
    }

	public org.hibernate.classic.Session getNewSession() {
		return sessionFactory.openSession();
	}
    
    /**
     * Se hace try-cath por que hibernate tira {@link HibernateException}} en cualquier operacion 
     * asi se puede usar en un finally sin hacer try-catch
     */
    public void closeSession() {
    	try {
    		session.close();
		} catch (HibernateException e) {
		}
    }

	public void setUnitOfWork(UnitOfWork unitOfWork) {
		this.unitOfWork = unitOfWork;
	}

	public UnitOfWork getUnitOfWork() {
		return unitOfWork;
	}

}
