import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GamerTest {
    //게이머 생성
    //draw를 했을때, 카드가 추가된다(카드길이 테스트)
    //TODO 3. Hit은 누가 주관?

    Gamer gamer;
    Deck deck;
    @BeforeEach
    void 게이머생성(){
        deck = new Deck();
        gamer = new Gamer(deck);
    }

    @Test
    void draw테스트(){
        gamer.draw(1);
        Cards cards = gamer.getCards();
        assertThat(cards.getSize()).isEqualTo(3);
    }
}
