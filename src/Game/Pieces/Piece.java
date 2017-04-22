package Game.Pieces;

import Game.BoardState;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.LinkedList;

/**
 * Piece class which represents a generic piece
 */
public abstract class Piece {
    protected Point position;
    protected boolean isWhite;
    protected BoardState board;
    /* Indentifier
     *  Pawn = 0, Knight = 1, Bishop = 2, Rook = 3, Queen = 4, King = 5
     */
    protected int identifier;

    /**
     * Constructor for piece
     * @param start starting position point
     * @param isWhite is the piece a white piece
     */
    public Piece(Point start, boolean isWhite, BoardState board) {
        this.position = start;
        this.board = board;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setBoardState(BoardState board) {this.board = board;}

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean canMove(Point newPos) {
        return getMoves().contains(newPos);
    }

    public abstract LinkedList<Point> getMoves();
}