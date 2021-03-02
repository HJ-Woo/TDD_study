public class GameFactory {

    public Frames frames() {
        return new Frames();
    }

    public Game game(RollNums rollNums){
        return new Game(rollNums);
    }
}
