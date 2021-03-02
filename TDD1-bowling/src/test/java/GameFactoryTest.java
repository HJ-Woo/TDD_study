import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GameFactoryTest {
    //게임 팩토리는
    //frames를 생성한다
    //생성한 frames를 토대로 각 맞추는 수(rollNum)을 가지는 game을 생성한다.
    GameFactory gameFactory;

    @BeforeEach
    void 게임팩토리(){
        gameFactory = new GameFactory();
    }

    @Test
    void 게임팩토리_프레임생성(){
        Assertions.assertEquals(gameFactory.frames().getClass(), Frames.class);
    }


    //RollNum을 Frames가 아닌 Game의 필드로 변경
    @Test
    void 게임팩토리_게임생성(){
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

        Assertions.assertEquals(gameFactory.game(rollNums).getRollNums(), rollNums);
    }
}
