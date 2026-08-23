package service;

import entity.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    Deque<Player> players;
    Board board;
    Player winner;

    public TicTacToeGame(int size) {
        this.board = new Board(size);

        PlayingPiece cross=new PlayingPieceX();
        Player player1=new Player("Player1",cross);

        PlayingPiece noughts= new PlayingPieceO();
        Player player2=new Player("Player2",noughts);

        players=new LinkedList<>();

        players.add(player1);
        players.add(player2);
    }

    public void startGame(){
        boolean nowinner=true;
        Scanner sc=new Scanner(System.in);
        while(nowinner){
            Player player=players.removeFirst();

            board.printBoard();
            List<Cell> freeSpace=board.getFreeCells();
            if(freeSpace.isEmpty()){
                nowinner = false;
                System.out.println("Games Drwa");
                board.printBoard();
                continue;
            }

            System.out.print("Player: " + player.getName() + " - Please enter [row, column]: ");
            String s= sc.nextLine();
            String [] values=s.split(",");
            int row=Integer.valueOf(values[0]);
            int col=Integer.valueOf(values[1]);

            boolean valid=board.addPlayingPiece(row,col,player.getPlayingPiece());
            if(!valid) {
                System.out.println("Please enter valid row & col");
                players.addFirst(player); // Add the player back to the queue(in the front)
                continue;
            }
            players.addLast(player);

            nowinner=!WinnerCheck.checkWinner(row,col,player.getPlayingPiece(),board);
            if(!nowinner){
                winner=player;
                System.out.println(player.getName() + " is the winner ");
                board.printBoard();
            }

        }

    }


}
