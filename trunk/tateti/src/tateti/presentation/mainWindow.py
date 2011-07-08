'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame
from pygame.locals import *

from window import Window
from gameWindow import GameWindow 
from tournamentGameWindow import TournamentGameWindow
from statisticsWindow import StatisticsWindow
from globals import *
from button import Button
from tateti.domain.player import Player

class MainWindow(Window):
    def __init__(self, screen):
        Window.__init__(self, screen, self)
        
        self.player1 = Player("Player 1")
        self.player2 = Player("Player 2")
        
        self.background = pygame.image.load("backgrounds/ta te ti main.jpg").convert()
        self.buttons = [Button(GameWindow, ((1,1),(4,2))),
                        Button(TournamentGameWindow, ((1,1),(4,2))),
                        Button(StatisticsWindow, ((1,1),(4,2)))]
        
    def checkForClickedButton(self, point):
        for b in self.buttons:
            window = b.getNextWindow(point)
            if window != None:
                self.screen.currentWindow = window(self.screen, self.player1, self.player2, self)
                break
        self.render()
                
    def useEventsIterations(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN and event.button == LEFT:
            point = (event.pos[0], event.pos[1])
            self.checkForClickedButton(point)
            
            
            
            
            
            
            