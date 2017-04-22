package Game.Pieces;

import Game.BoardState;

import java.awt.*;
import java.util.LinkedList;

public class Rook extends Piece {

    public Rook(Point start, boolean isWhite) {
        super(start, isWhite);
        this.identifier = 3;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        int originX = this.position.x;
        int originY = this.position.y;

        int[][] offsets = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        for(int[] offset : offsets) {
            for (int x = originX, y = originY; x < 8 && y < 8; x += offset[0], y += offset[1]) {
                Point move = new Point(x, y);
                Piece pieceAtMove = board.getPieceAt(move);
                if (pieceAtMove == null || pieceAtMove.isWhite != isWhite) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

    @Override
    public Piece clone() {
        return new Rook(new Point(this.getPosition()), this.isWhite);
    }
}
