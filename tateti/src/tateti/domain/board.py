from  matrix import Matrix

class Board:
    CROSS = 1
    CIRCLE = 0
    def __init__(self, x, y):
        self.matrix = Matrix(x, y)
        self.winnerSymbol = None
        self.emptySlots = [(0,0), (1,0), (2,0), (0,1), (1,1), (2,1), (0,2), (1,2), (2,2)]

    def getEmptySlots(self):
        return self.emptySlots
    
    def getEmptySlott(self, index):
        return self.emptySlots[index]
    
    def getEmptySlotsSize(self):
        return len(self.emptySlots)
    
    def setEmptySlots(self, aList):
        self.emptySlots = aList
    
    def areThereEmptySlots(self):
        return self.emptySlots != []
    
    def removeEmptySlot(self, tuple):
        self.emptySlots.remove(tuple)

    def getWinnerSymbol(self):
        return self.winnerSymbol 

    def setWinnerSymbol(self, symbol):
        self.winnerSymbol = symbol

    def put(self, i, j, symbol):
        self.matrix.put(i, j, symbol)
        self.checkForWinner(symbol)
        return self.winnerSymbol

    def checkForWinner(self, symbol):
        if (self.symbolWon(symbol)):
            self.winnerSymbol = symbol
            
    def symbolWon(self, symbol):
        return self.wonHorizontal(symbol) or self.wonVertical(symbol) or self.wonDiagonal(symbol)
               
    def wonHorizontal(self, symbol):
        return (self.matrix.get(0,0)==symbol and self.matrix.get(1,0)==symbol and self.matrix.get(2,0)==symbol) or \
               (self.matrix.get(0,1)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(2,1)==symbol) or \
               (self.matrix.get(0,2)==symbol and self.matrix.get(1,2)==symbol and self.matrix.get(2,2)==symbol)
        
    def wonVertical(self, symbol):
        return (self.matrix.get(0,0)==symbol and self.matrix.get(1,0)==symbol and self.matrix.get(0,2)==symbol) or \
               (self.matrix.get(1,0)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(1,2)==symbol) or \
               (self.matrix.get(2,0)==symbol and self.matrix.get(2,1)==symbol and self.matrix.get(2,2)==symbol)
        
    def wonDiagonal(self, symbol):
        return (self.matrix.get(0,0)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(2,2)==symbol) or \
               (self.matrix.get(0,0)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(2,0)==symbol)
                
