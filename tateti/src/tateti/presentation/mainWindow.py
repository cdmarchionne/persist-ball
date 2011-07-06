'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame
from pygame.locals import *

from window import Window
from gridWindow import GridWindow 

# Mouse buttons
LEFT = 1
RIGHT = 3

class MainWindow(Window):
    def __init__(self, screen):
        Window.__init__(self, screen)
        self.background = pygame.image.load("backgrounds/ta te ti main.jpg").convert()
        
    def useEventsIterations(self, event):
        if event.type == pygame.MOUSEBUTTONDOWN:
            self.screen.currentWindow = GridWindow(self.screen)
            self.render()