
package Game.Pieces;
import java.awt.*;
import java.util.LinkedList;

public class Queen extends Piece {
    private Bishop bishop;
    private Rook rook;

    public Queen(Point start, boolean isWhite) {
        super(start, isWhite, 4);
        bishop = new Bishop(start, isWhite);
        bishop.setBoardState(this.board);
        rook = new Rook(start, isWhite);
        rook.setBoardState(this.board);
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