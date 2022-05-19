package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import src.Hand;
import src.Shoe;

public class Shoe_Test {

    // verify correct shoe creation with one deck
    @Test
    public void validShoe1Deck() {
        // deck size == 1
        Shoe s = new Shoe(1);
        ArrayList<Integer> sList = s.getCardsInShoeFT();

        HashMap<Integer, Integer> cardCnt = new HashMap<>();

        for (Integer val : sList) {
            cardCnt.merge(val, 1, Integer::sum);
        }
        // check there are 52 cards
        assertEquals(52, sList.size());

        // check there are 13 unique cards
        assertEquals(13, cardCnt.size());

        // check that there are 4 of each card Ace through King (1-13)
        for (int i = 1; i <= 13; i++) {
            assertEquals(4, cardCnt.get(i));
        }
        return;
    }

    // verify correct shoe creation with SIX decks
    @Test
    public void validShoe6Decks() {
        // deck size == 6
        Shoe s = new Shoe(6);
        ArrayList<Integer> sList = s.getCardsInShoeFT();

        HashMap<Integer, Integer> cardCnt = new HashMap<>();

        for (Integer val : sList) {
            cardCnt.merge(val, 1, Integer::sum);
        }
        // check there are 312 cards
        assertEquals(312, sList.size());

        // check there are 13 unique cards
        assertEquals(13, cardCnt.size());

        // check that there are 24 of each card Ace through King (1-13)
        for (int i = 1; i <= 13; i++) {
            assertEquals(24, cardCnt.get(i));
        }
        return;
    }
    
/******************************************************************************/
    // Verify Hit functionality -> leaves shoe and into hand
    @Test
    public void validateHit() {
        Shoe s = new Shoe(1);
        Hand h = new Hand();

        assertEquals(51, s.getTopCardFT());
        assertEquals(0, h.getCards().size());

        s.hit(h);

        assertEquals(50, s.getTopCardFT());
        assertEquals(1, h.getCards().size());

        s.hit(h);
        s.hit(h);
        s.hit(h);

        assertEquals(47, s.getTopCardFT());
        assertEquals(4, h.getCards().size());

        return;

    }

}