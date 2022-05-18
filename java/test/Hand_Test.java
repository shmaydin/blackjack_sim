package test;

import org.junit.Test;

import src.Hand;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class Hand_Test {

    /**************** Hands.getBasicStratagyHand *******************************/

    // split hand
    @Test
    public void basicStratagyHandSplit() {
        Hand h = new Hand();

        h.addCard(4);
        h.addCard(4);

        assertEquals(2, h.getBasicStratagyHand().size());
        assertEquals(List.of(4, 4), h.getBasicStratagyHand());

        // make sure we dont split a hand of size 3
        h.addCard(5);
        assertNotEquals(List.of(4, 4), h.getBasicStratagyHand());

        return;
    }

    // hand has one ace
    @Test
    public void basicStratagyOneAce() {
        Hand h = new Hand();

        h.addCard(1);
        h.addCard(12);
        assertEquals(List.of(1, 10), h.getBasicStratagyHand());

        h.removeCardFT();
        h.addCard(10);
        assertEquals(List.of(1, 10), h.getBasicStratagyHand());

        h.removeCardFT();
        h.addCard(9);
        assertEquals(List.of(1, 9), h.getBasicStratagyHand());

        h.removeCardFT();
        h.addCard(3);
        assertEquals(List.of(1, 3), h.getBasicStratagyHand());

        h.addCard(5); // after this hand is [1,3,5]
        assertEquals(List.of(1, 8), h.getBasicStratagyHand());

        h.addCard(11); // after hand is [1,3,5,11]
        assertEquals(List.of(19), h.getBasicStratagyHand());

        h = new Hand();

        h.addCard(4);
        h.addCard(10);
        h.addCard(1);
        assertEquals(List.of(15), h.getBasicStratagyHand());

        return;
    }

    // hand has more than 1 ace
    @Test
    public void basicStratagyMoreThanOneAce() {
        Hand h = new Hand();

        // hand is two aces, check if we want to split it
        h.addCard(1);
        h.addCard(1);
        assertEquals(List.of(1, 1), h.getBasicStratagyHand());

        h.addCard(4);
        assertEquals(List.of(1, 5), h.getBasicStratagyHand());

        h = new Hand();

        h.addCard(2);
        h.addCard(1);
        h.addCard(13);
        h.addCard(1);
        assertEquals(List.of(14), h.getBasicStratagyHand());

        h = new Hand();

        h.addCard(1);
        h.addCard(10);
        assertEquals(List.of(1, 10), h.getBasicStratagyHand());

    }

    // standard hand - no aces and not splitable
    @Test
    public void basicStratagyStandardHand() {
        Hand h = new Hand();

        h.addCard(2);
        h.addCard(9);
        assertEquals(List.of(11), h.getBasicStratagyHand());

        h.addCard(7);
        assertEquals(List.of(18), h.getBasicStratagyHand());

        h.addCard(12);
        assertEquals(List.of(28), h.getBasicStratagyHand());

        h = new Hand();

        h.addCard(11);
        h.addCard(10);
        assertEquals(List.of(20), h.getBasicStratagyHand());

        h.removeCardFT();
        h.addCard(3);
        h.addCard(8);
        assertEquals(List.of(21), h.getBasicStratagyHand());

    }

    @Test
    public void dealerHandValueTest() {
        Hand h = new Hand();
        h.addCard(1);
        h.addCard(1);
        assertEquals(Arrays.asList(1, 1), h.getDealerValue());

        h.addCard(1);
        assertEquals(Arrays.asList(1, 2), h.getDealerValue());

        h.addCard(12);
        assertEquals(Arrays.asList(13), h.getDealerValue());

        h = new Hand();
        h.addCard(1);
        h.addCard(7);
        h.addCard(2);
        assertEquals(Arrays.asList(1, 9), h.getDealerValue());

        h = new Hand();
        h.addCard(5);
        assertEquals(Arrays.asList(5), h.getDealerValue());

        h.addCard(13);
        assertEquals(Arrays.asList(15), h.getDealerValue());

        h.addCard(3);
        assertEquals(Arrays.asList(18), h.getDealerValue());

        h.addCard(1);
        assertEquals(Arrays.asList(19), h.getDealerValue());

        h.addCard(10);
        assertEquals(Arrays.asList(29), h.getDealerValue());

        return;

    }

    // test for is blackjack if hand has an ace
    @Test
    public void checkHandWithAceFor21() {
        Hand h = new Hand();

        h.addCard(1);
        for (int i = 1; i < 10; i++) {
            h.addCard(i);
            assertEquals(false, h.isTwentyOne());
            h.removeCardFT();
        }

        h.addCard(10);
        assertEquals(true, h.isTwentyOne());
        h.removeCardFT();

        h.addCard(11);
        assertEquals(true, h.isTwentyOne());
        h.removeCardFT();

        h.addCard(12);
        assertEquals(true, h.isTwentyOne());
        h.removeCardFT();

        h.addCard(13);
        assertEquals(true, h.isTwentyOne());
        h.removeCardFT();

        h.addCard(7);
        h.addCard(3);
        assertEquals(true, h.isTwentyOne());
    }

    // chekcs hand without ace for BJ
    @Test
    public void checkHandWithoutAceFor21() {
        Hand h = new Hand();

        h.addCard(2);
        h.addCard(12);
        assertEquals(false, h.isTwentyOne());

        h.addCard(9);
        assertEquals(true, h.isTwentyOne());

    }

}
