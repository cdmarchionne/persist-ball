'''
Created on 06/07/2011

@author: Cristian Suarez
'''
from tateti.persistence.home import Home
from tateti.persistence.playerHome import PlayerHome
from tateti.persistence.useCaseManager import useCaseManager

import pygame
from pygame.locals import *

from tateti.domain.simpleGame import SimpleGame

from window import Window
from tateti.domain.board import Board
from button import Button
from globals import *

class GameWindow(Window):
    def __init__(self, screen, player1, player2, mainWindow):
        Window.__init__(self, screen, mainWindow)
        self.simpleGame = SimpleGame(player1, player2)
        self.background = pygame.image.load("backgrounds/ta te ti grid.jpg").convert()
        
        self.background.blit(FONT.render('%4s' % player1.getName(), True, (224,0,16)), (60, 30))
        self.background.blit(FONT.render('VS', True, (0,0,0)), (232, 30))
        self.background.blit(FONT.render('%4s' % player2.getName(), True, (4,174,0)), (355, 30))
        
        self.finished = False
        
        self.buttons = []
        
        self.images = {Board.CROSS:pygame.image.load("images/ta te ti cross.gif").convert(),
                       Board.CIRCLE:pygame.image.load("images/ta te ti circle.gif").convert(),
                       "volver":pygame.image.load("images/ta te ti boton volver.gif").convert()}
    
        self.turn = self.simpleGame.getTurn()
        self.boardBounds = ((1,1),(4,4))
    
    def addSymbolToBackGround(self, aLoadedImage, tuple):
        self.background.blit(aLoadedImage, tuple)
        self.render()
        
    def renderImage(self, aLoadedImage, tuple):
        self.screen.blit(aLoadedImage, tuple)
        pygame.display.flip()
        
    def changeTurn(self):
        self.turn = self.simpleGame.getTurn()
            
    def putSymbolInSlot(self, point):
        sPoint = ( (int(point[0]/SS))*SS , (int(point[1]/SS))*SS )
        self.addSymbolToBackGround(self.images[self.turn], sPoint)
    
    def clickedOutsideTheBoard(self, point):
        start = (self.boardBounds[0][0]*SS, self.boardBounds[0][1]*SS)
        end =  (self.boardBounds[1][0]*SS, self.boardBounds[1][1]*SS)
        
        return (start[0] >= point[0] or end[0] <= point[0]) or \
               (start[1] >= point[1] or end[1] <= point[1])
    
    def isEmptySlot(self, point):
        return point in self.simpleGame.getBoard().getEmptySlots()
    
    def addBackButton(self):
        self.buttons.append(Button(self.mainWindow, ((1,5),(4,6))))
        self.addSymbolToBackGround(self.images["volver"], (1*SS,5*SS))
    
    def useEventsIterations(self, event):
#        if event.type == pygame.MOUSEMOTION:
#            pass
        if event.type == pygame.MOUSEBUTTONDOWN and event.button == LEFT:
            point = (event.pos[0], event.pos[1])
            if not self.finished:
                if not self.clickedOutsideTheBoard(point):
                    boardPoint = ( int(point[0]/SS) - self.boardBounds[0][0],
                                   int(point[1]/SS) - self.boardBounds[0][1] )
                    if self.isEmptySlot(boardPoint):
                        self.putSymbolInSlot(point)
                        self.finished = self.simpleGame.playTurn(boardPoint)
                        self.changeTurn()
                        if self.finished:
                            useCaseManager.execute(self.saveGame)
                            self.addBackButton()
                            self.showWinner()
                            self.mainWindow.resetButtons()
            else:
                self.mainWindow.initGame()
                self.checkForClickedButton(point)
               
    def showWinner(self):
        winner = self.simpleGame.getWinner()
        if winner != None:
            self.addSymbolToBackGround(FONT.render('¡¡ %4s gana !!' % winner.getName(), True, (0,0,0)), (150, 430))
        else:
            self.addSymbolToBackGround(FONT.render('¡¡ Empate !!', True, (0,0,0)), (180, 430))
                            
     
    def saveGame(self):
        gameHome = Home(SimpleGame)
        playerHome = PlayerHome()
        self.simpleGame.pointsDistribution()
        gameHome.saveObject(self.simpleGame) 
        playerHome.updateObject(self.simpleGame.getPlayer1()) 
        playerHome.updateObject(self.simpleGame.getPlayer2()) 

                
            
            
            
            
