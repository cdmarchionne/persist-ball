from simpleGame import SingleGame


class TournamentGame:
    def __init__(self, player1, player2):
        self.player1 = player1
        self.player2 = player2
        
        self.SimpleGame1 = SingleGame(player1, player2)
        self.SimpleGame2 = SingleGame(player2, player1)
        self.SimpleGame3 = SingleGame(player1, player2)
