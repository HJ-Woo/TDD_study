//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class GameTest {
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
//         Assertions.assertEquals(game.getTotalScore(), 0);
//
//    }
//
//    //
////    @Test
////    @DisplayName("Game Start() 수행")
////    void testStart() {
////        for(int i = 0; i < 10; i++){
////            Frame frame = game.frames.get(i);
////            int first = 4;
////            int second = 5;
////            int score = frame.roll(first, second);
////            Assertions.assertEquals(score, game.frames.get(i).totalScore);
////        }
////        game.start();
////    }
//
//    //game.start()의 역할은 각 프레임마다의 게임이 수행되게끔 해줌
//    //
//
//
//    @Test
//    void 열번의_프레임수행을_체크() {
//
//    }
//}
