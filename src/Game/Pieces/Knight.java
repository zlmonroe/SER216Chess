

package Game.Pieces;

import Game.BoardState;
import java.awt.*;
import java.util.LinkedList;

public class Knight extends Piece {

    public Knight(Point start, boolean isWhite, BoardState board) {
        super(start, isWhite, board);
        this.identifier = 1;
    }

    @Override
    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        int[][] possibleMoves = {{-2,-1}, {-2,1}, {-1,-2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

        for(int[] move : possibleMoves) {
            Point possibleMove = new Point(move[0], move[1]);
            Piece pieceAtMove = board.getPieceAt(possibleMove);
            if (pieceAtMove == null || pieceAtMove.isWhite != isWhite) {
                moves.add(possibleMove);
            }
        }

        return moves;
    }
}