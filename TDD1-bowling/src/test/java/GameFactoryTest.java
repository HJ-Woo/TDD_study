import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

class FrameFactoryTest {
    //프레임 팩토리는 각 프레임별 던질 볼의 갯수를 지정해서 frames를 생성한다.
    @Test
    void 프레임팩토리생성(){
        FrameFactory frameFactory = new FrameFactory();
        LinkedList<int[]> rollnum = new LinkedList<>();
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

        Assertions.assertEquals(frameFactory.frames(rollNums).getRollNums(), rollNums);
    }
}
