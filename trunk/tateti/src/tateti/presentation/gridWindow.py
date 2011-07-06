'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame
from pygame.locals import *

from window import Window
from globals import *

# Mouse buttons
LEFT = 1
RIGHT = 3

class GridWindow(Window):
    def __init__(self, screen):
        Window.__init__(self, screen)
        self.background = pygame.image.load("backgrounds/ta te ti grid.jpg").convert()
        
        self.images = {"cross":pygame.image.load("images/ta te ti cross.gif").convert(),
                       "circle":pygame.image.load("images/ta te ti circle.gif").convert(),
                       "hover":pygame.image.load("images/ta te ti hover.gif").convert()}
    
    def addSymbolToBackGround(self, aLoadedImage, tuple):
        self.background.blit(aLoadedImage, tuple) 
        pygame.display.flip()
        
    def renderImage(self, aLoadedImage, tuple):
        self.screen.blit(aLoadedImage, tuple)
        pygame.display.flip()
    
    def useEventsIterations(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            if event.button == LEFT:
                self.renderImage(self.images["cross"], (event.pos[0]-(SS/2), event.pos[1]-(SS/2)))
            elif event.button == RIGHT:
                self.renderImage(self.images["circle"], (event.pos[0]-(SS/2), event.pos[1]-(SS/2)))
