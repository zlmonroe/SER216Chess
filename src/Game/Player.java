package Game;

import Game.Pieces.Piece;

import java.awt.*;

/**
 * Created by tjcup on 4/19/2017.
 */
public class Player {
    private static BoardState cb;
    private Iterable<Piece> myPieces;
    private Player opponent;
    private boolean isWhite;

    //Constructor here

    public boolean inCheck(){
        return false;
    }

    public boolean inCheckMate(){
        return false;
    }

}
