package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import src.BasicStratagy;

public class PlayerDecision_Test {

    @Test
    public void hardTotalsFullCoverage() {
        ArrayList<Integer> hand = new ArrayList<>();

        hand.add(16);
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 10));

        hand.clear();
        hand.add(4);
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 2));

        hand.clear();
        hand.add(14);
        // player 14 vs dealer >= 7
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 7));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 8));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 9));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 10));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 11));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 12));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 13));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 1));

        // player 14 vs dealer < 7
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 2));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 3));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 4));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 5));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 6));

        hand.clear();
        hand.add(17);
        // player 17 stands against all
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 7));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 8));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 9));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 10));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 11));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 12));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 13));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 1));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 2));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 3));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 4));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 5));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 6));

        hand.clear();
        hand.add(20);
        // player 20 stands against all
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 7));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 8));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 9));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 10));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 11));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 12));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 13));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 1));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 2));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 3));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 4));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 5));
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 6));

        hand.clear();
        hand.add(10);
        // player 10 doubles against all except 10 and Ace
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 7));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 8));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 9));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 10));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 11));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 12));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 13));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 1));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 2));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 3));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 4));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 5));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 6));
    }

    // Pair Splitting
    @Test
    public void playerPair() {
        ArrayList<Integer> hand = new ArrayList<>();

        hand.addAll(Arrays.asList(1, 1));
        // check pair of aces vs all dealer card
        for (int i = 1; i < 14; i++) {
            assertEquals("SP", BasicStratagy.getPlayerDecision(hand, i));
        }

        hand.clear();
        hand.addAll(Arrays.asList(8, 8));
        // pair of 8s should always be split
        for (int i = 1; i < 14; i++) {
            assertEquals("SP", BasicStratagy.getPlayerDecision(hand, i));
        }

        hand.clear();
        hand.addAll(Arrays.asList(8, 8));
        // pair of 5s is never split
        for (int i = 1; i < 9; i++) {
            assertEquals("SP", BasicStratagy.getPlayerDecision(hand, i));
        }

        hand.clear();
        hand.addAll(Arrays.asList(6, 6));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 1));
        assertEquals("SP", BasicStratagy.getPlayerDecision(hand, 2));
        assertEquals("SP", BasicStratagy.getPlayerDecision(hand, 3));
        assertEquals("SP", BasicStratagy.getPlayerDecision(hand, 4));
        assertEquals("SP", BasicStratagy.getPlayerDecision(hand, 5));
        assertEquals("SP", BasicStratagy.getPlayerDecision(hand, 6));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 7));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 8));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 9));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 10));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 11));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 12));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 13));

        hand.clear();
        hand.addAll(Arrays.asList(10, 10));
        // cannot split a 10,J
        assertEquals("S", BasicStratagy.getPlayerDecision(hand, 6));
    }

    @Test
    public void softTotals() {
        ArrayList<Integer> hand = new ArrayList<>();

        hand.addAll(Arrays.asList(1, 9));
        // player should always stay with A,9
        for (int i = 1; i < 14; i++) {
            assertEquals("S", BasicStratagy.getPlayerDecision(hand, i));
        }

        hand.clear();
        hand.addAll(Arrays.asList(1, 5));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 1));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 2));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 3));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 4));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 5));
        assertEquals("D", BasicStratagy.getPlayerDecision(hand, 6));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 7));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 8));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 9));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 10));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 11));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 12));
        assertEquals("H", BasicStratagy.getPlayerDecision(hand, 13));
    }

}
