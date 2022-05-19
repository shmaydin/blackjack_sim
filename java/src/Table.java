package src;

import java.util.LinkedList;
import java.util.Queue;

/* 
This class represents a blackjack table
Attributes: players at the table, shoe for the table, dealer for the table
*/

public class Table {
    Shoe shoe;
    Player[] players;
    Hand dealer;

    // when table object is created initialize players at the table
    public Table(int numPlayers) {
        players = new Player[numPlayers];
        dealer = new Hand();
        dealer.setIsDealer(true);
    }

    public void playShoe(int numDecks) {
        // (1) initialize the shoe with desired number of decks
        shoe = new Shoe(numDecks);

        // (8a) play another hand if we havnt gotten to the cut card yet
        while (!shoe.passedCutCard()) {

            // (2) deal initial two cards to the table
            dealFirstTwoCards();

            // (3) set isBlackJack for dealer and players. And set outcomes for affected
            // players
            checkTableForBlackJack();

            // TODO: iterate through players and let them play - hit/double/split until they
            // stand, repeat for all players at table

            // (4) iterate through players and play their hands
            for (Player player : players) {

                // skip player if player is done
                if (player.getIsDone()) { // player is done playing hands
                    continue;
                } else { // player is not done and we need to play the hands
                    // queue to hold all the players hands. when hand is split we add to to q
                    Queue<Hand> handQueue = new LinkedList<>();
                    // add all player hands to queue
                    for (Hand h : player.getAllHands()) {
                        handQueue.add(h);
                    }

                    // play each hand in the q. if split add new hand to end of q
                    while (handQueue.size() > 0) {
                        //hand were currently playing
                        Hand curHand = handQueue.remove();

                        //check if the hand is busted
                        if (curHand.isBusted()) {
                            
                        }

                        BasicStratagy.getPlayerDecision(curHand.getCards(), dealer.getDealerUpCard());

                    }

                }
            }

        }

    }

    /*
     * Input: None
     * Output: None
     * 
     * Funciton: Deals two cards from the shoe to each player and dealer
     * at the table to begin the hand
     */
    private void dealFirstTwoCards() {
        // go around table twice
        for (int i = 0; i < 2; i++) {
            // visit each hand at the table
            for (int j = 0; j < players.length; j++) {
                // get top card from shoe and add it to current hand
                shoe.hit(players[j].getHandAt(0));
            }
            // give dealer his card after player(s)
            shoe.hit(dealer);
        }
    }

    /*
     * Function: each player should only have one hand when this method is called ->
     * check each player and the dealers hand for BJ. If there is BJ jet that hands
     * hasBlackJack instance variable
     */
    private void checkTableForBlackJack() {
        // check each players hand for BJ -> if BJ set BJ instance bool
        for (Player p : players) {
            if (p.getHandAt(0).isTwentyOne()) {
                p.getHandAt(0).setHasBlackJack(true);
            }
        }
        // if dealer has BJ set hasBJ instance var for dealer
        if (dealer.isTwentyOne()) {
            dealer.setHasBlackJack(true);
        }
        // if anyone or dealer has BJ, they won (also push if dealer && players)
        setTableBlackJackOutcomes();
    }

    /*
     * Function: to be called after we have checked for blackjack initially
     */
    private void setTableBlackJackOutcomes() {
        for (Player p : players) {
            if (dealer.getHasBlackJack()) { // dealer has BJ
                if (p.getHandAt(0).getHasBlackJack()) { // player also has BJ -> push
                    p.getHandAt(0).setOutcome(Outcomes.PUSH);
                    p.setIsDone(true);
                } else { // player doesnt have BJ -> lose
                    p.getHandAt(0).setOutcome(Outcomes.LOSE);
                    p.setIsDone(true);
                }
            } else { // dealer doesnt have BJ
                if (p.getHandAt(0).getHasBlackJack()) { // player has BJ -> wins
                    p.getHandAt(0).setOutcome(Outcomes.WIN);
                    p.setIsDone(true);
                }
                // player doesnt have BJ -> play on
            }
        }
    }

}

/*
 * ~ flow of Table ~
 * 1) inititalize players at the table, shoe, dealer
 * 
 * 2) iterate through hands (including dealer) and deal one card each until each
 * have 2 cards
 * 
 * 3) check all hands for blackjack -> all winners are paid, unless dealer has -
 * then push with other BJ and non BJ lose money
 * 
 * 4) starting with first hand, getPlayer decision until hand is either busted
 * or stands (also handle splits)- then move to next
 * - splits handled by adding another hand to Player and interating through his
 * hands
 * 
 * 5) once all hands have had their turns it is the dealers -> getDelaer
 * decision until bust or stand
 * - if dealer busts all non busted hands win
 * - Note: if all hands have bj or all hands busted before dealer turn do not
 * have dealers turn
 * 
 * 6) if dealer stands must find all hands with handValue > dealerHandValue. if
 * == then push.
 * if dealer busts all hands which have not busted win - others lose
 * 
 * 7) write all outcome and necissary data to csv file
 * - thought: maybe play shoe method can return teh csv of what happend in that
 * shoe?
 * 
 * 8) if we havnt hit the new shoe card -> reset all players hands (and dealer)
 * and go again
 * - if we have - end for now, later give option to run another shoe
 * - maybe create a method that runs playShoe n times. returns massive CSV or
 * many smaller ones
 * 
 */