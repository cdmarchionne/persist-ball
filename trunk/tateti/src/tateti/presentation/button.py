'''
Created on 08/07/2011

@author: Cristian Suarez
'''

from globals import *

class Button:
    def __init__(self, windowClass, bounds):
        self.windowClass = windowClass
        self.bounds = bounds
        
    def getNextWindow(self, point):
        start = (self.bounds[0][0]*SS, self.bounds[0][1]*SS)
        end =  (self.bounds[1][0]*SS, self.bounds[1][1]*SS)
        
        if (start[0] <= point[0] and end[0] >= point[0]) and \
           (start[1] <= point[1] and end[1] >= point[1]):
            return self.windowClass
    
        