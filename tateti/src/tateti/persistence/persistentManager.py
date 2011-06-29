from ZODB import FileStorage, DB
from unitOfWork import UnitOfWork

class PersistentManager:
    def __init__(self):        
        self.storage = FileStorage.FileStorage('../database/database.fs')
        self.db = DB(self.storage)
        self.unitOfWork = None
        

    def createUnitOfWork(self):
        self.unitOfWork = UnitOfWork(self.db.open())
        return self.unitOfWork
               
