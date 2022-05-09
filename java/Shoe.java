import java.util.ArrayList;

public class Shoe {
    
    public int numDecks = 1;
    public ArrayList<Integer> shoe = new ArrayList<Integer>();
    public int cut_card_index = 0;

    Shoe(){}

    Shoe(int numDecks){
        this.numDecks = numDecks;
        this.shoe = createShoe(numDecks);
    }

    public static void createDeck(){
        
    }







}
