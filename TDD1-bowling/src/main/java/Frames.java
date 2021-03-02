import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Frames {
    private List<Frame> frameList;
    private Queue<Integer> strikeQ;
    private Queue<Integer> spareQ;
    private int strikeNum;


    private static final int EmptyResult = -1;
    private static final int FrameNum = 10;

    public Frames() {
        this.frameList = new ArrayList<>();
        for(int i = 0; i < FrameNum; i++){
            frameList.add(new Frame());
        }
        strikeQ = new LinkedList<>();
        spareQ = new LinkedList<>();
        strikeNum = 0;
    }

    public List<Frame> getFrameList() {
        return frameList;
    }

    public int getStrikeNum() {
        return strikeNum;
    }

    public int[] playOneFrame(Frame frame, int[] rollNum){
        return frame.play(rollNum[0], rollNum[1]);
    }

    public int[] playLastFrame(Frame frame, int[] rollNum) {
        return frame.playLast(rollNum[0], rollNum[1], rollNum[2]);
    }

    public void playFrame(int index, int[] rollNum){
        Frame frame = frameList.get(index);
        int[] scores = index != 9? playOneFrame(frame, rollNum): playLastFrame(frame, rollNum);
        for (int score: scores){
            if(score == 0) continue;
            setScoreSpecial(score);
        }
        checkSpecial(frame, FrameNum - 1);
        frameList.set(FrameNum - 1, frame);
    }


    public void checkSpecial(Frame frame, int index){
        if(frame.isStrike()) {
            strikeQ.add(index);
            strikeQ.add(index);
            strikeNum++;
        }
        if(frame.isSpare()) {
            spareQ.add(index);
        }
    }

    public int checkStrikeQ(){
        return strikeQ.isEmpty() ? EmptyResult : strikeQ.peek();
    }

    public int checkSpareQ(){
        return spareQ.isEmpty() ? EmptyResult : spareQ.peek();
    }

    public void addStrikeScore(int score){
        int index = strikeQ.poll();
        Frame frame = frameList.get(index);
        frame.addFristScore(score);
    }

    public void addSpareScore(int score){
        int index = spareQ.poll();
        Frame frame = frameList.get(index);
        frame.addSecondScore(score);
    }

    public void setScoreSpecial(int score){
        if(checkStrikeQ() > EmptyResult) addStrikeScore(score);
        if(checkSpareQ() > EmptyResult) addSpareScore(score);
    }

    public int sumAllFrame(){
        int sum = 0;
        for(Frame frame : frameList){
            sum += frame.getTotal();
        }
        return sum;
    }
}
