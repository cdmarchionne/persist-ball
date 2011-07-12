'''
Created on 08/07/2011

@author: Cristian Suarez
'''

import pygame

from tateti.persistence.playerHome import PlayerHome

from window import Window
from button import Button
from globals import *

class StatisticsWindow(Window):
    def __init__(self, screen, mainWindow):
        Window.__init__(self, screen, mainWindow)
        self.background = pygame.image.load("backgrounds/ta te ti statistics.jpg").convert()
        self.buttons = [Button(self.mainWindow, ((1,5),(4,6)))]
        useCaseManager.execute(self.showStatistics)
        
    def showStatistics(self):
        players = self.getPlayers()
        x = 10
        y = 160

        for p in players:
            self.background.blit(FONT.render('%4s' % p.getName(), True, (0,0,0)), (x, y))
            x += 200
            self.background.blit(FONT.render('%4s' % p.getWonGames(), True, (4,174,0)), (x+(75*0), y))
            self.background.blit(FONT.render('%4s' % p.getLostGames(), True, (224,0,16)), (x+(75*1), y))
            self.background.blit(FONT.render('%4s' % p.getTiedGames(), True, (0,0,0)), (x+(75*2), y))
            self.background.blit(FONT.render('%4s' % p.getTotalGames(), True, (0,0,0)), (x+(75*3), y))
            x = 10
            y += 35

    def useEventsIterations(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN and event.button == LEFT:
            point = (event.pos[0], event.pos[1])
            self.checkForClickedButton(point)
            
    def getPlayers(self):
        playerHome = PlayerHome()
        return playerHome.getAll()
            
            
            
            
            