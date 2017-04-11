package MainFrame.ChessFrame.players;

/**
 * Created by zachary on 4/4/2017.
 */

import MainFrame.ChessFrame.players.Pieces.*;
import java.awt.*;

public class player {

    //Create all of the pieces for the player
    private Rook leftRook;
    private Rook rightRook;
    private Knight leftKnight;
    private Knight rightKnight;
    private Queen queen;
    private Bishop leftBishop;
    private Bishop rightBishop;
    private King king;
    public final Piece[] pieces;

    private boolean isWhite;
    private boolean check;
    private player enemy;
    private Piece inHand;

    /**
     * player Constructor
     * initializes the pieces for the player and gives them their image
     */
    public player(player enemy, boolean isWhite) {
        this.enemy = enemy;
        this.isWhite = isWhite;
        Piece inHand = null;
        check = false;

        //create pieces array
        pieces = new Piece[16];

        //fill array with newly initialized pieces
        char player = isWhite ? 'w':'b';
        for(int i=0;i<=7;i++) pieces[i] = new Pawn(player+"p.gif", i + 1, 7);
        pieces[8] = leftRook = new Rook(player+"r.gif",8,8);
        pieces[9] = rightRook = new Rook(player+"r.gif",1,8);
        pieces[10] = leftKnight = new Knight(player+"n.gif",2,8);
        pieces[11] = rightKnight = new Knight(player+"n.gif",7,8);
        pieces[12] = leftBishop  = new Bishop(player+"b.gif",3,8);
        pieces[13] = rightBishop = new Bishop(player+"b.gif",6,8);
        pieces[14] = queen       = new Queen(player+"q.gif",4,8);
        pieces[15] = king        = new King(player+"k.gif",5,8);
    }

    public Piece getInHand() {
        return inHand;
    }

    public void setInHand(Piece inHand) {
        this.inHand = inHand;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean checkMateGameOver() {
        for (Piece piece : this.pieces) {
            if (piece.hasMoves(isWhite)) {
                return false;
            }
        }
        return true;

    }
    public boolean kingInCheck() {
        for (Piece enemyPiece : enemy.pieces) {
            if (enemyPiece.canMove(king.returnX(),king.returnY(),isWhite ? "white":"black")) {
                return true;
            }
        }
        return false;
    }


    public boolean killProtectKing(Point movingTo) {
        Point kingPos = king.returnPosition();

        for(Piece enemyPiece : enemy.pieces) {
            Point other = enemyPiece.returnPosition();

            if(other.x==movingTo.x&&other.y==movingTo.y) {
                if (enemyPiece.canMove(kingPos.x, kingPos.y, isWhite ? "black":"white"))
                return true;
            }
        }
        return false;
    }

    public boolean pieceAlreadyThere(Point newP) {
        Point samePosition;
        for (Piece piece : this.pieces) {
            //Check if there is another one of my pieces in the new Point
            //If so we Can't move (Same Color)!!
            samePosition = piece.returnPosition();
            if (newP.x == samePosition.x && newP.y == samePosition.y) {
                return true;
            }

        }
        return false;
    }
}