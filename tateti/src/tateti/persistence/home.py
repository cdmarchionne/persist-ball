from tateti.persistence.persistentManager import PersistentManager
class Home:
    persistentManager = PersistentManager()
    def __init__(self, type):
        self.type = type
            
    def getPersistentObject(self):
        return Home.persistentManager.getPersistentRoot()
    
    def saveObject(self, object):
        persistentObject = self.getPersistentObject()
        if not str(self.type) in persistentObject:
            persistentObject[str(self.type)] = []
#        objects = self.getPersistentObject()[self.type]
#        objects.append(object)
#        self.getPersistentObject()[self.type] = objects
        
        persistentObject[str(self.type)].append(object)
        persistentObject._p_changed = True
        
    def updateObject(self, object):
        persistentObject = self.getPersistentObject()
        persistentObject[str(self.type)].remove(object)
        persistentObject[str(self.type)].append(object)
        self.getPersistentObject()._p_changed = True
        
    

    
    def getAll(self):
        return self.getPersistentObject()[str(self.type)]
        
        
        
