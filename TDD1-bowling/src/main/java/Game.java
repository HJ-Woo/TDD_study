public class Game {

    private Frames frames;
    private int totalScore;
    private RollNums rollNums;

    private static final int PerfectScore = 300;

    public Game(RollNums rollNums) {
        this.frames = new Frames();
        this.totalScore = 0;
        this.rollNums = rollNums;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public RollNums getRollNums() {
        return rollNums;
    }

    public void sumAllFrame(){
        totalScore = frames.sumAllFrame();
        if(frames.getStrikeNum() == Frames.FrameNum) totalScore = PerfectScore;
    }

    public void playAllFrame(){
        int index = 0;
        for(int[] rollNum: rollNums.getRollNums()){
            frames.playFrame(index, rollNum);
            index++;
        }
        sumAllFrame();
    }
}
