import transaction
class UnitOfWork:


    def __init__(self, connection):
        self.connection = connection
        self.root = self.connection.root()
    
    def beginTransaction(self):
        transaction.begin()
    
    def comit(self):
        transaction.commit()
    
    def rollback(self):
        transaction.abort()
    
    def close(self):
        self.connection.close();
    
    