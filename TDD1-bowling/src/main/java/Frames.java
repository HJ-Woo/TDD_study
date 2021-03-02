import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Frames {
    private List<Frame> frameList;
    private Queue<Integer> strkieQ;
    private Queue<Integer> spareQ;

    public Frames() {
        this.frameList = frameList;
        this.frameList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            frameList.add(new Frame());
        }
        strkieQ = new LinkedList<>();
        spareQ = new LinkedList<>();

    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public int playOneFrame(int i, int[] rollNums){
        Frame frame = frameList.get(i-1);
        frame.play(rollNums[0], rollNums[1]);

        return 0;
    }

    public int playLastFrame(int[] rollNums) {
        Frame frame = frameList.get(9);
        frame.playLast(rollNums[0], rollNums[1], rollNums[2]);
        return 0;
    }

    public void checkSpecial(Frame frame, int index){
        if(frame.isStrike()) strkieQ.add(index);
        if(frame.isSpare()) spareQ.add(index);
    }

    public int checkStrikeQ(){
        return strkieQ.isEmpty() ? -1 : strkieQ.peek();
    }

    public int checkSpareQ(){
        return spareQ.isEmpty() ? -1 : spareQ.peek();
    }
}
