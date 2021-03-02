import java.util.ArrayList;

public class RollNums {
    private ArrayList<int[]> rollNums;

    public RollNums(ArrayList<int[]> rollnum) {
        this.rollNums = rollnum;
    }

    public ArrayList<int[]> getRollNums() {
        return rollNums;
    }

    public int[] getOneRollNum(int index){
        return rollNums.get(index-1);
    }
}
