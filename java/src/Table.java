package src;

/* 
This class represents a blackjack table
Attributes: players at the table, shoe for the table, dealer for the table
*/

public class Table {
    Shoe shoe;
    Player[] players;
    Hand dealer;

    // when table object is created initialize players at the table
    public Table(int numDecks, int numPlayers) {
        players = new Player[numPlayers];
        dealer = new Hand();
        shoe = new Shoe(numDecks);
    }


}
