from  matrix import Matrix

CROSS = 1
CIRCLE = 0

class Board:
    def __init__(self):
        self.matrix = Matrix(3, 3)
        self.winnerSymbol = None

    def getWinnerSymbol(self):
        # HACELO BIEN!!!
        return CROSS

    def put(self,i, j, symbol):
        self.matrix.put(i, j, symbol)
        self.checkForWinner(symbol)
        return self.winner

    def checkForWinner(self, symbol):
        # HACELO BIEN!!!
        if \
        (self.matrix.get(0,0)==symbol and self.matrix.get(1,0)==symbol and self.matrix.get(2,0)==symbol) or \
        (self.matrix.get(0,1)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(2,1)==symbol) or \
        (self.matrix.get(0,2)==symbol and self.matrix.get(1,2)==symbol and self.matrix.get(2,2)==symbol) or \
        (self.matrix.get(0,0)==symbol and self.matrix.get(1,0)==symbol and self.matrix.get(0,2)==symbol) or \
        (self.matrix.get(1,0)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(1,2)==symbol) or \
        (self.matrix.get(2,0)==symbol and self.matrix.get(2,1)==symbol and self.matrix.get(2,2)==symbol) or \
        (self.matrix.get(0,0)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(2,2)==symbol) or \
        (self.matrix.get(0,0)==symbol and self.matrix.get(1,1)==symbol and self.matrix.get(2,0)==symbol):
                self.winner = symbol
