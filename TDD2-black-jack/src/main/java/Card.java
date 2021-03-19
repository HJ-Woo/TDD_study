public class Card {
    private final String suit;
    private final String number;

    public Card(String suit, String number) {
        this.suit = suit;
        this.number = number;
    }

    public String getSuit() {
        return this.suit;
    }


    public int getNumber() {
        if(number.equals("A")){
            return 1;
        }
        if(number.equals("Q") || number.equals("K") || number.equals("J")){
            return 10;
        }
        return Integer.parseInt(number);
    }
}
