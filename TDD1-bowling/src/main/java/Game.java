public class Game {

    private Frames frames;
    private int totalScore;
    private RollNums rollNums;

    public Game(RollNums rollNums) {
        this.frames = new Frames();
        this.totalScore = 0;
        this.rollNums =rollNums;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public RollNums getRollNums() {
        return rollNums;
    }

    public int playOneFrame(int i){
        return frames.playOneFrame(i, rollNums.getOneRollNum(i));
    }

    public int playLastFrame(){
        return frames.playLastFrame(rollNums.getOneRollNum(10));
    }

    public void playAllFrame(){

    }
}
