class Matrix:
    def __init__(self, x, y):
        self.dictionary = {}
        
        for i in range(x):
            self.dictionary[i] = {}
            
        for i in range(x):
            for j in range(y):
                self.dictionary[i][j] = None
                
    def put(self, i, j, elem):
        self.dictionary[i][j] = elem
        
    def get(self, i, j):
        return self.dictionary[i][j]
    
        