import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrameTest {

    private Frame frame;

    @BeforeEach
    void setUp(){
        frame = new Frame();
    }


    @Test
    void frameCreatePin(){
        Assertions.assertEquals(frame.getPin(), 10);
    }

    @Test
    void frameCreateScore(){
        Assertions.assertEquals(frame.getFirstScroe(), 0);
        Assertions.assertEquals(frame.getSecondScore(), 0);
        Assertions.assertEquals(frame.getPin(), 10);
        Assertions.assertEquals(frame.isStrike(), false);
        Assertions.assertEquals(frame.isSpare(), false);

    }

    @Test
    void frameSetScore(){
        frame.addFristScore(5);
        frame.addSecondScore(10);
        Assertions.assertEquals(frame.getFirstScroe(), 5);
        Assertions.assertEquals(frame.getSecondScore(), 10);
    }

    @Test
    void hitTest(){
        Assertions.assertEquals(frame.getPin(), 10);
        int hitNum = 3;
        frame.hit(hitNum);
        Assertions.assertEquals(frame.getPin(), 10-hitNum);
    }

    @Test
    void spareTrueTest(){
        int hitNum = 3;
        frame.hit(hitNum);

        Assertions.assertEquals(frame.isSpare(), false);

        int spareNum = 10 - hitNum;
        frame.spare(spareNum);
        Assertions.assertEquals(frame.getPin(), 0);
        Assertions.assertEquals(frame.isSpare(), true);

    }

    @Test
    void spareFalseTest(){
        int hitNum = 3;
        frame.hit(hitNum);
        int spareNum = 10 - hitNum - 1;

        frame.spare(spareNum);
        Assertions.assertEquals(frame.isSpare(), false);
    }

    @Test
    void strikeTest() {
        Assertions.assertEquals(frame.getPin(), 10);
        Assertions.assertEquals(frame.isStrike(), false);
        frame.strike();
        Assertions.assertEquals(frame.getPin(), 0);
        Assertions.assertEquals(frame.isStrike(), true);
        Assertions.assertEquals(frame.isSpare(), false);

    }

    @Test
    void rollStrikeTest(){
        frame.roll(1, 10);
        frame.roll(2, 0);
        Assertions.assertEquals(frame.getPin(), 0);
        Assertions.assertEquals(frame.isStrike(), true);
        Assertions.assertEquals(frame.isSpare(), false);
        Assertions.assertEquals(frame.getFirstScroe(), 10);
    }

    @Test
    void rollSpareTest(){
        frame.roll(1, 1);
        frame.roll(2, 9);
        Assertions.assertEquals(frame.getPin(), 0);
        Assertions.assertEquals(frame.isSpare(), true);
        Assertions.assertEquals(frame.getFirstScroe(), 1);
        Assertions.assertEquals(frame.getSecondScore(), 9);

    }

    @Test
    void rollSpareFalseTest(){
        frame.roll(1, 1);
        frame.roll(2, 2);
        Assertions.assertEquals(frame.getPin(), 7);
        Assertions.assertEquals(frame.getFirstScroe(), 1);
        Assertions.assertEquals(frame.getSecondScore(), 2);
    }



    @Test
    void playLastSpareTest(){
        frame.playLast(1, 9, 10);
        Assertions.assertEquals(frame.getFirstScroe(), 11);
        Assertions.assertEquals(frame.isSpare(), true);
        Assertions.assertEquals(frame.getSecondScore(), 9);
    }

    @Test
    void playLastTest(){
        frame.playLast(1, 8, 1);
        Assertions.assertEquals(frame.getFirstScroe(), 1);
        Assertions.assertEquals(frame.getSecondScore(), 9);
    }
    @Test
    void playLastTripleStrikeTest(){
        frame.playLast(10, 10, 10);
        Assertions.assertEquals(frame.getFirstScroe(), 30);
        Assertions.assertEquals(frame.isStrike(), true);
        Assertions.assertEquals(frame.getSecondScore(), 0);
    }

    @Test
    void playLastOneStrikeTest(){
        frame.playLast(10, 9, 0);
        Assertions.assertEquals(frame.getFirstScroe(), 10);
        Assertions.assertEquals(frame.isStrike(), false);
        Assertions.assertEquals(frame.getSecondScore(), 9);
    }

}
