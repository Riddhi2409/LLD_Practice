package entity;

import java.util.ArrayList;
import java.util.List;

public class Board {
    PlayingPiece [][] grid;
    public int size;

    public Board(int size) {
        this.size = size;
        grid=new PlayingPiece[size][size];
    }

    public boolean addPlayingPiece(int row,int col,PlayingPiece p){
        if(row>=size || col>=size || grid[row][col]!=null){
            return false;
        }
        grid[row][col]=p;
        return true;
    }

    public List<Cell> getFreeCells(){
        List<Cell> ans = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid[i][j]==null){
                    Cell curr=new Cell(i,j);
                    ans.add(curr);
                }
            }
        }
        return ans;
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(grid[i][j]==null){
                    System.out.print(" ");
                }
                else{
                    System.out.print(grid[i][j].pieceType.name());
                }
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-----------");
        }
    }

    public PlayingPiece getCell(int row,int col){
        if(grid[row][col]==null){
            return null;
        }
        return grid[row][col];
    }
}
