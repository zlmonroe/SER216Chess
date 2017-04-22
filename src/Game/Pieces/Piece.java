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
    protected Point oldPosition;
    protected Image pieceIcon;
    protected boolean isWhite;
    protected static BoardState board;
    /* Indentifier
     *  Pawn = 0, Knight = 1, Bishop = 2, Rook = 3, Queen = 4, King = 5
     */
    protected int identifier;

    /**
     * Constructor for piece
     * @param fileName name of file to open
     * @param start starting position point
     * @param isWhite is the piece a white piece
     */
    public Piece(String fileName, Point start, boolean isWhite) {
        String fileSeparator = System.getProperty("file.separator");
        String location = "Icons" + fileSeparator;
        try {
            pieceIcon = ImageIO.read(getClass().getResourceAsStream(location + fileName));
        } catch (Exception e) {
            System.out.println("Unable to open " + location + fileName);
            System.out.println(e);
        }
        this.position = start;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Point oldPosition) {
        this.oldPosition = oldPosition;
    }

    public Image getPieceIcon() {
        return pieceIcon;
    }

    public void setPieceIcon(Image pieceIcon) {
        this.pieceIcon = pieceIcon;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public static void setBoardState(BoardState board) {
        Piece.board = board;
    }

    public int getIdentifier() {
        return this.identifier;
    }

    public boolean canMove(Point newPos) {
        return getMoves().contains(newPos);
    }

    public abstract LinkedList<Point> getMoves();
}