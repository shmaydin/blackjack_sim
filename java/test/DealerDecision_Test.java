package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import src.BasicStratagy;

public class DealerDecision_Test {
    
    // Test for if dealer has an ace and any value
    @Test
    public void dealerHasAce(){
        ArrayList<Integer> ph = new ArrayList<>(); //ph stands for processed hand

        ph.add(0, 1); //dealer has an ace so [0] == 1
        ph.add(1, 1);
        assertEquals("H", BasicStratagy.getDealerDecision(ph));

        //dealer has an ace and a sum from 2 to 6(soft 13 to soft 17)
        //All of these should hit
        for (int i = 2; i <= 6; i++){
            ph.set(1,i);
            assertEquals("H", BasicStratagy.getDealerDecision(ph));
        }

        //dealer stays with 7 -> 10
        // for (int i = 7; i <= 10; i++){
        //     ph.set(1, i);
        //     assertEquals("S", BasicStratagy.getDealerDecision(ph));
        // }
        

        ph.set(1,7);
        assertEquals(Arrays.asList(1,7), ph);
        assertEquals("S", BasicStratagy.getDealerDecision(ph));

        ph.set(1,8);
        assertEquals("S", BasicStratagy.getDealerDecision(ph));

        ph.set(1,9);
        assertEquals("S", BasicStratagy.getDealerDecision(ph));

        ph.set(1,10);
        assertEquals("S", BasicStratagy.getDealerDecision(ph));


    }


    @Test
    public void dealerDoesNotHaveAce() {
        ArrayList<Integer> ph = new ArrayList<>(); //ph stands for processed hand
        ph.add(0,0);
        
        //sum of dealer hand is from 2 to 16 (Hard)-> dealer always hits these
        for (int i = 2; i < 17; i++) {
            ph.set(0,i);
            assertEquals("H", BasicStratagy.getDealerDecision(ph));
        }

        //17-21 -> dealer always stands
        for (int i = 17; i <= 21; i++) {
            ph.set(0,i);
            assertEquals("S", BasicStratagy.getDealerDecision(ph));
        }
    }
}
