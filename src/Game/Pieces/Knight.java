

package Game.Pieces;

import java.awt.*;
import java.util.LinkedList;

public class Knight extends Piece {

    public Knight(Point start, boolean isWhite) {
        super(start, isWhite, 1);
    }

    @Override
    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        int[][] possibleMoves = {{-2,-1}, {-2,1}, {-1,-2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for(int[] move : possibleMoves) {
            Point possibleMove = new Point(this.position.x + move[0], this.position.y + move[1]);
            if(possibleMove.x < 7 && possibleMove.x >= 0 && possibleMove.y < 7 && possibleMove.y >= 0) {
                Piece pieceAtMove = board.getPieceAt(possibleMove);
                if (pieceAtMove == null || pieceAtMove.isWhite != isWhite) {
                    moves.add(possibleMove);
                }
            }
        }

        return moves;
    }

    @Override
    public Piece clone() {
        return new Knight(new Point(this.getPosition()), this.isWhite);
    }
}