package Game.Pieces;

import java.awt.*;
import java.util.LinkedList;

public class Bishop extends Piece {

    public Bishop(Point start, boolean isWhite) {
        super(start, isWhite);
        this.identifier = 2;
    }

    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        int originX = this.position.x;
        int originY = this.position.y;

        for(int x = originX, y = originY; x < 8 && y < 8; x++, y++) {
           Point move = new Point(x,y);
           Piece pieceAtMove = board.getPieceAt(move);
           if(pieceAtMove == null || pieceAtMove.isWhite != isWhite ){
               moves.add(move);
            }

        }

        return moves;
    }
}
