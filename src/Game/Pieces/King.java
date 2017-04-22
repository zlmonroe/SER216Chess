package Game.Pieces;

import java.awt.*;
import java.util.LinkedList;

public class King extends Piece {

    public King(String nameIcon, Point start, boolean isWhite) {
        super(nameIcon, start, isWhite);
        this.identifier = 5;
    }

    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        for(int xOffset = -1; xOffset <= 1; xOffset++) {
            for(int yOffset = -1; yOffset <= 1; yOffset++) {
                if(xOffset != 0 && yOffset != 0) {
                    Point currentMove = new Point(this.position.x + xOffset, this.position.y + yOffset);
                    Piece pieceAtMove = board.getPieceAt(currentMove);

                    if(pieceAtMove != null && pieceAtMove.isWhite() != this.isWhite) {
                        moves.add(currentMove);
                    }
                }
            }
        }
        return moves;
    }
}