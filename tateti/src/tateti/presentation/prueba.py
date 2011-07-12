from tateti.persistence.home import Home
from tateti.domain.player import Player
from tateti.persistence.useCaseManager import UseCaseManager, useCaseManager
from tateti.domain.simpleGame import SimpleGame
from tateti.domain.tournamentGame import TournamentGame
from threading import Thread
from tateti.persistence.playerHome import PlayerHome

class Prueba:
    def savePlayers(self):
        home = Home(Player)
        home.saveObject(Player("player1"))
        home.saveObject(Player("player2"))
        home.saveObject(Player("player3"))

    def saveTournamentGames(self):
        TournamentGameHome = Home(TournamentGame)
        playerHome = Home(Player)
        players = playerHome.getAll();
        game = TournamentGame(players[0], players[1])
        game.autoPlay();
        print "winner ", game.getWinner()
        print "looser ", game.getLooser()
        TournamentGameHome.saveObject(game)
    
    
    def retrievePlayers(self):
        home = Home(Player)
        print home.getAll()
        return home.getAll()
    
    def retriveTournamentGames(self):
        home = Home(TournamentGame)
        return home.getAll()


class ThreadPrueba(Thread):
    
    def __init__(self, method):
        Thread.__init__(self)
        self.method = method
        
    def run(self):
        tournamentGames = useCaseManager.execute(self.method)
        game = tournamentGames[0] 
        print "winner ", game.getWinner()
        print "looser ", game.getLooser()
        
def retriveSimpleGame(self):
    home = Home(SimpleGame)
    return home.getAll()

def updateJugador3():
    home = PlayerHome()
    player = home.getPlayerByName("player3")
    player.wonGames = 100;
    home.updateObject(player)    

#print useCaseManager.execute(retriveSimpleGame)
#useCaseManager.execute(prueba.saveTournamentGames)
#ThreadPrueba(prueba.retriveTournamentGames).start()
#print useCaseManager.execute(updateJugador3)
#print useCaseManager.execute(prueba.retrievePlayers)

"""PARA CUANDO SE CORRE POR PRIMERA VEZ!!!!!!"""
prueba = Prueba()
print useCaseManager.execute(prueba.savePlayers)




