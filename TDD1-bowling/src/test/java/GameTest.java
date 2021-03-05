import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class GameTest {
    private GameFactory gameFactory;
    private Game game;
    private Game perfectGame;

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

        ArrayList<int[]> perfectRollNum = new ArrayList<>();
        for(int i=0; i<9; i++){
            perfectRollNum.add(new int[]{10, 0});
        }
        perfectRollNum.add(new int[]{10, 10, 10});
        RollNums perfectRollNums = new RollNums(perfectRollNum);

        gameFactory = new GameFactory();
        game = gameFactory.game(rollNums);
        perfectGame = gameFactory.game(perfectRollNums);
    }

    @Test
    @DisplayName("Game 초기화")
    void testInitGame() {
         Assertions.assertEquals(game.getTotalScore(), 0);

    }

    @Test
    void 전체프레임을_실행하고_점수합산한다(){
        game.playAllFrame();
        assertThat(game.getTotalScore()).isEqualTo(86);

        perfectGame.playAllFrame();
        assertThat(perfectGame.getTotalScore()).isEqualTo(300);
    }

}
