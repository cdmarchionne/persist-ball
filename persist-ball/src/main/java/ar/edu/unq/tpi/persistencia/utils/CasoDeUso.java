package ar.edu.unq.tpi.persistencia.utils;

import org.hibernate.Session;

import ar.edu.unq.tpi.persistencia.exception.UserException;

public class CasoDeUso {
	
	public static void ejecutar(Object object, String metodo, Object...objects ){
		Session session = PersistenceManager.getInstance().getCurrentSession();
    	try {
    		session.beginTransaction();
			ReflectionUtils.invokeMethod(object, metodo,objects);
			session.getTransaction().commit();
			
		}catch (UserException e) {
			throw new UserException(e);
		} catch (Exception e) {
    		session.getTransaction().rollback();
    		throw new UserException(e);
    	}finally{
    		PersistenceManager.getInstance().closeSession();
    	}
	}

}
