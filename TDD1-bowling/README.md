### Bowling-Game 구현 (2021.02.20)
[참고자료](https://kata-log.rocks/bowling-game-kata) [Git](https://github.com/msbaek/bowling-game)

- 요구사항
  - 볼링 게임은 10개의 프레임으로 구성된다.
  - 각 프레임은 대개 2 롤을 갖는다(10개의 핀을 쓰러 뜨리기 위해 2번의 기회를 갖는다).
  - Spare: 10 + next first roll에서 쓰러 뜨린 핀수.
  - Strike: 10 + next two rolls에서 쓰러 뜨린 핀수.
  - 10th 프레임은 특별. spare 처리하면 3번 던질 수 있음.

- 지켜야할 사항
  - depth 1 이하
  - else 문 사용 금지
  - 메소드 길이 최대한 짧게
  - 원시값 포장
  - 일급 콜렉션 사용
