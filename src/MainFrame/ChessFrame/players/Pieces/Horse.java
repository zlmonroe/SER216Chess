

package MainFrame.ChessFrame.players.Pieces;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

public class Horse {
    
    /** Creates a new instance of Horse */
    private int  X,Y;
    private Point pixelPoint=new Point();
    private int pixelX,pixelY;
    private boolean havelife=true;
    private Point old=new Point();
    
    private pieceIcon PieceIcon;
    private Point p=new Point();
    public Horse(String NameIcon,int startX,int startY) {
        
        PieceIcon=new pieceIcon(NameIcon);
        
        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
    }
    public Point returnPostion() {
        return (Point)p.clone();
    }
    public Image returnPieceImage() {
        return PieceIcon.returnPieceIcon();
    }
    public int  returnX() {
        return X;
    }
    public void setPoint(Point newPoint) {
        old.x=p.x;
        old.y=p.y;
        X=p.x=newPoint.x;
        Y=p.y=newPoint.y;
    }
    public Point returnOld() {
        return (Point)old.clone();
        
    }
    public void setPixels(int newpixelX,int newpixelY) {
        pixelPoint.x=newpixelX;
        pixelPoint.y=newpixelY;
    }
    public int getPixelX() {
        return pixelX;
    }
    public int getPixelY() {
        return pixelY;
    }
    public Point getpixelPoint() {
        return  pixelPoint;
    }
    public void setX(int newX) {
        X=newX;
        p.x=X;
    }
    public void setY(int newY) {
        Y=newY;
        p.y=Y;
    }
    public int  returnY() {
        return Y;
    }
    
    public boolean returnLife() {
        return  havelife ;
    }
    public boolean Inthispostion(int x,int y) {
        if(p.x==x&&p.y==y)
            return true;
        return false;
    }
    public void toOld(Point Old) {
        
        p.x=Old.x;
        p.y=Old.y;
        
    }
    public boolean Canmove(int x, int y) {
        
        if((x+1==X)&&(y+2==Y)||(x+1==X)&&(y-2==Y)||(x-1==X)&&(y+2==Y)||(x-1==X)&&(y-2==Y)||(x+2==X)&&(y+1==Y)
        ||(x+2==X)&&(y-1==Y)||(x-2==X)&&(y+1==Y)||(x-2==X)&&(y-1==Y)) {
            
            return true;
        } else {
            return false;}
        
        
        
    }
    public Point GeneratePossible_Moves() {
        return new Point();
    }
    public String Tell_me() {
        return "Horse= ("+p.x+','+p.y+")";
    }
}
