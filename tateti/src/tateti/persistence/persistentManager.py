from ZODB import FileStorage, DB
from unitOfWork import UnitOfWork
import threading

class PersistentManager:
    def __init__(self):        
        self.storage = FileStorage.FileStorage('../database/database.fs')
        self.db = DB(self.storage)
        self.threadLocal =  threading.local();

    def createUnitOfWork(self):
        self.threadLocal.unitOfWork = UnitOfWork(self.db.open())
        return self.threadLocal.unitOfWork
    
    def getPersistentRoot(self):
        return self.threadLocal.unitOfWork.getRoot()
