

package MainFrame.ChessFrame.players.Pieces;

import java.awt.*;


public class King {

    /**
     * Creates a new instance of King
     */
    private int X, Y;
    private Point pixelPoint = new Point();
    private int pixelX, pixelY;
    private boolean havelife = true;
    private pieceIcon PieceIcon;
    private Point old = new Point();
    private Point p = new Point();

    public King(String NameIcon, int startX, int startY) {

        PieceIcon = new pieceIcon(NameIcon);

        X = startX;
        Y = startY;
        p.x = X;
        p.y = Y;
    }

    public Image returnPieceImage() {
        return PieceIcon.returnPieceIcon();
    }

    public Point returnPosition() {
        return (Point) p.clone();
    }

    public int returnX() {
        X = p.x;
        return X;
    }

    public void setPixels(int newpixelX, int newpixelY) {
        pixelPoint.x = newpixelX;
        pixelPoint.y = newpixelY;
    }

    public int getPixelX() {
        return pixelX;
    }

    public int getPixelY() {
        return pixelY;
    }

    public Point getPixelPoint() {
        return pixelPoint;
    }

    public int returnY() {
        Y = p.y;
        return Y;
    }

    public void setPoint(Point newPoint) {
        old.x = p.x;
        old.y = p.y;

        p.x = newPoint.x;

        p.y = newPoint.y;

        X = p.x;
        Y = p.y;
    }

    public void toOld(Point old) {

        p.x = old.x;
        p.y = old.y;

    }

    public Point getOld() {
        return old;
    }

    public void setX(int newX) {
        X = newX;
        p.x = X;
    }

    public void setY(int newY) {
        Y = newY;
        p.y = Y;
    }

    public boolean inThisPosition(int x, int y) {
        if (p.x == x && p.y == y)
            return true;//cant kill the King anymore;
        return false;
    }

    public boolean returnLife() {
        return havelife;
    }

    public boolean Canmove(int x, int y) {

        if (((y == Y) && (x == (X - 1))) || ((y == Y - 1) && (x == (X + 1)))
                || ((y == Y - 1) && (x == (X - 1))) || ((y == Y + 1) && (x == (X + 1)))
                || (((y == Y + 1) && x == (X - 1))) || ((y == Y) && (x == (X + 1)))
                || ((y == Y - 1) && x == ((X))) || ((y == Y + 1) && (x == (X)))) {


            return true;
        }


        return false;

    }

    public Point GeneratePossible_Moves() {
        /* Not implemented */
        return new Point();
    }

    public String Tell_me() {
        return "King= (" + p.x + ',' + p.y + ")";
    }


}
