import java.util.*;

public class Board {

    // Safe cells where cutting is not allowed
    private int[] safeCells = {0, 8, 13, 21, 26, 34, 39, 47};

    // check if a position is safe
    private boolean isSafeCell(int pos) {
        for (int i : safeCells) {
            if (i == pos) {
                return true;
            }
        }
        return false;
    }

    // cutting logic
    public void checkAndCut(ArrayList<Player> players, int currentPlayer, int movedPos) {

        // only main board positions can be cut
        if (movedPos < 0 || movedPos >= 52) {
            return;
        }

        // no cutting on safe cells
        if (isSafeCell(movedPos)) {
            return;
        }

        // check all other players
        for (int i = 0; i < players.size(); i++) {

            // skip current player
            if (i == currentPlayer) continue;

            ArrayList<Integer> tokens = players.get(i).getTokens();

            for (int j = 0; j < tokens.size(); j++) {
                if (tokens.get(j) == movedPos) {
                    // CUT!
                    tokens.set(j, -1);
                    System.out.println("Player " + i + " token " + j + " got CUT!");
                }
            }
        }
    }

    // display current positions
    public void display(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            System.out.println("Player " + i + " : " + players.get(i).getTokens());
        }
        System.out.println("--------------------------------");
    }
}
