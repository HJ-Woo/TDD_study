import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardTest {

    //1. Card를 생성한다
    //Card의 suit 구분은 heart, spade, clova, diamond
    //2. getNumber()로 카드의 점수를 계산한다.
    @Test
    public void 카드생성(){
        assertThat(5).isEqualTo(5);
        Card card = new Card("heart","10");
        assertThat(card.getNumber()).isEqualTo(10);
        assertThat(card.getSuit()).isEqualTo("heart");
    }


}