package test;

import org.junit.Test;

import src.Hand;

import static org.junit.jupiter.api.Assertions.*;

public class Hand_Test {

    /**************** Hands.getBasicStratagyHand *******************************/

    // split hand
    @Test
    public void basicStratagyHandSplit() {
        Hand h = new Hand();

        h.addCard(4);
        h.addCard(4);

        assertEquals(1, h.getBasicStratagyHand().size());
        assertEquals(4, h.getBasicStratagyHand().get(0));

        return;
    }
}
