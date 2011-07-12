from persistent import Persistent

class Player(Persistent):
    def __init__(self, name):
        self.name = name
        self.wonGames = 0
        self.tieGames = 0
        self.totalGames = 0

    def getName(self):
        return self.name

    def incWonGames(self):
        self.wonGames += 1
        self.totalGames += 1

    def getWonGames(self):
        return self.wonGames

    def incTotalGames(self):
        self.totalGames += 1
        
    def incTieGames(self):
        self.tieGames += 1
        self.totalGames += 1

    def getTotalGames(self):
        return self.totalGames
    
    def getTieGames(self):
        return self.tieGames
    
    def getLostGames(self):
        return self.totalGames - (self.wonGames + self.tieGames)
    
    def __repr__(self):
        return self.name + " ganados: "+ str(self.getWonGames()) + " empatados: " +str(self.getTieGames()) +" perdidos: "+ str(self.getLostGames())
  

