import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameTest {
    private GameFactory gameFactory;
    private Game game;

    @BeforeEach
    void setUpGame() {
        ArrayList<int[]> rollnum = new ArrayList<>();
        rollnum.add(new int[]{1, 2});
        rollnum.add(new int[]{7, 3});
        rollnum.add(new int[]{10, 0});
        rollnum.add(new int[]{2, 5});
        rollnum.add(new int[]{4, 6});
        rollnum.add(new int[]{3, 7});
        rollnum.add(new int[]{1, 2});
        rollnum.add(new int[]{1, 2});
        rollnum.add(new int[]{1, 2});
        rollnum.add(new int[]{1, 2, 3});
        RollNums rollNums = new RollNums(rollnum);

        gameFactory = new GameFactory();
        this.game = gameFactory.game(rollNums);
    }

    @Test
    @DisplayName("Game 초기화")
    void testInitGame() {
         Assertions.assertEquals(game.getTotalScore(), 0);

    }


    @Test
    void i번째_프레임이_strike일때의_점수계산(){

    }

}
