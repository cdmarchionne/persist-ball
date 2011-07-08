from tateti.persistence.home import Home
from tateti.domain.player import Player
from tateti.persistence.useCaseManager import UseCaseManager
from tateti.domain.simpleGame import SimpleGame
from tateti.domain.tournamentGame import TournamentGame


class Prueba:
            
    def savePlayers(self):
        home = Home(Player)
        home.saveObject(Player("pepito2"))
        home.saveObject(Player("Jose2"))
        home.saveObject(Player("Maria2"))

    def saveTournamentGames(self):
        TournamentGameHome = Home(TournamentGame)
        playerHome = Home(Player)
        players = playerHome.getAll();
        game = TournamentGame(players[0], players[1])
        game.autoPlay();
        print "winner ", game.getWinner()
        print "looser ", game.getLooser()
        TournamentGameHome.saveObject(game)
    
    
    def retrivePlayers(self):
        home = Home(Player)
        print home.getAll()
        return home.getAll()
    
    def retriveTournamentGames(self):
        home = Home(TournamentGame)
        return home.getAll()


useCaseManager = UseCaseManager()
prueba = Prueba()
#useCaseManager.execute(prueba.saveTournamentGames)
tournamentGames = useCaseManager.execute(prueba.retriveTournamentGames)
game = tournamentGames[0] 
print "winner ", game.getWinner()
print "looser ", game.getLooser()
#print "matrix ", game.board.matrix.dictionary




