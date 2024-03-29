from home import Home

class UseCaseManager:
    def __init__(self):
        self.persistenceManager = Home.persistentManager
    
    def execute(self, operation):
        unitOfWork = self.persistenceManager.createUnitOfWork()
        unitOfWork.beginTransaction()
        result = None
        try:
            result = operation()
            unitOfWork.comit()
#        Falta hacer un finally y tirar la exception devuelta
        except Exception, e:
            unitOfWork.rollback()
            print e
        unitOfWork.close()
        return result
            
        
        
useCaseManager = UseCaseManager()
