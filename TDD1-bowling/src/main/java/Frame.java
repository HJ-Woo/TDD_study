public class Frame {

    private int pin;
    private int firstScroe;
    private int secondScore;
    private boolean isStrike;
    private boolean isSpare;

    public Frame(){
        this.pin = 10;
        this.firstScroe = 0;
        this.secondScore = 0;
        this.isStrike = false;
    }

    public int getPin() {
        return pin;
    }

    public int getFirstScroe() {
        return firstScroe;
    }

    public int getSecondScore() {
        return secondScore;
    }


    public boolean isStrike() {
        return isStrike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void addFristScore(int firstScroe) {
        this.firstScroe += firstScroe;
    }

    public void addSecondScore(int secondScore) {
        this.secondScore += secondScore;
    }


    public void hit(int hit) {
        pin = pin-hit;
    }


    public void strike() {
        this.isStrike = true;
        this.pin = 0;
        this.firstScroe += 10;
    }

    public void spare(int spareNum) {
        hit(spareNum);
        if(this.pin == 0 && !this.isStrike){
            this.isSpare = true;
        }
        this.secondScore += spareNum;
    }

    public void roll(int rollNum, int hitNum){
        if(hitNum == 10 && rollNum < 3){
            strike();
            return;
        }
        if(rollNum >= 2){
            spare(hitNum);
            return;
        }
        hit(hitNum);
        this.firstScroe += hitNum;
    }

    public int[] play(int firstHit, int secondHit){
        roll(1, firstHit);
        roll(2, secondHit);
        return new int[]{firstScroe, secondScore};
    }

    public int[] playLast(int firstHit, int secondHit, int lastHit){
        roll(1, firstHit);
        if(isStrike()) {
            this.isStrike = false;   //마지막 프레임의 isStrike는 double strike일때를 의미
            this.pin = 10;
        }
        roll(2, secondHit);
        if(isSpare()) this.pin = 10;
        roll(3, lastHit);

        return new int[]{firstScroe, secondScore};
    }

    public int getTotal(){
        return firstScroe + secondScore;
    }

}
