class Card:
    def __init__(self, val=-1, suit='a'):
        self.val = val
        self.suit = suit

    def displayCard(self):
        return [self.val, self.suit]
