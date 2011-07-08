'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame

from mainWindow import MainWindow
        
class Screen:
    def __init__(self):
        # Fonts
        if not pygame.font.get_init():
            pygame.font.init()
        self.arialFnt = pygame.font.SysFont('Arial', 16)
        
        # Screen y ventana actual
        self.screen = pygame.display.set_mode((500, 700))
        self.currentWindow = MainWindow(self)
        
        # generate an event 30 times a second, and perform simulation update. this
        # keeps the game running at the same speed in framerate-independent fashion.
        self.UPDATE = pygame.USEREVENT
        pygame.time.set_timer(self.UPDATE, int(1000.0/30))
        
    def render(self):
        self.currentWindow.renderBackground()
#        self.screen.blit(self.arialFnt.render('player x: %4d' % (self.player.x / 80), True, (0,0,0)), (10, 10))
#        self.screen.blit(self.arialFnt.render('player y: %4d' % (self.player.y / 80), True, (0,0,0)), (10, 30))
        
    def blit(self, image, tuple):
        self.screen.blit(image, tuple)
        
    def play(self):
        self.currentWindow.render()
        running = True
        while running:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                
                self.currentWindow.useEventsIterations(event)
            

                
