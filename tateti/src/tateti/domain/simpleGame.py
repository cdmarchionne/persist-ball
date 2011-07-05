from board import Board
from random import Random

class SingleGame:
    def __init__(self, player1, player2):
        self.player1 = player1
        self.player2 = player2
        self.symbolRelationships = {Board.CROSS: self.player1,
                                    Board.CIRCLE : self.player2,
                                    None : None                                    
                                    }
        self.board = Board(3, 3)
        self.height = 3
        self.width = 3

    def getPlayer1(self):
        return self.player1
        
    def getPlayer2(self):
        return self.player2

    def getBoard(self):
        return self.board

    def getWinner(self):
        return self.symbolRelationships[self.board.getWinnerSymbol()]
    
    def getLooser(self):
        if self.getWinner() == self.player1:
            return self.player2
        else:
            return self.player1
    
    def autoPlay(self):
        random = Random()
        crossPlayed = False
        for x in range(self.height*self.width):
            randomNum = random.randint(0, self.board.getEmptySlotsSize()-1)
            tuple = self.board.getEmptySlot(randomNum)
            self.board.removeEmptySlot(tuple)
            if crossPlayed:
                symbol = Board.CIRCLE
            else:
                symbol = Board.CROSS
            crossPlayed = not crossPlayed
            winner = self.board.put(tuple[0], tuple[1], symbol)
            
            if winner != None:
                self.getWinner().incWonGames()
                self.getLooser().incTotalGames()
                break
           
            

    
