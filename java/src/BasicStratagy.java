package src;

import java.util.ArrayList;

//TODO: MUST fix table values. find correct numbers from a mathmatician or something
// Ive been finding inconsistant data on optimal stratagy

public class BasicStratagy {
    // map for standard player hand (no aces and not a split)
    /*
     * To get correct first index: subtract 8, if less than zero take zero, elif
     * greater than 9 take 9 - maybe see when tbales are updated
     * (Starts at 8 and goes to 17)
     */
    private static final String[][] PLAYER_HARD_TOTAL = {
            { "-1", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H", "H" }, // 8
            { "-1", "H", "H", "D", "D", "D", "D", "H", "H", "H", "H", "H", "H", "H" }, // 9
            { "-1", "H", "D", "D", "D", "D", "D", "D", "D", "D", "H", "H", "H", "H" }, // 10
            { "-1", "H", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, // 11
            { "-1", "H", "H", "H", "S", "S", "S", "H", "H", "H", "H", "H", "H", "H" }, // 12
            { "-1", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H", "H", "H" }, // 13
            { "-1", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H", "H", "H" }, // 14
            { "-1", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H", "H", "H" }, // 15
            { "-1", "H", "S", "S", "S", "S", "S", "H", "H", "H", "H", "H", "H", "H" }, // 16
            { "-1", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" } // 17
    };

    // table starts at 2 and goes to 9: these numbers represent card other than ace
    private static final String[][] PLAYER_SOFT_TOTAL = {
            { "-1", "H", "H", "H", "H", "D", "D", "H", "H", "H", "H", "H", "H", "H" }, // 2
            { "-1", "H", "H", "H", "H", "D", "D", "H", "H", "H", "H", "H", "H", "H" }, // 3
            { "-1", "H", "H", "H", "D", "D", "D", "H", "H", "H", "H", "H", "H", "H" }, // 4
            { "-1", "H", "H", "H", "D", "D", "D", "H", "H", "H", "H", "H", "H", "H" }, // 5
            { "-1", "H", "H", "D", "D", "D", "D", "H", "H", "H", "H", "H", "H", "H" }, // 6
            { "-1", "H", "S", "D", "D", "D", "D", "S", "S", "H", "H", "H", "H", "H" }, // 7
            { "-1", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" }, // 8
            { "-1", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" } // 9
    };

    // starts at 1 and goes to 10
    private static final String[][] PLAYER_PAIR = {
            { "-1", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP" }, // 1
            { "-1", "H", "SP", "SP", "SP", "SP", "SP", "SP", "H", "H", "H", "H", "H", "H" }, // 2
            { "-1", "H", "SP", "SP", "SP", "SP", "SP", "SP", "H", "H", "H", "H", "H", "H" }, // 3
            { "-1", "H", "H", "H", "H", "SP", "SP", "H", "H", "H", "H", "H", "H", "H" }, // 4
            { "-1", "H", "D", "D", "D", "D", "D", "D", "D", "D", "H", "H", "H", "H" }, // 5
            { "-1", "H", "SP", "SP", "SP", "SP", "SP", "H", "H", "H", "H", "H", "H", "H" }, // 6
            { "-1", "H", "SP", "SP", "SP", "SP", "SP", "SP", "H", "H", "H", "H", "H", "H" }, // 7
            { "-1", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP", "SP" }, // 8
            { "-1", "S", "SP", "SP", "SP", "SP", "SP", "S", "SP", "SP", "S", "S", "S", "S" }, // 9
            { "-1", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S" } // 10
    };

    /*
     * Input: A processed version of a player hand - the ArrayList returned from
     * Hand.getBasicStratagyHand(). Should be length 1 or 2. Never larger and never
     * empty/Null
     * 
     * Output: Returns what action the player should take in the form of a string.
     * Output are "S": Stand, "H": Hit, "SP": Split, "D": Double Down
     */
    public static String getPlayerDecision(ArrayList<Integer> playerHand, int dealerUpCard) {
        int playerTableIndex;

        // use split table
        if (handIsSplitable(playerHand)) {
            // get row for table
            playerTableIndex = playerHand.get(0) - 1;
            // control for index out of bounds (convert J,Q,K to a 10)
            if (playerTableIndex > 9) {
                playerTableIndex = 9;
            }
            return PLAYER_PAIR[playerTableIndex][dealerUpCard];

        } else if (handIsSoftTotal(playerHand)) {
            // get row for table
            playerTableIndex = playerHand.get(1) - 2;
            // NOTE: playerHand[1] should never be more than 9
            return PLAYER_SOFT_TOTAL[playerTableIndex][dealerUpCard];

        } else {
            if (playerHand.get(0) < 8) {
                return "H";
            }
            if (playerHand.get(0) > 17) {
                return "S";
            }
            playerTableIndex = playerHand.get(0) - 8;

            return PLAYER_HARD_TOTAL[playerTableIndex][dealerUpCard];
        }
    }

    /*
     * Input: Processed dealer hand - if no ace in hand should be length 1 which is
     * sum of cards in hand. if there is an ace [0] == 1 and [1] == sum of remaining
     * cards. Should never recieve an ace with [1] > 10
     * 
     * Output: String of length 1 - should be either "H" or "S" for hit or stand
     * 
     * Function: Returns hit or stand decision based on rules for the dealer
     */
    public static String getDealerDecision(ArrayList<Integer> dealerHand) {

        int dealerHandValue = 0;
        boolean soft = false;
        // dealer has an ace in hand
        if (dealerHand.size() == 2) {
            // // figure out if ace should be treated as a 1 or 11
            if (dealerHand.get(1) + 11 <= 21) {
                dealerHandValue = dealerHand.get(1) + 11;
                soft = true;
            } else {
                dealerHandValue = dealerHand.get(1) + 1;
            }
        }
        // dealer does not have an ace
        else {
            dealerHandValue = dealerHand.get(0);
        }
        // hit vs stand logic based of value... dealer hits soft 17
        if (soft && dealerHandValue == 17) {
            return "H";
        } else if (dealerHandValue < 17) {
            return "H";
        } else {
            return "S";
        }

    }

    // given processed player hand, check if we should use the split table
    private static boolean handIsSplitable(ArrayList<Integer> h) {
        if (h.size() == 2 && h.get(0) == h.get(1)) {
            return true;
        }
        return false;
    }

    // given processed player hand, check if we should use the soft total table (has
    // ace)
    private static boolean handIsSoftTotal(ArrayList<Integer> h) {
        if (h.size() == 2 && h.get(0) == 1) {
            return true;
        }
        return false;
    }

}
