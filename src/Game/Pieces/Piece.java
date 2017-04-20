package Game.Pieces;

import javax.imageio.ImageIO;
import java.awt.*;


/**
 * Created by zachary on 3/23/2017.
 */
public abstract class Piece {
    protected int X, Y;
    protected Image pieceIcon;
    protected Point p = new Point();
    protected Point old = new Point();

    /**
     *  Constructor for piece
     *  @param fileName the Icon image name for this specific piece
     *  @param startX the beginning location for the piece (x)
     *  @param startY the beginning location for the piece (y)
     */
    public Piece(String fileName, int startX,int startY) {
        String fileSeparator = System.getProperty("file.separator");
        String location = "Icons" + fileSeparator;
        try {
            pieceIcon = ImageIO.read(getClass().getResourceAsStream(location + fileName));
        } catch (Exception e) {
            System.out.println("Unable to open " + location + fileName);
            System.out.println(e);
        }
        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
    }

    /**
     * getter for image of piece
     * @return pieceIcon piece's image
     */
    public Image returnPieceImage() {
        return pieceIcon;
    }

    /**
     * Set x position
     */
    public int  returnX() {
        X=p.x;
        return X;
    }

    /**


    /**
     * return the chessboard Y
     * @return Y
     */
    public int  returnY() {
        Y=p.y;
        return Y;
    }

    /**
     * set the old point
     * @param Old
     */
    public void setOld(Point Old) {
        p.x=Old.x;
        p.y=Old.y;
    }

    /**
     * set the new point
     * @param newPoint
     */
    public void setPoint(Point newPoint) {
        old.x=p.x;
        old.y=p.y;
        X=p.x=newPoint.x;
        Y=p.y=newPoint.y;
    }

    /**
     * set just the x of new point
     * @param newX
     */
    public void setX(int newX) {
        X=newX;
        p.x=newX;
    }

    /**
     * set just the y of new point
     * @param newY
     */
    public void setY(int newY) {
        Y=newY;
        p.y=newY;
    }

    public Point returnOld() {
        return old;
    }
    public Point returnPosition() {return (Point)p.clone();}
    /*public boolean returnLife() {
        return haveLife;
    }*/

    public boolean inThisPosition(int x, int y) {
        return p.x == x && p.y == y;
    }

    public boolean hasMoves(boolean isWhite) {
        for (int x = 0, y = 0;  x< 8 && y<8; x++, y += x/8) {
            if (this.canMove(x,y,isWhite ? "white":"black")) {
                return true;
            }
        }
        return false;
    }

    public abstract String Tell_me();
    public abstract boolean canMove(int x, int y, String s);
}
