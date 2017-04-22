

package Game.Pieces;

import java.awt.*;
import java.util.LinkedList;

public class Pawn extends Piece{

    private boolean movedBefore;
    private boolean jumpedTwoLast;

    public Pawn(Point start, boolean isWhite) {
        super(start, isWhite,0);
        this.movedBefore = false;
        this.jumpedTwoLast = false;
    }

    @Override
    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        int direction = isWhite ? 1:-1;

        //left attack
        if (this.position.x > 0) {
            Point leftAttack = new Point(this.position.x - 1, this.position.y + direction);
            if(board.getPieceAt(leftAttack).isWhite()) moves.add(leftAttack);
        }
        //right attack
        if (this.position.x < 7) {
            Point rightAttack = new Point(this.position.x + 1, this.position.y + direction);
            if(board.getPieceAt(rightAttack).isWhite()) moves.add(rightAttack);
        }
        //forward
        Point oneForward = new Point(this.position.x, this.position.y + direction);
        if (!pieceInMyWay(oneForward)) {
            moves.add(oneForward);
        }
        //two forward
        if (!movedBefore) {
            Point twoForward = new Point(this.position.x, this.position.y + direction * 2);
            if (!pieceInMyWay(twoForward)) {
                moves.add(twoForward);
            }
        }
        return moves;
    }

    private boolean pieceInMyWay(Point newPoint) {
        int direction = isWhite ? 1:-1;

        if(newPoint.x == this.position.x) {
            if(newPoint.y == (this.position.y + direction)) {
                return board.getPieceAt(newPoint) == null;
            }
            if(newPoint.y == (this.position.y + 2 * direction)) {
                return board.getPieceAt(newPoint) != null &&
                        board.getPieceAt(new Point(newPoint.x, newPoint.y - direction)) != null;
            }
        }
        return false;
    }

    @Override
    public Piece clone() {
        return new Pawn(new Point(this.getPosition()), this.isWhite);
    }

}
