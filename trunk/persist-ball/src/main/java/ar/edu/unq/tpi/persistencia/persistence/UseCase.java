package ar.edu.unq.tpi.persistencia.persistence;

import ar.edu.unq.tpi.persistencia.exception.UserException;
import ar.edu.unq.tpi.persistencia.utils.ReflectionUtils;

public class UseCase {
	
	public static void execute(Object object, String metodo, Object...objects ){
		execute(TransactionManager.STANDARD, object, metodo, objects);		
	}
	
	public static void executeTest(Object object, String metodo, Object...objects ){
		execute(TransactionManager.ROLLBACK, object, metodo, objects);		
		
	}
	public static void executeDummy(Object object, String metodo, Object...objects ){
		execute(TransactionManager.DUMMY, object, metodo, objects);		
		
	}
	
	
	public static void execute(TransactionManager transactionManager, Object object, String metodo, Object...objects){
		UnitOfWork unitOfWork = PersistenceManager.getInstance().initUnitOfWork(transactionManager);
		try {
			unitOfWork.getTransaction().begin();
			ReflectionUtils.invokeMethod(object, metodo,objects);
			unitOfWork.commit();
			
		}catch (Exception e) {
			unitOfWork.rollback();
			throw new UserException(e);
		}finally{
			unitOfWork.close();
		}
		
	}
	

}
