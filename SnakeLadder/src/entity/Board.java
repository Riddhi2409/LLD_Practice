package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
    int size;
    Cell [][] grid;

    public Board(int size) {
        this.size = size;
        grid = new Cell[size][size];
        int pos=1;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                Cell cell=new Cell(0);
                grid[i][j]=cell;
                pos++;
            }
        }
    }

    public Cell getCell(int pos){
        int row=(pos-1)/size;
        int col=(pos-1)%size;
        return grid[row][col];
    }

    public void addSnakesLadders(int snakeCount, int ladderCount){
        List<Integer> available=new ArrayList<>();
        int totalCells=size*size;
        for(int i=2;i<totalCells;i++){
            available.add(i);
        }
        while(snakeCount>0){
            int n=available.size();

            int head= available.get(ThreadLocalRandom.current().nextInt(5,n));
            int tail=available.get(ThreadLocalRandom.current().nextInt(0,head));
            Jump snake=new Jump(head,tail);

            Cell cell = getCell(head);
            cell.setJump(snake);
            available.remove(Integer.valueOf(head));
            available.remove(Integer.valueOf(tail));
            snakeCount--;
        }

        while(ladderCount>0){
            int n=available.size();

            int head= available.get(ThreadLocalRandom.current().nextInt(0,n/2));
            int tail=available.get(ThreadLocalRandom.current().nextInt(head+1,n));
            Jump snake=new Jump(head,tail);

            Cell cell = getCell(head);
            cell.setJump(snake);
            available.remove(Integer.valueOf(head));
            available.remove(Integer.valueOf(tail));
            ladderCount--;
        }
    }
}
