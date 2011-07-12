from tateti.persistence.home import Home
from tateti.domain.player import Player
class PlayerHome(Home):
    def __init__(self):
        Home.__init__(self, Player)
    
    def getPlayerByName(self, aName):
        players = self.getAll()
        for player in players:
            if(player.getName() == aName):
                return player
        return None
        
