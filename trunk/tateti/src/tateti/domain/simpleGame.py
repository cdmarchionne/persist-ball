from board import Board

class SingleGame:
    def __init__(self, player1, player2):
        self.player1 = player1
        self.player2 = player2
        self.symbolRelationships = {Board.CROSS : self.player1,
                                    Board.CIRCLE : self.player2,
                                    }
        self.board = Board()

    def getPlayer1(self):
        return self.player1
        
    def getPlayer2(self):
        return self.player2

    def getBoard(self):
        return self.board

    def getWinner(self):
        return self.symbolRelationships[self.board.getWinnerSymbol()]

    
