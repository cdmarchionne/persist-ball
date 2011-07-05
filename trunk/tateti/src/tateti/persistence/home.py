from tateti.persistence.persistentManager import persistentManager
class Home:
            
    def getPersistentObject(self):
        return persistentManager.getPersistentRoot()
    
    def savePlayer(self, object):
        if not "player" in self.getPersistentObject():
            self.getPersistentObject()["player"] = []
        players = self.getPersistentObject()["player"]
        players.append(object)
        self.getPersistentObject()["player"] = players
        
##        self.getPersistentObject()["player"].append(object)
##        self.getPersistentObject()["player"]._p_changed = True

    
    def getPlayers(self):
        return self.getPersistentObject()["player"]
        
        
        
