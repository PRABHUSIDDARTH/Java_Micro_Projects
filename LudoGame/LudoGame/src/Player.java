import java.util.*;

public class Player {

    private int playerId;
    private ArrayList<Integer> tokens;

    public Player(int id) {
        this.playerId = id;
        tokens = new ArrayList<>();

        // initialize 4 tokens inside home
        for (int i = 0; i < 4; i++) {
            tokens.add(-1);
        }
    }

    // entry position based on player
    private int getEntryPoint() {
        switch (playerId) {
            case 0: return 0;
            case 1: return 13;
            case 2: return 26;
            case 3: return 39;
        }
        return 0;
    }

    public void moveCoin(int tokenIndex, int diceValue) {

        int curr = tokens.get(tokenIndex);

        //  Token inside home
        if (curr == -1) {
            if (diceValue == 6) {
                tokens.set(tokenIndex, getEntryPoint());
            }
            return;
        }

        //  Token on main board
        if (curr >= 0 && curr < 52) {
            int newPos = curr + diceValue;

            // move into home path after completing round
            if (newPos >= 52) {
                newPos = 52 + (newPos - 52);
            }

            tokens.set(tokenIndex, newPos);
            return;
        }

        //  Token in home path
        if (curr >= 52 && curr <= 57) {
            int newPos = curr + diceValue;

            // exact dice needed to finish
            if (newPos <= 57) {
                tokens.set(tokenIndex, newPos);
            }
        }
    }

    // helper
    public ArrayList<Integer> getTokens() {
        return tokens;
    }

    public boolean hasWon() {
        for (int pos : tokens) {
            if (pos != 57) {
                return false;
            }
        }
        return true;
    }
    public int getMovableToken(int diceValue) {
        for (int i = 0; i < tokens.size(); i++) {
            int pos = tokens.get(i);

            // token inside home → needs 6
            if (pos == -1 && diceValue == 6) {
                return i;
            }

            // token on board
            if (pos >= 0 && pos < 52) {
                return i;
            }

            // token in home path but not finished
            if (pos >= 52 && pos < 57 && pos + diceValue <= 57) {
                return i;
            }
        }
        return -1; // no valid token
    }


}
