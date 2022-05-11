package src;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Shoe {

    private int numDecks = 1;

    private ArrayList<Integer> shoe = new ArrayList<Integer>();
    private int cutCardIndex = 0;
    private int topCardIndex;

    public Shoe(int numDecks) {
        this.numDecks = numDecks;
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
        topCardIndex = shoe.size() - 1;

        return;
    }

    private void placeCutCard() {

        int lowerBound = shoe.size() / 6;
        int upperBound = shoe.size() / 2;

        cutCardIndex = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1);

        return;
    }

    private Integer getTopCard(){
        int card = shoe.get(topCardIndex);
        topCardIndex--;

        //make sure top card index is valid
        if (topCardIndex < 0){
            System.out.println("top card index is out of bounds, deck is empty");
        }

        return card;
    }

    public void hit(Hand h){
        h.addCard(getTopCard());
    }

    public void debugInfo() {
        System.out.println(shoe);
        System.out.println("CC is: " + cutCardIndex);
        System.out.println("size is: " + shoe.size());


        return;
    }
}
