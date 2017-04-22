package Game.Pieces;

import Game.BoardState;

import java.awt.*;
import java.util.LinkedList;

public class Bishop extends Piece {

    public Bishop(Point start, boolean isWhite, BoardState board) {
        super(start, isWhite, board);
        this.identifier = 2;
    }

    @Override
    @SuppressWarnings("Duplicates")
    public LinkedList<Point> getMoves() {
        LinkedList<Point> moves = new LinkedList<>();

        int originX = this.position.x;
        int originY = this.position.y;

        int[][] offsets = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

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
}
