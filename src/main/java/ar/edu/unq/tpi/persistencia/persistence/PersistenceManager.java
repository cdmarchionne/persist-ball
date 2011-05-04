package ar.edu.unq.tpi.persistencia.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;


public class PersistenceManager {

    private static PersistenceManager INSTANCE;

    private ThreadLocal<UnitOfWork> unitsOfWork = new ThreadLocal<UnitOfWork>();

    private AnnotationConfiguration cfg = new AnnotationConfiguration().configure("hibernate.cfg.xml");

    private SessionFactory sessionFactory;

    public synchronized static PersistenceManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersistenceManager();
        }
        return INSTANCE;
    }

    private PersistenceManager() {
//         this.initializeSchema();
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

    public UnitOfWork initUnitOfWork(TransactionManager transactionManager){
    	UnitOfWork unitOfWork = new UnitOfWork(getNewSession(), transactionManager);
    	this.unitsOfWork.set(unitOfWork);
    	return unitOfWork;
    }


	/**
     * This destroys the sessionFactory and forces hibernate to a full reload.
     */
    public void destroy() {
        INSTANCE = null;
    }


	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public UnitOfWork getUnitOfWork() {
		return this.unitsOfWork.get();
	}

}
