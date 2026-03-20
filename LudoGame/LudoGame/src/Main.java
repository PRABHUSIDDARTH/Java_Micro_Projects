import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Dice dice = new Dice();
        TurnManager turnManager = new TurnManager();
        Board board = new Board();

        // create players
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            players.add(new Player(i));
            turnManager.addFirst(i);
        }

        boolean gameOver = false;

        while (!gameOver) {

            int currentPlayer = turnManager.turn.getFirst();
            System.out.println("\nPlayer " + currentPlayer + "'s turn");
            System.out.println("Press ENTER to roll the dice...");
            sc.nextLine(); // ENTER = roll button

            int diceValue = dice.roll();
            System.out.println("Dice rolled: " + diceValue);

            // automatically select a valid token
            int tokenIndex = players.get(currentPlayer)
                    .getMovableToken(diceValue);

            if (tokenIndex != -1) {

                players.get(currentPlayer)
                        .moveCoin(tokenIndex, diceValue);

                int newPos = players.get(currentPlayer)
                        .getTokens()
                        .get(tokenIndex);

                board.checkAndCut(players, currentPlayer, newPos);

            } else {
                System.out.println("No valid move for Player " + currentPlayer);
            }

            board.display(players);

            // check win condition
            if (players.get(currentPlayer).hasWon()) {
                System.out.println("\n🏆 PLAYER " + currentPlayer + " WINS THE GAME 🏆");
                gameOver = true;
                break;
            }

            // handle turn rotation
            if (diceValue != 6) {
                turnManager.next();
            } else {
                System.out.println("Extra turn!");
            }
        }

        System.out.println("Game Over.");
        sc.close();
    }
}
