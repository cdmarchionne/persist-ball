class Player:
    def __init__(self, name):
        self.name = name
        self.wonGames = 0
        self.totalGames = 0

    def getName(self):
        return self.name

    def incWonGames(self):
        self.wonGames += 1
        self.totalGames += 1

    def getWonGames(self):
        return self.wonGames

    def incTotalGames(self):
        self.totalGames += 1

    def getTotalGames(self):
        return self.totalGames


