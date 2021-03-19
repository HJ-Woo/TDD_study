public class Deck {
    private Cards cards = new Cards();

    public Deck() {
        String[] suits = { "Heart", "Spade", "Diamond","Clova"};
        for(String suit : suits){
            generateNumberOf(suit);
        }
        cards.shuffle();
    }

    private void generateNumberOf(String suit){
        String[] numbers = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        for(String number : numbers){
            Card card = new Card(suit, number);
            cards.add(card);
        }
    }

    public Cards getCards() {
        return cards;
    }

    public Card pop() {
        return cards.pop();
    }
}
