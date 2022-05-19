package src;

import java.util.ArrayList;


//TODO: class might be redundant after creating table obejct
public class PlayShoe {

    /*
     * ~ flow of method ~
     * 1) create hand object(s)
     * 2) iterate through hands (including dealer) and deal one card each until each
     * have 2 cards
     * 3) check all hands for blackjack -> all winners are paid, unless dealer has -
     * then push with other BJ and non BJ lose money
     * 4) starting with first hand, getPlayer decision until hand is either busted
     * or stands (also handle splits)- then move to next
     * 5) once all hands have had their turns it is the dealers -> getDelaer
     * decision until bust or stand
     * - if dealer busts all non busted hands win
     * 6) if dealer stands must find all hands with handValue > dealerHandValue. if
     * == then push
     * 7) if we havnt hit the new shoe card -> reset all hands and go again
     * 
     */
    public static void play(Shoe shoe, int numPlayers) {
        // (1) create hands list with list of hands. [0] is dealer
        ArrayList<Hand> hands = initializeBlackJackTable(numPlayers);

        // (7) keep playing hands until we have passed the cut card
        while (!shoe.passedCutCard()) {
            // (2) give each player and dealer their first two cards to start the hand
            dealFirstTwoCards(shoe, hands);

            /*
             * Create array of string which stores outcome of each hand <WIN, LOSE, PUSH>
             * - This can be passed to output data class (print to CSV) to record winners
             * and losers of hand
             * Note: Dealer win or lose in relative, so index [0] will always be Null
             */
            Outcomes[] outcomes = new Outcomes[hands.size()];

            // (3) check which (if any) hands have BJ
            setBlackJackInstanceBools(hands);

            // (3) if dealer has BJ, hands with it push, others lose
            // else: dealer doesnt have BJ, all hands with win - rest play on
            for (int i = 1; i < hands.size(); i++) {

                if (hands.get(0).getHasBlackJack()) { // dealer has BJ
                    if (hands.get(i).getHasBlackJack()) { // player at position i also has BJ -> push
                        outcomes[i] = Outcomes.PUSH;
                    } else { // player at i does not have BJ -> loses
                        outcomes[i] = Outcomes.LOSE;
                    }
                } else { // dealer does not have BJ
                    if (hands.get(i).getHasBlackJack()) { //player has BJ -> win
                        outcomes[i] = Outcomes.WIN;
                    }
                    //else: player does not have BJ -> do nothing and play on
                }
            }

        }
    }

    /*
     * Input: number of players that are playing the shoe
     * 
     * Output: List of hand objects (Index [0] is the dealers hand)
     * 
     * Function: creates and list of hand object to simulate players sitting at the
     * blackjack table ready to play the shoe
     */
    private static ArrayList<Hand> initializeBlackJackTable(int numP) {
        // list of hands that holds all hands playing in the shoe. Index 0 is dealer
        ArrayList<Hand> h = new ArrayList<>();
        // Initialize dealer hand at index [0]
        h.add(0, new Hand());
        h.get(0).setIsDealer(true);

        for (int i = 1; i < numP + 1; i++) {
            h.add(i, new Hand());
        }
        return h;
    }

    /*
     * Input: Shoe and list of hands being played
     * Output: None
     * 
     * Funciton: Deals two cards to each player at the table to begin the hand
     */
    private static void dealFirstTwoCards(Shoe s, ArrayList<Hand> hList) {
        // go around table twice
        for (int i = 0; i < 2; i++) {
            // visit each hand at the table
            for (int j = 1; j < hList.size(); j++) {
                // get top card from shoe and add it to current hand
                s.hit(hList.get(j));
            }
            // give dealer his card after player(s)
            s.hit(hList.get(0));
        }
    }

    /*
     * Input: list of hands at the table
     * Output: None
     * 
     * Function: Called at the start of the hand (after initial 2 cards delt) and
     * sets the hasBlackJack instance variable for each of the hands in hands
     */
    private static void setBlackJackInstanceBools(ArrayList<Hand> hands) {
        for (Hand h : hands) {
            if (h.isTwentyOne()) {
                h.setHasBlackJack(true);
            }
        }
    }

    // default to one deck if arg not specified
    public static void play(Shoe shoe) {
        play(shoe, 1);
    }
}
