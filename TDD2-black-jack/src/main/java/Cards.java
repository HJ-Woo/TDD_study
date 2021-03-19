import java.util.Collections;
import java.util.LinkedList;

public class Cards {
    private LinkedList<Card> cards=new LinkedList<Card>();

    public void add(Card card) {
        cards.add(card);
    }

    public Card peek() {
        return cards.peek();
    }

    public int getStat() {
        return cards.stream().mapToInt(Card::getNumber).sum();
    }

    public Card pop() {
        return cards.pop();
    }

    public int getSize() {
        return cards.size();
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }
}
