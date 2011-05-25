package ar.edu.unq.tpi.persit.ball.persistencia;

import ar.edu.unq.tpi.persist.ball.domain.exception.UserException;
import ar.edu.unq.tpi.persist.ball.domain.utils.ReflectionUtils;
import ar.edu.unq.tpi.persit.ball.persistencia.logger.Reporter;

public class UseCaseManager {
	
	public static void execute(Object object, String metodo, Object...objects ){
		execute(TransactionManager.STANDARD, object, metodo, objects);		
	}
	
	public static void executeTest(Object object, String metodo, Object...objects ){
		execute(TransactionManager.ROLLBACK, object, metodo, objects);		
		
	}
	public static void executeDummy(Object object, String metodo, Object...objects ){
		execute(TransactionManager.DUMMY, object, metodo, objects);		
		
	}
	public static void execute(UseCase useCase ){
		execute(TransactionManager.STANDARD, useCase);		
	}
	
	public static void executeTest(UseCase useCase){
		execute(TransactionManager.ROLLBACK, useCase);		
		
	}
	public static void executeDummy(UseCase useCase){
		execute(TransactionManager.DUMMY, useCase);		
		
	}
	
	
	public static void execute(TransactionManager transactionManager, final Object object, final String metodo, final Object...objects){
			execute(transactionManager, new UseCase() {
				@Override
				public void run() {
					ReflectionUtils.invokeMethod(object, metodo,objects);
				}
			});
	}
	public static void execute(TransactionManager transactionManager, UseCase useCase){
		UnitOfWork unitOfWork = PersistenceManager.getInstance().initUnitOfWork(transactionManager);
		try {
			Reporter.startTime();
			unitOfWork.getTransaction().begin();
			new Thread(useCase).run();
			unitOfWork.commit();
		}catch (Exception e) {
			unitOfWork.rollback();
			throw new UserException(e);
		}finally{
			unitOfWork.close();
			Reporter.finishTime();
			Reporter.show();
		}
	}
	

}
