import entity.*;

import java.util.Deque;
import java.util.LinkedList;

public class Game {
    Board board;
    Deque<Player> players;
    Dice dice;
    int playerCount;
    int diceCount;
    int minDiceVal;
    int maxDiceVal;
    int boardSize;
    int snakeCount;
    int ladderCount;

    public Game(int playerCount, int diceCount, int minDiceVal, int maxDiceVal, int boardSize, int snakeCount, int ladderCount) {
        this.playerCount = playerCount;
        this.diceCount = diceCount;
        this.minDiceVal = minDiceVal;
        this.maxDiceVal = maxDiceVal;
        this.boardSize = boardSize;
        this.ladderCount = ladderCount;
        this.snakeCount = snakeCount;
    }

    public void initializeGame(){
        board=new Board(boardSize);
        dice=new Dice(diceCount,minDiceVal,maxDiceVal);
        players=new LinkedList<>();
        for(int i=1;i<=playerCount;i++){
            Player p1=new Player("player"+i);
            players.add(p1);
        }
        board.addSnakesLadders(snakeCount,ladderCount);
    }

    public int checkJump(int pos){

        while (true){
            Cell cell= board.getCell(pos);

            if(cell.getJump()==null){
                break;
            }

            Jump jump = cell.getJump();

            String jumpType = (jump.start < jump.end) ? "Ladder" : "Snake";

            System.out.println("[+] Jump done by: " + jumpType +
                    " from " + jump.start + " to " + jump.end);

            pos = jump.end;

        }

        return pos;
    }

    private Player findPlayerTurn(){
        Player p1=players.removeFirst();
        players.addLast(p1);
        return p1;
    }

    public void startGame(){
        boolean nowinner=true;
        while(nowinner){
            Player p1=findPlayerTurn();
            System.out.println("Player turn:" + p1.getName() + " current position is: " + p1.getCurrPos());
            int diceNumbers = dice.rollDice();

            int tentativePosition = p1.getCurrPos() + diceNumbers;

            if(tentativePosition> boardSize*boardSize){
                System.out.println("Move skipped due to overshoot.");
                continue;
            }

            int nextPos= checkJump(tentativePosition);
            p1.setCurrPos(nextPos);
            System.out.println(p1.getName() + " next position "+ p1.getCurrPos());

            if(nextPos==boardSize*boardSize){
                System.out.println(p1.getName() + " is the winner");
                nowinner=false;
            }

        }
    }
}
