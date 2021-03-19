public class Dealer implements Player{
    private Cards cards = new Cards();
    private Deck deck;

    public Dealer(Deck deck) {
        this.deck = deck;
        draw(2);
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public void draw(int times) {
        for(int i=0;i<times;i++){
            Card card = deck.pop();
            cards.add(card);
        }
    }

    @Override
    public int getStat() {
        return cards.getStat();
    }


}
