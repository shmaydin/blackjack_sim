package src;

import java.util.ArrayList;

/*
This class represent a player at the blackjack table.
Attributes: list of hands (player can have more than one hand after splitting)

Player object also will allow tracking of winning/loses
*/
public class Player {

    ArrayList<Hand> hands;
    ArrayList<Outcomes> outcome;

    //when new player in created they have an empty hand with no outcomes
    public Player() {
        hands = new ArrayList<>(1);
        hands.set(0, new Hand());
        
        outcome = new ArrayList<>(1);
    }
}
