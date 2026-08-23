public class Main {
    public static void main(String[] args) {
        int playerCount=2;
        int diceCount=2;
        int minDiceVal=1;
        int maxDiceVal=6;
        int boardSize=5;
        int snakeCount=2;
        int ladderCount=3;

        Game game=new Game(playerCount,diceCount,minDiceVal,maxDiceVal,boardSize,snakeCount,ladderCount);
        game.initializeGame();
        game.startGame();
    }
}