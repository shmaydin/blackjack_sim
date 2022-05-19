package src;

import java.util.ArrayList;

/*
This class represent a player at the blackjack table.
Attributes: - list of hands (player can have more than one hand after splitting)
- outcomes: list of outcomes of each of the players hands (indices match hands)
- isDone: flag is set when the player is done playing (set to true when outcomes for all hands is filled)

Player object also will allow tracking of winning/loses
*/
public class Player {

    private ArrayList<Hand> hands;
    private boolean isDone;

    //when new player in created they have an empty hand with no outcomes
    public Player() {
        hands = new ArrayList<>(1);
        hands.set(0, new Hand());

        isDone = false;
    }

    //getter for a hand at specific index
    public Hand getHandAt(int i) {
        return hands.get(i);
    }

    //getter for list of all hands that player has
    public ArrayList<Hand> getAllHands(){
        return hands;
    }

    //setter: sets isDone for the player
    public void setIsDone(boolean b){
        isDone = b;
    }

    //getter: returns value of isDone attribue
    public boolean getIsDone() {
        return isDone;
    }
}
