from tateti.persistence.persistentManager import persistentManager
class Home:
    def __init__(self, type):
        self.type = type
            
    def getPersistentObject(self):
        return persistentManager.getPersistentRoot()
    
    def saveObject(self, object):
        if not self.type in self.getPersistentObject():
            self.getPersistentObject()[self.type] = []
        objects = self.getPersistentObject()[self.type]
        objects.append(object)
        self.getPersistentObject()[self.type] = objects
        
#        self.getPersistentObject()[self.type].append(object)
#        self.getPersistentObject()[self.type]._p_changed = True

    
    def getAll(self):
        return self.getPersistentObject()[self.type]
        
        
        
