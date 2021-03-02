import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> frameList;
    private RollNums rollNums;

    public Frames() {
        this.frameList = frameList;
        this.frameList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            frameList.add(new Frame());
        }
    }

    public Frames(RollNums rollNums) {
        this.frameList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            frameList.add(new Frame());
        }
        this.rollNums = rollNums;
    }



    public List<Frame> getFrameList() {
        return frameList;
    }

    public RollNums getRollNums() {
        return rollNums;
    }
}
