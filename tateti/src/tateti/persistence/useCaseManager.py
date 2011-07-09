from tateti.persistence.persistentManager import persistentManager

class UseCaseManager:
    
    def __init__(self):
        self.persistenceManager = persistentManager
    
    def execute(self, operation):
        unitOfWork = self.persistenceManager.createUnitOfWork()
        unitOfWork.beginTransaction()
        try:
            result = operation()
            unitOfWork.comit()
#        Falta hacer un finally y tirar la exception devuelta
        except Exception:
            unitOfWork.rollback()
        unitOfWork.close()
        return result
            
        
        
