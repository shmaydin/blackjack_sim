package src;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private ArrayList<Integer> cards = new ArrayList<>();
    private boolean isDealer;

    // constructor if no arguments given
    public Hand() {
        // default to not a dealer
        isDealer = false;
    }

    // contructor if one argument given
    public Hand(boolean d) {
        isDealer = d;
    }

    // adds given card to the hand
    public void addCard(int c) {
        cards.add(c);
    }

    /*
     * - If the player has a standard hand, method will return
     * its sum as a list of length 1 (EX: if cards = [5,12,3] -> return 20)
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
            handValue = cards;
        }

        // Atleast one ace in the hand
        else if (hasAce) {
            int totalValue = getBlackJackValue();

            // if sum of hand and ace is more than 11 we use hard totals table
            if (totalValue > 11) {
                handValue.add(totalValue);
            } else { //we use soft totals
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

    public ArrayList<Integer> getCardsFT() {
        return cards;
    }

    public void removeCardFT() {
        if (cards.size() > 0) {
            cards.remove(cards.size() - 1);
        }
        return;
    }
}
