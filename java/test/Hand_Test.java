package test;

import org.junit.Test;

import src.Hand;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(List.of(4,4), h.getBasicStratagyHand());

        //make sure we dont split a hand of size 3
        h.addCard(5);
        assertNotEquals(List.of(4,4), h.getBasicStratagyHand());

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
        assertEquals(List.of(1, 18), h.getBasicStratagyHand());

        h = new Hand();

        h.addCard(4);
        h.addCard(10);
        h.addCard(1);
        assertEquals(List.of(1, 14), h.getBasicStratagyHand());

        return;
    }

    // hand has more than 1 ace
    @Test
    public void basicStratagyMoreThanOneAce() {
        Hand h = new Hand();

        //hand is two aces, check if we want to split it
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
        assertEquals(List.of(1, 13), h.getBasicStratagyHand());
    }

    //standard hand - no aces and not splitable
    @Test
    public void basicStratagyStandardHand(){
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
}
