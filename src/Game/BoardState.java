package Game;

import Game.Pieces.*;

import java.awt.*;

/**
 * Created by tjcup on 4/19/2017.
 */
public class BoardState {
    private final Piece[][] board;

    private BoardState(Piece[][] board) {
        this.board = board;
    }

    public BoardState() {
        board = new Piece[8][8];
        board[0] = new Piece[]{new Rook(new Point(0,0), true), new Knight(new Point(1,0), true), new Bishop(new Point(2, 0), true), new King(new Point(3, 0), true), new Queen(new Point(4,0),true), new Bishop(new Point(5,0),true), new Knight(new Point(6, 0), true), new Rook(new Point(7, 0), true)};
        board[7] = new Piece[]{new Rook(new Point(0,7), false), new Knight(new Point(1,7), false), new Bishop(new Point(2, 7), false), new King(new Point(3, 7), false), new Queen(new Point(4,7),false), new Bishop(new Point(5,7),false), new Knight(new Point(6, 7), false), new Rook(new Point(7, 7), false)};
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(new Point(i, 1), true);
            board[6][i] = new Pawn(new Point(i, 6), false);
        }
    }

    public Piece getPieceAt(Point p){
        return board[p.x][p.y];
    }

    public BoardState move(Point moveStart, Point moveEnd){
        Piece[][] newBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            System.arraycopy(board[i], 0, newBoard[i], 0, 8);
        }
        newBoard[moveEnd.x][moveEnd.y] = newBoard[moveStart.x][moveStart.y];
        newBoard[moveStart.x][moveStart.y] = null;
        return new BoardState(board);
    }
}
