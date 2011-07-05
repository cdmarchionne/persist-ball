import transaction
class UnitOfWork:


    def __init__(self, connection):
        self.connection = connection
##        dbroot = self.connection.root()
        # Ensure that a 'userdb' key is present
        # in the root
##        if not dbroot.has_key('userdb'):
##            from BTrees.OOBTree import OOBTree
##            dbroot['userdb'] = OOBTree()
##        self.root = dbroot['userdb']
        self.root = self.connection.root()

    
    def beginTransaction(self):
        transaction.begin()
    
    def comit(self):
        transaction.commit()
    
    def rollback(self):
        transaction.abort()
    
    def close(self):
        self.connection.close();
        
    def getRoot(self):
        return self.root
    
    
