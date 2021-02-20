import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {

    //Todo Game을 생성하면 프레임 10개 리스트를 생성한다
    //Todo start()에 한 프레임씩 방문하면서 roll()을 실행한다
    //Todo end()가 실행되었을 경우 게임을 종료하고 점수를 출력한다

    @Test
    void testInitGame() {
        Game game = new Game();

        Assertions.assertEquals(game.frames.size(), 10);
        Assertions.assertEquals(game.get(0).getClass(), Frame.class);
    }
}
