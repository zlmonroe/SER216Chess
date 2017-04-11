package MainFrame.ChessFrame.players.Pieces;

import java.awt.*;
import java.awt.Image;


/**
 * Created by zachary on 3/23/2017.
 */
public abstract class Piece {
    protected int X, Y;
    protected int pixelX, pixelY;
    protected Point pixelPoint = new Point();
    protected boolean haveLife = true;
    protected Image pieceIcon;
    protected Point p = new Point();
    protected Point old = new Point();

    public Piece(String fileName, int startX,int startY) {
        String fileSeparator = System.getProperty("file.separator");
        String location = "src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator;

        pieceIcon = Toolkit.getDefaultToolkit().getImage(location+fileName);

        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
    }

    public Image returnPieceImage() {
        return pieceIcon;
    }
    public int  returnX() {
        X=p.x;
        return X;
    }
    public void setPixels(int newPixelX,int newPixelY) {
        pixelPoint.x=newPixelX;
        pixelPoint.y=newPixelY;
    }
    public int getPixelX() {
        return pixelX;
    }
    public int getPixelY() {
        return pixelY;
    }
    public Point getPixelPoint() {
        return  pixelPoint;
    }


    public int  returnY() {
        Y=p.y;
        return Y;
    }
    public void toOld(Point Old) {

        p.x=Old.x;
        p.y=Old.y;

    }
    public void setPoint(Point newPoint) {
        old.x=p.x;
        old.y=p.y;
        X=p.x=newPoint.x;
        Y=p.y=newPoint.y;
    }
    public void setX(int newX) {
        X=newX;
        p.x=newX;
    }
    public void setY(int newY) {
        Y=newY;
        p.y=newY;
    }

    public Point returnOld() {
        return old;
    }
    public Point returnPosition() {return (Point)p.clone();}
    public boolean returnLife() {
        return haveLife;
    }

    public boolean inThisPosition(int x, int y) {
        if(p.x==x&&p.y==y)
            return true;
        return false;
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
