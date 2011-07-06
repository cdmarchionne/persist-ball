'''
Created on 06/07/2011

@author: Cristian Suarez
'''

class Window:
    """ Abstracta """
    def __init__(self, screen):
        self.screen = screen
        self.background = None
        
    def renderBackground(self):    
        self.screen.blit(self.background, (0,0))
        
    def render(self):
        self.screen.render()