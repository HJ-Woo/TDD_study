//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//public class GameTest {
//
//    //Todo Game을 생성하면 프레임 10개 리스트를 생성한다
//    //Todo start()에 한 프레임씩 방문하면서 roll()을 실행한다
//    //Todo start() roll을 실행했을 떄 다음 프레임에 넘겨줘야한다
//    //Todo end()가 실행되었을 경우 게임을 종료하고 점수를 출력한다
//
//    private Game game;
//
//    @BeforeEach
//    void setUpGame() {
//        this.game = new Game();
//    }
//
//    @Test
//    @DisplayName("Game 초기화")
//    void testInitGame() {
//        Assertions.assertEquals(game.frames.size(), 10);
//        Assertions.assertEquals(game.frames.get(0).getClass(), Frame.class);
//    }
//
//    //
//    @Test
//    @DisplayName("Game Start() 수행")
//    void testStart() {
//        for(int i = 0; i < 10; i++){
//            Frame frame = game.frames.get(i);
//            int first = 4;
//            int second = 5;
//            int score = frame.roll(first, second);
//            Assertions.assertEquals(score, game.frames.get(i).totalScore);
//        }
//        game.start();
//    }
//
//    // 목요일 8시30분 부터 8시30분에 다시 만나
//    // 전에 리스트의 (-2, -1)까지 isStrike인지 (-1) isSpare인지 확인을 해서
//    // 전에 리스트가 스페어라면 자신의 currentScore를 전의 프레임의 SetTotal에 넘겨주고
//    // 자신의 Score도 update해주어야 한다
//    // 토탈값을 반환 다음 프레임이 이어받아야한다
//    // roll을 한번 실행한 다음 setTotalScore을 실행한다
//
//    @Test
//    void testCheck() {
//
//    }
//}
