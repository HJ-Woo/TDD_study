import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeckTest {
    Deck deck;

    //TODO 1. 카드 52장을 생성 및 셔플
    @BeforeEach
    void 덱생성(){
        deck = new Deck();
//      assertThat(deck.getCards().getSize()).isEqualTo(52);
    }

    //TODO 2.pop 카드 한장 꺼내가기
    @Test
    void 덱에서꺼내가기(){
        deck.pop();
        assertThat(deck.getCards().getSize()).isEqualTo(51);
    }
}
