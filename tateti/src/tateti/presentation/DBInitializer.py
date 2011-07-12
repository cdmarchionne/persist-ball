'''
Created on 12/07/2011

@author: Administrador
'''
from tateti.domain.player import Player
from tateti.persistence.home import Home
from globals import *

class DBInitializer:
    def savePlayers(self):
        home = Home(Player)
        home.saveObject(Player("player1"))
        home.saveObject(Player("player2"))
        home.saveObject(Player("player3"))

dbi = DBInitializer()
useCaseManager.execute(dbi.savePlayers)