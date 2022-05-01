from python.card import Card
import random


class Shoe:
    def __init__(self, numDecks=1):
        self.numDecks = numDecks
        self.deck =  self.createShoe(numDecks)
        self.cutCardIndex  = 0



    def shuffle(self):
        random.shuffle(self.deck)
        self.placeCutCard()

    def placeCutCard(self):
        lower = len(self.deck) // 6
        upper = len(self.deck) // 2
        self.cutCardIndex = random.randint(lower, upper)



    #initializes and returns a single deck of 52 cards
    def create_single_deck(self):
        suits = ['H', 'D', 'S', 'C']
        deck = []

        for suit in suits:
            for val in range(1,14):
                deck.append(Card(val, suit))
        
        return deck
    
    #initializes and returns a multideck shoe
    def createShoe(self, numDecks):
        singleDeck = self.create_single_deck()
        fullDeck = []

        for _ in range(numDecks):
            fullDeck += singleDeck
        
        return fullDeck



    def getNextCard(self):
        card = self.deck.pop()
        return card
