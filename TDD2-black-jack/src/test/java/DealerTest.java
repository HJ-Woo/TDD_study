import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    Dealer dealer;
    Deck deck;

    //1. 생성자에게 Deck을 알려준다
    //2. 생성자에게 카드 두개(Cards로 포장한)를 넣어준다.

    @BeforeEach
    void 딜러생성(){
        deck = new Deck();
        dealer = new Dealer(deck);
    }

    @Test
    void draw테스트_16점(){
        dealer.draw(1);
        Cards cards = dealer.getCards();
        assertThat(cards.getSize()).isEqualTo(3);
    }
}
