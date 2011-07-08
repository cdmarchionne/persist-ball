from simpleGame import SimpleGame


class TournamentGame:
    def __init__(self, player1, player2):
        self.player1 = player1
        self.player2 = player2
        self.winner = None
        
        self.simpleGame1 = SimpleGame(player1, player2)
        self.simpleGame2 = SimpleGame(player2, player1)
        self.simpleGame3 = SimpleGame(player1, player2)
        
    def getWinner(self):
        return self.winner
    
    def getLooser(self):
        winner = self.getWinner()
        if winner == self.player1:
            return self.player2
        elif winner == self.player2 :
            return self.player1
        else:
            return None;
        
    def autoPlay(self):
        self.simpleGame1.autoPlay()
        self.simpleGame2.autoPlay()
        self.simpleGame3.autoPlay()
        winnerGame1 = self.simpleGame1.getWinner()
        winnerGame2 = self.simpleGame2.getWinner()
        winnerGame3 = self.simpleGame3.getWinner()
        
        winningDict = {self.player1 : 0,
                       self.player2 : 0,
                       None : 0}
        
        winningDict[winnerGame1] += 1
        winningDict[winnerGame2] += 1
        winningDict[winnerGame3] += 1
        
        if winningDict[self.player1] > winningDict[self.player2]:
            self.winner = self.player1
        elif winningDict[self.player1] < winningDict[self.player2]:
            self.winner = self.player2
        
        
        
        