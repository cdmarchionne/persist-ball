package ar.edu.unq.tpi.persistencia.persistence;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.search.Search;

public class UnitOfWork {

	private Session session;
	private Transaction transaction;
	private TransactionManager txManager;


	public UnitOfWork(final Session session, final TransactionManager txManager) {
		this.session = session != null ? Search.getFullTextSession(session) : null;
		this.txManager = txManager;
		this.transaction = txManager.openTransaction(this.session);
	}


	public Session getSession() {
		return session;
	}


	public void setSession(final Session session) {
		this.session = session;
	}


	public Transaction getTransaction() {
		return transaction;
	}


	public void setTransaction(final Transaction transaction) {
		this.transaction = transaction;
	}


	public void setTxManager(final TransactionManager txManager) {
		this.txManager = txManager;
	}


	public void commit() {
		txManager.commitTransaction(this.transaction);
	}


	public void rollback() {
		if ( this.transaction != null ) {
			txManager.rollbackTransaction(this.transaction);
		}
	}


	public void close() {
		if ( this.session != null ) {
			this.session.close();
		}
	}
	

	public UnitOfWork openNext() {
		return new UnitOfWork(this.session.getSessionFactory().openSession(), TransactionManager.STANDARD);
	}


}