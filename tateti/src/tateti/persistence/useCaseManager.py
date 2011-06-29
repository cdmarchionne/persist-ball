from persistentManager import PersistentManager

class UseCaseManager:
    
    def __init__(self):
        self.persistenceManager = PersistentManager()
    
    def execute(self, operation):
        unitOfWork = self.persistenceManager.createUnitOfWork()
        unitOfWork.beginTransaction()
        try:
            operation()
            unitOfWork.comit()
#        Falta hacer un finally y tirar la exception devuelta
        except Exception:
            unitOfWork.rollback()
        unitOfWork.close()
            
        
        
