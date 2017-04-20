package Game;

import Game.Pieces.Piece;

import java.awt.*;

/**
 * Created by tjcup on 4/19/2017.
 */
public class BoardState {
    Piece[][] board;

    BoardState(){
        board = new Piece[8][8];
    }

    public Piece getPieceAt(Point p){
        return null;
    }
    public BoardState move(Point moveStart, Point moveEnd){
        return null;
    }
}
