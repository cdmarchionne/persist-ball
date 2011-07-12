import transaction
from BTrees import OOBTree
class UnitOfWork:


    def __init__(self, connection):
        self.connection = connection
        self.dbroot = self.connection.root()
#        Ensure that a 'userdb' key is present in the dbroot
        if not self.dbroot.has_key('repository'):
            from BTrees.OOBTree import OOBTree
            self.dbroot['repository'] = OOBTree()
        self.root = self.dbroot['repository']
#        self.root = self.connection.root()

    
    def beginTransaction(self):
        transaction.begin()
    
    def comit(self):
        self.dbroot['repository'] = self.root
        transaction.commit()
    
    def rollback(self):
        transaction.abort()
    
    def close(self):
        self.connection.close();
        
    def getRoot(self):
        return self.root
    
    
