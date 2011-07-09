'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame

class Window:
    """ Abstracta """
    def __init__(self, screen, mainWindow):
        self.screen = screen
        self.mainWindow = mainWindow
        self.buttons = []
        self.background = None
        
    def renderBackground(self):    
        self.screen.blit(self.background, (0,0))
        pygame.display.flip()
        
    def checkForClickedButton(self, point):
        print self.buttons
        for b in self.buttons:
            window = b.getNextWindow(point)
            if window != None:
                self.screen.currentWindow = window
                break
        self.render()
        
    def render(self):
        self.screen.render()
        
    def returnToMainWindow(self):
        self.screen.currentWindow = self.mainWindow
        self.render()