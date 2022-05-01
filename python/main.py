from python.deck import *

temp = Shoe(1)
temp.shuffle()

for i in range(len(temp.deck)):
    print(temp.deck[i].displayCard())