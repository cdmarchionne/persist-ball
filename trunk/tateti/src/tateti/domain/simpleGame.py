from board import Board
from random import Random

class SimpleGame:
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
        
        self.turn = Board.CROSS
        
    def getTurn(self):
        return self.turn
        
    def changeTurn(self):
        if self.turn == Board.CROSS:
            self.turn = Board.CIRCLE
        elif self.turn == Board.CIRCLE:
            self.turn = Board.CROSS

    def getPlayer1(self):
        return self.player1
        
    def getPlayer2(self):
        return self.player2

    def getBoard(self):
        return self.board

    def getWinner(self):
        return self.symbolRelationships[self.board.getWinnerSymbol()]
    
    def getLooser(self):
        winner = self.getWinner()
        if winner == self.player1:
            return self.player2
        elif winner == self.player2 :
            return self.player1
        else:
            return None;
        
    def playTurn(self, tuple):
        self.board.removeEmptySlot(tuple)
        winner = self.board.put(tuple[0], tuple[1], self.turn)
        self.changeTurn()
        
        if winner != None:
            self.getWinner().incWonGames()
            self.getLooser().incTotalGames()
            return True

        elif self.board.getEmptySlotsSize() == 0:
            self.player1.incTotalGames()
            self.player2.incTotalGames()
            return True
        
        return False
            
    
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
           
            

    
