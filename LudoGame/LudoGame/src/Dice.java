import java.util.*;

public class Dice {
    Random dice = new Random();
    public int roll(){
        int val = dice.nextInt(6) + 1;
        return val;
    }
}
