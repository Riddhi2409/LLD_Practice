package service;

import entity.Board;
import entity.PlayingPiece;

public class WinnerCheck {

    static boolean checkWinner(int row, int col, PlayingPiece p, Board board){
        boolean rowCheck=true;
        for(int i=0;i<board.size;i++){
            if(board.getCell(row,i)!=p) {
                rowCheck = false;
                break;
            }
        }

        boolean colCheck=true;
        for(int i=0;i<board.size;i++){
            if(board.getCell(i,col)!=p) {
                colCheck = false;
                break;
            }
        }

        boolean diagCheck=true;
        for(int i=0,j=0;i<board.size;i++,j++){
            if(board.getCell(i,j)!=p){
                diagCheck=false;
                break;
            }
        }

        boolean antiDiagCheck=true;
        for(int i=0,j=board.size-1;i<board.size;i++,j--){
            if(board.getCell(i,j)!=p){
                antiDiagCheck=false;
                break;
            }
        }

        return rowCheck || colCheck || diagCheck || antiDiagCheck;



    }
}
