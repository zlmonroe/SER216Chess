
package Game.Pieces;
import Game.BoardState;

import java.awt.Point;
import java.util.LinkedList;

public class Queen extends Piece {
    private Bishop bishop;
    private Rook rook;

    public Queen(Point start, boolean isWhite) {
        super(start, isWhite);
        bishop = new Bishop(start, isWhite);
        rook = new Rook(start, isWhite);

        this.identifier = 4;
    }

    @Override
    public LinkedList<Point> getMoves() {
        LinkedList<Point> movesB = bishop.getMoves();
        LinkedList<Point> movesR = rook.getMoves();
        movesB.addAll(movesR);
        return movesB;
    }

    @Override
    public Piece clone() {
        return new Queen(new Point(this.getPosition()), this.isWhite);
    }
}