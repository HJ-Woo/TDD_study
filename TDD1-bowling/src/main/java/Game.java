public class Game {

    private Frames frames;
    private int totalScore;
    private RollNums rollNums;

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
    }

    public void playAllFrame(){

    }
}
