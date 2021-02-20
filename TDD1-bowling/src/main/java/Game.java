import java.util.ArrayList;
import java.util.List;

public class Game {

    public List<Frame> frames;

    public Game() {
        this.frames = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            frames.add(new Frame());
        }
    }
}
