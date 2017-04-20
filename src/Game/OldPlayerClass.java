package Game;

import Game.Pieces.*;
import java.awt.*;

/**
 * Created by zachary on 4/4/2017.
 */
public class OldPlayerClass {

    //Create all of the pieces for the OldPlayerClass
    public Rook leftRook;
    public Rook rightRook;
    public Knight leftKnight;
    public Knight rightKnight;
    public Queen queen;
    public Bishop leftBishop;
    public Bishop rightBishop;
    public King king;
    public final Piece[] pieces;

    private boolean isWhite;
    private boolean check;
    private OldPlayerClass enemy;
    private Piece inHand;

    /**
     * OldPlayerClass Constructor
     * initializes the pieces for the OldPlayerClass and gives them their image
     */
    public OldPlayerClass(OldPlayerClass enemy, boolean isWhite) {
        this.enemy = enemy;
        this.isWhite = isWhite;
        Piece inHand = null;
        check = false;

        //create pieces array
        pieces = new Piece[16];

        //fill array with newly initialized pieces
        char player = isWhite ? 'w':'b';
        for(int i=0;i<=7;i++) pieces[i] = new Pawn(player+"p.gif", i, isWhite ? 6:1);
        int backRow = isWhite ? 7:0;
        pieces[8] = leftRook = new Rook(player+"r.gif",7,backRow);
        pieces[9] = rightRook = new Rook(player+"r.gif",0,backRow);
        pieces[10] = leftKnight = new Knight(player+"n.gif",1,backRow);
        pieces[11] = rightKnight = new Knight(player+"n.gif",6,backRow);
        pieces[12] = leftBishop  = new Bishop(player+"b.gif",2,backRow);
        pieces[13] = rightBishop = new Bishop(player+"b.gif",5,backRow);
        pieces[14] = queen       = new Queen(player+"q.gif",3,backRow);
        pieces[15] = king        = new King(player+"k.gif",4,backRow);
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