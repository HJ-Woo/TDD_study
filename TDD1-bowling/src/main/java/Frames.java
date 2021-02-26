import java.util.ArrayList;
import java.util.List;

public class Frames {
    private List<Frame> frameList;

    public Frames() {
        this.frameList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            frameList.add(new Frame());
        }
    }

    public List<Frame> getFrameList() {
        return frameList;
    }
}
