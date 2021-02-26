import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames;
    private int totalScore;

    public Game() {

        this.totalScore = 0;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
