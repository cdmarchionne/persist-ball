'''
Created on 06/07/2011

@author: Cristian Suarez
'''

import pygame
from tateti.persistence.useCaseManager import UseCaseManager

# Sprite Size
SS = 100

# Mouse buttons
LEFT = 1
RIGHT = 3

# Font, Para ecribir
if not pygame.font.get_init():
    pygame.font.init()
FONT = pygame.font.SysFont('Arial', 32)

# UseCaseManager
useCaseManager = UseCaseManager()