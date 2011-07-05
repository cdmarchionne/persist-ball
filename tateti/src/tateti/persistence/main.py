from tateti.persistence.home import Home
from tateti.domain.player import Player
from tateti.persistence.useCaseManager import UseCaseManager
from tateti.domain.simpleGame import SingleGame


class Prueba:
            
    def save(self):
        home = Home()
        home.savePlayer(Player("pepito2"))
        home.savePlayer(Player("Jose2"))
        home.savePlayer(Player("Maria2"))
    
    
    def retrive(self):
        home = Home()
        print home.getPlayers()


useCaseManager = UseCaseManager()
prueba = Prueba()
#useCaseManager.execute(prueba.save)
useCaseManager.execute(prueba.retrive)
game = SingleGame(Player("p1"), Player("p2"))
game.autoPlay()
print "matrix ", game.board.matrix.dictionary
print "winner ", game.getWinner()
print "looser ", game.getLooser()




