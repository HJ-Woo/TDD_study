import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FrameTest {

    //Todo 1. Frame을 초기화 할때 핀을 10개를 생성한다
    //Todo 2. Frame을 초기화 할때 점수를 0으로 초기화한다
    //Todo 3. Frame을 초기화 할때 누적점수를 0으로 초기화한다

    private Frame frame;

    @BeforeEach
    void setUp(){
        frame = new Frame();
    }


    @Test
    void frameCreatePin(){
        Assertions.assertEquals(frame.getPin(), 10);
    }

    //TODO 이후 수정본) current와 total이 아닌 각 roll 횟수별 score로 변경
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
        frame.setFirstScroe(5);
        frame.setSecondScore(10);
        Assertions.assertEquals(frame.getFirstScroe(), 5);
        Assertions.assertEquals(frame.getSecondScore(), 10);
    }

    //TODO 1. hit()을 수행하면 pin의 수(frame), 쓰러뜨리는 수(parameter)
    //TODO 2. pin = pin의 수 - parameter의 쓰러뜨린 수 갱신
    @Test
    void hitTest(){
        Assertions.assertEquals(frame.getPin(), 10);
        int hitNum = 3;
        frame.hit(hitNum);
        Assertions.assertEquals(frame.getPin(), 10-hitNum);
    }

    // TODO 1. 첫번재 roll에 pin이 남아있으면 spare함수처리
    // TODO 2. hit()을 해주어 pin==0 하면 (2-1) pin !=0 (2-2)
    // TODO 2-1. spare true시 pin의 갯수는 0개, currentScore 10, totalScore +10
    // TODO 2-2. spare false시 pin의 갯수는 pin - spare의 hit개, currentScore 10 - pin, totalScore 10 - pin
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

    // TODO 1. strike시에 strike field에 true 바뀜
    // TODO 2. pin의 갯수는 0개, currentScore 10, totalScore +10
    // TODO 3. 이후의 roll은 진행하지 않고 다음 frame 넘어간다.

    @Test
    void strikeTest(){
        Assertions.assertEquals(frame.getPin(), 10);
        Assertions.assertEquals(frame.isStrike(), false);
        frame.strike();
        Assertions.assertEquals(frame.getPin(), 0);
        Assertions.assertEquals(frame.isStrike(), true);
    }

    //Todo 1. roll()에서 hit()을 수행한다
    //Todo 2. hit()을 수행한 다음에 몇개남았는지에 따라서 함수를 실행
    //Todo 3. hit 0개면은 open 지나간다
    //Todo 4. hit 10개 미만 spare를 실행 hit()
    //Todo 5. hit 10개 strike를 실행
//    @Test
//    void rollStrikeTest(){
//        //Strike
//        int firsthit = 10;
//        int secondhit = 0;
//        frame.roll(firsthit, secondhit);
//        Assertions.assertEquals(frame.totalScore, 10);
//        Assertions.assertEquals(frame.isSpare, false);
//        Assertions.assertEquals(frame.isStrike, true);
//    }
//
//    @Test
//    void rollSpareTest(){
//        int firsthit = 4;
//        int secondhit = 6;
//        frame.roll(firsthit, secondhit);
//        Assertions.assertEquals(frame.totalScore, 10);
//        Assertions.assertEquals(frame.isSpare, true);
//        Assertions.assertEquals(frame.isStrike, false);
//    }
//
//    @Test
//    @DisplayName("spare 실패했을 경우")
//    void rollNotSpareTest(){
//        //Spare실패
//        int firsthit = 0;
//        int secondhit = 5;
//        frame.roll(firsthit, secondhit);
//        Assertions.assertEquals(frame.totalScore, firsthit+secondhit);
//        Assertions.assertEquals(frame.isSpare, false);
//        Assertions.assertEquals(frame.isStrike, false);
//    }



    //Roll의 의미를 변경
    //Frame에 rollNum 필드를 두고 roll

    @Test
    void rollStrikeTest(){
        frame.roll(1, 10);
        Assertions.assertEquals(frame.getPin(), 0);
        Assertions.assertEquals(frame.isStrike(), true);
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
        Assertions.assertEquals(frame.getPin(), 1);
        Assertions.assertEquals(frame.getFirstScroe(), 1);
        Assertions.assertEquals(frame.getSecondScore(), 8);
    }



    @Test
    void playLastSpareTest(){
        frame.playLast(1, 9, 10);
        Assertions.assertEquals(frame.getFirstScroe(), 1);
        Assertions.assertEquals(frame.isSpare(), true);
        Assertions.assertEquals(frame.getSecondScore(), 19);
    }

    @Test
    void playLastTest(){
        frame.playLast(1, 8, 1);
        Assertions.assertEquals(frame.getFirstScroe(), 1);
        Assertions.assertEquals(frame.getSecondScore(), 9);
    }
    @Test
    void playLastTwoStrikeTest(){
        frame.playLast(10, 10, 0);
        Assertions.assertEquals(frame.getFirstScroe(), 20);
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
