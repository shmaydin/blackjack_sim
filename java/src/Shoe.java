package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Shoe {

    private int numDecks;

    private ArrayList<Integer> shoe = new ArrayList<Integer>();
    private int cutCardIndex = 0;
    private int topCardIndex;

    public Shoe(int cntDecks) {
        this.numDecks = cntDecks;
        createShoe();
        shuffle();
    }

    private ArrayList<Integer> createDeck() {
        ArrayList<Integer> deck = new ArrayList<>();

        for (int i = 1; i <= 13; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(i);
            }
        }

        return deck;
    }

    private void createShoe() {
        ArrayList<Integer> deck = createDeck();

        for (int i = 0; i < numDecks; i++) {
            this.shoe.addAll(deck);
        }
        return;
    }

    private void shuffle() {
        Collections.shuffle(shoe);
        placeCutCard();
        topCardIndex = shoe.size() - 1;

        return;
    }

    private void placeCutCard() {

        int lowerBound = shoe.size() / 6;
        int upperBound = shoe.size() / 2;

        cutCardIndex = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);

        return;
    }

    private Integer getTopCard() {
        int card = shoe.get(topCardIndex);
        topCardIndex--;

        // make sure top card index is valid
        if (topCardIndex < 0) {
            System.out.println("top card index is out of bounds, deck is empty");
        }

        return card;
    }

    public void hit(Hand hand) {
        hand.addCard(getTopCard());
    }

    public boolean passedCutCard() {
        if (topCardIndex > cutCardIndex) {
            return false;
        }
        return true;
    }

    public ArrayList<Integer> getCardsInShoeFT() {
        return shoe;
    }

    public int getTopCardFT() {
        return topCardIndex;
    }
}
