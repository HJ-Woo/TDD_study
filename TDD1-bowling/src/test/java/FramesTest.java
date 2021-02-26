import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FramesTest {
    public Frames frames;

    @Test
    void framesCreateTest(){
        frames = new Frames();
        Assertions.assertEquals(frames.getFrameList().size(), 10);
        Assertions.assertEquals(frames.getFrameList().get(0).getClass(), Frame.class);
    }


}
