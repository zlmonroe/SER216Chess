package Game;

import Game.Pieces.*;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Immutable board state, including pieces
 * @author Jonathan Bush
 */
public class BoardState {
    private final Piece[][] board;
    private LinkedList<Piece> blackPieces;
    private LinkedList<Piece> whitePieces;
    /**
     * Indicates how many moves have happened since a pawn was last moved
     */
    public final int movesSincePawn;
    /**
     * Total number of moves completed thus far
     */
    public final int totalNumMoves;

    private BoardState(Piece[][] board, int movesSincePawn, int totalNumMoves) {
        this.totalNumMoves = totalNumMoves;
        this.board = board;
        this.movesSincePawn = movesSincePawn;
        initLists();
    }

    private void initLists() {
        blackPieces = new LinkedList<>();
        whitePieces = new LinkedList<>();
        for (Piece[] pieces : board) {
            for (Piece piece : pieces) {
                if (piece != null) {
                    piece.setBoardState(this);
                    if (piece.isWhite()) {
                        whitePieces.add(piece);
                    } else {
                        blackPieces.add(piece);
                    }
                }
            }
        }
    }

    /**
     * Creates a new BoardState with the initial chess setup
     */
    public BoardState() {
        totalNumMoves = 0;
        movesSincePawn = 0;
        board = new Piece[8][8];
        Piece[] whites = new Piece[]{new Rook(new Point(0,0), true), new Knight(new Point(1,0), true), new Bishop(new Point(2, 0), true), new Queen(new Point(3, 0), true), new King(new Point(4,0),true), new Bishop(new Point(5,0),true), new Knight(new Point(6, 0), true), new Rook(new Point(7, 0), true)};
        Piece[] blacks = new Piece[]{new Rook(new Point(0,7), false), new Knight(new Point(1,7), false), new Bishop(new Point(2, 7), false), new Queen(new Point(3, 7), false), new King(new Point(4,7),false), new Bishop(new Point(5,7),false), new Knight(new Point(6, 7), false), new Rook(new Point(7, 7), false)};
        for (int i = 0; i < 8; i++) {
            board[i][0] = whites[i];
            board[i][7] = blacks[i];
            board[i][1] = new Pawn(new Point(i, 1), true);
            board[i][6] = new Pawn(new Point(i, 6), false);
        }
        initLists();
    }

    /**
     * Get the piece at a particular location on in the board
     * @param p The point at which to get the piece
     * @return The piece at p, or null if none or out of bounds
     */
    public Piece getPieceAt(Point p){
        if (p.x < 0 || p.x > 7 || p.y < 0 || p.y > 7)
            return null;
        return board[p.x][p.y];
    }

    /**
     * Gets pieces of white or black player
     * @param whitePlayer true for white, false for black
     * @return the list of pieces
     */
    public LinkedList<Piece> getPieces(boolean whitePlayer) {
        LinkedList<Piece> pieces = new LinkedList<>();
        for( Piece p : whitePlayer ? whitePieces : blackPieces) {
            Piece pc = p.clone();
            pc.setBoardState(this);
            pieces.add(pc);
        }
        return pieces;
    }

    /**
     * Move the piece at moveStart to moveEnd, replacing any piece that may be at moveEnd
     * @param moveStart Starting position
     * @param moveEnd Ending position
     * @return new BoardState after the move is executed
     */
    public BoardState move(Point moveStart, Point moveEnd){
        Piece[][] newBoard = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (null != board[i][j])
                    newBoard[i][j] = board[i][j].clone();
            }
        }
        int pawnMoves;
        if (newBoard[moveStart.x][moveStart.y].identifier == 0) {
            pawnMoves = 0;
        } else {
            pawnMoves = movesSincePawn + 1;
        }
        newBoard[moveEnd.x][moveEnd.y] = newBoard[moveStart.x][moveStart.y];
        newBoard[moveEnd.x][moveEnd.y].setPosition(moveEnd);
        if (!moveStart.equals(moveEnd))
            newBoard[moveStart.x][moveStart.y] = null;
        return new BoardState(newBoard, pawnMoves, totalNumMoves + 1);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BoardState)) {
            return false;   // if other is not a BoardState
        }
        BoardState b = (BoardState) other;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    if (b.board[i][j] != null) return false;    // if there is a piece in the wrong place
                } else {
                    if (!board[i][j].equals(b.board[i][j])) return false;   // if there is a different piece
                }
            }
        }
        Set<Piece> thisWhite = new HashSet<>(whitePieces);
        Set<Piece> thisBlack = new HashSet<>(blackPieces);
        Set<Piece> otherWhite = new HashSet<>(b.whitePieces);
        Set<Piece> otherBlack = new HashSet<>(b.blackPieces);
        return thisWhite.equals(otherWhite) && thisBlack.equals(otherBlack);
    }

    @Override
    public String toString() {
        String s = "";
        for(int r = 7; r >= 0; r--) {
            for(int c = 0; c <= 7; c++) {
                Piece p = getPieceAt(new Point(c, r));
                if(p!=null) {
                    s += "[" +r + ", " + c + ", " + (p.isWhite() ? "white" : "black") + ", " + p.getIdentifier() + "] ";
                }
                else {
                    s += "[" + r + ", " + c + ",      , x] ";
                }
            }
            s += "\n";
        }
        return s;
    }
}
