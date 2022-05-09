import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Shoe {

    private int numDecks = 1;

    private ArrayList<Integer> shoe = new ArrayList<Integer>();
    private int cutCardIndex = 0;

    Shoe(int numDecks) {
        this.numDecks = numDecks;
        createShoe();
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

    public void createShoe() {
        ArrayList<Integer> deck = createDeck();

        for (int i = 0; i < numDecks; i++) {
            this.shoe.addAll(deck);
        }
        return;
    }

    public void shuffle() {
        Collections.shuffle(shoe);
        placeCutCard();

        return;
    }

    private void placeCutCard() {

        int lowerBound = shoe.size() / 6;
        int upperBound = shoe.size() / 2;

        cutCardIndex = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);

        return;
    }


    
    public void debugInfo() {
        shuffle();
        System.out.println(shoe);
        System.out.println("CC is: " + cutCardIndex);
        System.out.println("size is: " + shoe.size());
        return;
    }
}
