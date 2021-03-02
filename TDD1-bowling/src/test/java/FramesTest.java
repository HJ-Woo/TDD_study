import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FramesTest {
    public Frames frames;

    public static final int EmptyResult = -1;

    @BeforeEach
    void 프레임생성(){
        frames = new Frames();
    }


    @Test
    void framesCreateTest(){
        Assertions.assertEquals(frames.getFrameList().size(), 10);
        Assertions.assertEquals(frames.getFrameList().get(0).getClass(), Frame.class);
    }


    @Test
    void i번째_프레임이_strkie혹은spare인지_확인(){
        Frame strikeFrame = new Frame();
        int strikeIndex = 5;
        Frame spareFrame = new Frame();
        int spareIndex = 7;

        strikeFrame.play(10, 0);
        spareFrame.play(3, 7);

        frames.checkSpecial(strikeFrame, strikeIndex);
        Assertions.assertEquals(frames.checkSpareQ(), EmptyResult);
        Assertions.assertEquals(frames.checkStrikeQ(), strikeIndex);

        frames = new Frames();
        frames.checkSpecial(spareFrame, spareIndex);
        Assertions.assertEquals(frames.checkSpareQ(), spareIndex);
        Assertions.assertEquals(frames.checkStrikeQ(), EmptyResult);

    }

}
