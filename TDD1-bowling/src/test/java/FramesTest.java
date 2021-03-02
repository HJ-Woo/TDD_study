import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FramesTest {
    public Frames frames;


    private Frame strikeFrame;
    private Frame spareFrame;

    private static final int strikeIndex = 5;
    private static final int spareIndex = 7;
    private static final int EmptyResult = -1;

    @BeforeEach
    void 프레임생성(){
        frames = new Frames();

        strikeFrame = new Frame();
        spareFrame = new Frame();
        strikeFrame.play(10, 0);
        spareFrame.play(3, 7);
    }


    @Test
    void framesCreateTest(){
        Assertions.assertEquals(frames.getFrameList().size(), 10);
        Assertions.assertEquals(frames.getFrameList().get(0).getClass(), Frame.class);
    }


    @Test
    void i번째_프레임이_strike혹은spare인지_확인(){
        frames.checkSpecial(strikeFrame, strikeIndex);
        Assertions.assertEquals(frames.checkSpareQ(), EmptyResult);
        Assertions.assertEquals(frames.checkStrikeQ(), strikeIndex);

        frames = new Frames();
        frames.checkSpecial(spareFrame, spareIndex);
        Assertions.assertEquals(frames.checkSpareQ(), spareIndex);
        Assertions.assertEquals(frames.checkStrikeQ(), EmptyResult);

    }

    @Test
    void strikeQ에서꺼내서_이전점수에_합산(){ //테스트용 접근방법
        frames.getFrameList().set(strikeIndex, strikeFrame);
        frames.checkSpecial(strikeFrame, strikeIndex);
        frames.addStrikeScore(4);
        frames.addStrikeScore(6);
        Assertions.assertEquals(frames.getFrameList().get(strikeIndex).getFirstScroe(), 20);
    }


    @Test
    void spareQ에서꺼내서_이전점수에_합산(){ //테스트용 접근방법
        frames.getFrameList().set(spareIndex, spareFrame);
        frames.checkSpecial(spareFrame, spareIndex);
        frames.addSpareScore(4);
        Assertions.assertEquals(frames.getFrameList().get(spareIndex).getSecondScore(), 11);
    }

    @Test
    void 하나의_프레임을_play한다(){
        frames.playFrame(strikeIndex, new int[]{10, 0});
        frames.playFrame(strikeIndex+1, new int[]{3, 7}); //spare
        frames.playFrame(strikeIndex+2, new int[]{10, 0});
        Assertions.assertEquals(frames.getFrameList().get(strikeIndex).getFirstScroe(), 20);
        Assertions.assertEquals(frames.getFrameList().get(strikeIndex+1).getSecondScore(), 17);
    }

}
