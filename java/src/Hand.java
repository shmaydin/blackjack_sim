package src;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Integer> cards = new ArrayList<>();
    private boolean isDealer;
    private boolean doubleAllowed; // doube is only allowed on first turn, not after hand has been hit
    private boolean hasBlackJack;
    private boolean isBusted;
    private boolean isStanding;
    private Outcomes outcome;

    // constructor if no arguments given
    public Hand() {
        // default to not a dealer
        isDealer = false;
        doubleAllowed = true;
        hasBlackJack = false;
        isBusted = false;
        isStanding = false;
        outcome = Outcomes.PLAYING;
    }

    // contructor if one argument given
    public Hand(boolean d) {
        isDealer = d;
        doubleAllowed = true;
        hasBlackJack = false;
        isBusted = false;
        isStanding = false;
        outcome = Outcomes.PLAYING;
    }

    // adds given card to the hand
    public void addCard(int c) {
        cards.add(c);
    }

    /*
     * - If the player has a standard hand, method will return
     * its sum as a list of length 1 (EX: if cards = [5,12,3] -> return [20])
     * - If cards contains at least one ace, method will return a list of length two
     * which
     * contains [1, sum of values other than ace]. Note that additional aces are
     * included
     * in index 1 as a value of 1 as if you have two aces both being 11 the hand is
     * busted.
     * - If cards is length 2 and the values are the same (cannot split a jack and
     * queen for
     * example) it will return a list of length 2 containing both cards (EX: [8,8])
     * 
     * TODO: Another method should handle the basic stratagy decisions (dealer
     * method and
     * player method) and decide if the hand is busted or not. Yet another method
     * should
     * determine winner
     */
    public ArrayList<Integer> getBasicStratagyHand() {
        boolean hasAce = cards.contains(1);

        ArrayList<Integer> handValue = new ArrayList<>();

        // we can split the hand
        if (cards.size() == 2 &&
                cards.get(0) == cards.get(1)) {

            // handValue is list of length 2 where both values are equivalent
            handValue = new ArrayList<>(cards);
        }

        // Atleast one ace in the hand
        else if (hasAce) {
            int totalValue = getBlackJackValue();

            // if sum of hand and ace is more than 11 we use hard totals table
            if (totalValue > 11) {
                handValue.add(totalValue);
            } else { // we use soft totals
                // handValue[0] == 1, handValue[1] == sum of cards other than one ace
                handValue.add(1);
                handValue.add(totalValue - 1);
            }
        }
        // normal hand -> no ace and not splitable
        else {
            // handValue is a list of length = 1
            handValue.add(getBlackJackValue());
        }
        return handValue;
    }

    /*
     * processes the dealers hand (mainly aces) so that BS class can make a hit or
     * stand decision
     */
    public ArrayList<Integer> getDealerValue() {
        boolean hasAce = cards.contains(1);
        int handValue = getBlackJackValue();
        ArrayList<Integer> output = new ArrayList<>();

        // not an ace in the hand, just take total sum || with ace but ace is always 1
        // otherwise bust
        if (!hasAce || handValue > 11) {
            output.add(handValue);
            return output;
        } else {
            output.add(1);
            output.add(handValue - 1);
            return output;
        }

    }

    /*
     * Input: Ran on the hand object that it is called on
     * 
     * Output: true or false based on if hand has gone over 21 or not
     * 
     * Function: Calculates weather the hand in question has gone over 21.
     * Aces can always be counted as 1 because we are checking for bust.
     */
    public boolean isBusted() {
        int hVal = getBlackJackValue();

        // busted if value is over 21, else not busted
        if (hVal > 21) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * Input: is ran on a hand object
     * Output: True for blackjack, false for not blackjack
     * Function: This does not control for busted hands, the boolean is soley based
     * on if the hand value == 21 or not. must consider aces as hard or soft.
     */
    public boolean isTwentyOne() {
        boolean hasAce = false;
        int hVal = getBlackJackValue();

        // set ace flag if the hand has atleast one ace
        if (cards.contains(1)) {
            hasAce = true;
        }

        // if theres not an ace return if hVal == 21
        if (!hasAce) {
            return hVal == 21;
        }
        // if ace is 1 and hand is 21
        else if (hVal == 21) {
            return true;
        }
        // check if making the ace == 11 yeilds blackjack -> only add 10 because hVal
        // has hand as ace == 1
        else {
            return hVal + 10 == 21;
        }

    }

    /*
     * returns the value of the hand with aces counted as one and face cards counted
     * as 10
     */
    private int getBlackJackValue() {
        int bjTotal = 0;

        for (Integer c : cards) {
            if (c > 10) {
                bjTotal += 10;
            } else {
                bjTotal += c;
            }
        }
        return bjTotal;
    }

    public void invalidateDouble() {
        doubleAllowed = false;
    }

    // if hand is either busted or standing return true, else false
    public boolean isHandDone() {
        if (isBusted || isStanding) {
            return true;
        }
        return false;
    }

    public void setHandBusted() {
        isBusted = true;
    }

    public void setIsStanding() {
        isStanding = true;
    }

    public void setIsDealer(boolean d) {
        isDealer = d;
    }

    public void setHasBlackJack(boolean bj) {
        hasBlackJack = bj;
    }

    public boolean getHasBlackJack() {
        return hasBlackJack;
    }

    public boolean getIsHandBusted() {
        if (isBusted) {
            return true;
        }
        return false;
    }

    //dealer up card is first card in dealers hand
    public int getDealerUpCard() {
        return cards.get(0);
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    //setter: set the outcome of the hand
    public void setOutcome(Outcomes oc) {
        outcome = oc;
    }
    
    //getter: get outcome of the hand
    public Outcomes getOutcome() {
        return outcome;
    }

    public void removeCardFT() {
        if (cards.size() > 0) {
            cards.remove(cards.size() - 1);
        }
        return;
    }
}
