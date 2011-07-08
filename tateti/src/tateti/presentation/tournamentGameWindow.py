'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame
from pygame.locals import *

from gameWindow import GameWindow
from globals import *

class TournamentGameWindow(GameWindow):
    def __init__(self, screen, player1, player2, mainWindow):
        GameWindow.__init__(self, screen, player1, player2, mainWindow)
    
    def useEventsIterations(self, event):
        pass
