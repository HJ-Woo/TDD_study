public class Frame {

    public int pin;
    public int currentScore;
    public int totalScore;
    public boolean isStrike;
    public boolean isSpare;

    public Frame(){
        this.pin = 10;
        this.currentScore = 0;
        this.totalScore = 0;
        this.isStrike = false;
    }

    public void setCurrentScore(int score){
        this.currentScore = score;
    }

    public void setTotalScore(int score){
        this.totalScore = score;
    }


    public void hit(int hit) {
        pin = pin-hit;
    }


    public void strike() {
        isStrike = true;
        pin = 0;
        currentScore = 10;
        totalScore += 10;
    }

    public void spare(int spareNum) {
        hit(spareNum);
        if(pin == 0){
            isSpare = true;
        }
        currentScore = 10 - pin;
    }

    public int roll(int firsthit, int secondhit) {
        hit(firsthit);
        if (pin == 0) {
            strike();
            return totalScore;
        }
        spare(secondhit);
        totalScore += 10 - pin;
        return totalScore;
    }


//       if(pin == 0) {
//           strike();
//       } else {
//           spare(secondhit);
//       }
//       totalScore += 10 - pin;
//    }
}
