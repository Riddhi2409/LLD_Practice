import service.TicTacToeGame;

public class Main {
    public static void main(String[] args) {

        int size=3;

        System.out.println("\n===>>> TicTacToe Game\n");
        TicTacToeGame game = new TicTacToeGame(size);
        game.startGame();

    }
}