import java.util.*;
public class TurnManager {
    LinkedList<Integer> turn = new LinkedList<>();

    public void addFirst(int a){
        turn.addFirst(a);
    }
    public void next (){
        int prev = turn.removeFirst();
        turn.addLast(prev);
    }
}
