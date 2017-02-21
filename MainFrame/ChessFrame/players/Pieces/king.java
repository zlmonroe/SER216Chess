

package MainFrame.ChessFrame.players.Pieces;

import java.awt.Image;
import java.awt.Point;
import java.io.IOException;


public class king
{
    
    /** Creates a new instance of king */
    private int  X,Y;
    private Point pixelPoint=new Point();
    private int pixelX,pixelY;
    private boolean havelife=true;
    private pieceIcon PieceIcon;
    private Point old=new Point();
    private Point p=new Point();
    public king(String NameIcon,int startX,int startY)
    {
        
        PieceIcon=new pieceIcon(NameIcon);
        
        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
    }
    public Image returnPieceImage()
    {
        return PieceIcon.returnPieceIcon();
    }
    public Point returnPostion()
    {
        return  (Point)p.clone();
    }
    public int  returnX()
    {
        X=p.x;
        return X;
    }
    public void setPixels(int newpixelX,int newpixelY)
    {
        pixelPoint.x=newpixelX;
        pixelPoint.y=newpixelY;
    }
    public int getPixelX()
    {
        return pixelX;
    }
    public int getPixelY()
    {
        return pixelY;
    }
    public Point getpixelPoint()
    {
        return  pixelPoint;
    }
    public int  returnY()
    {
        Y=p.y;
        return Y;
    }
    public void setPoint(Point newPoint)
    {
        old.x=p.x;
        old.y=p.y;
        
        p.x=newPoint.x;
        
        p.y=newPoint.y;
        
        X=p.x;
        Y=p.y;
    }
    public void toOld(Point Old)
    {
        
        p.x=Old.x;
        p.y=Old.y;
        
    }
    public Point returnOld()
    {
        return old;
    }
    public void setX(int newX)
    {
        X=newX;
        p.x=X;
    }
    public void setY(int newY)
    {
        Y=newY;
        p.y=Y;
    }
    public boolean Inthispostion(int x,int y)
    {
        if(p.x==x&&p.y==y)
            return true;//cant kill the king anymore;
        return false;
    }
    
    public boolean returnLife()
    {
        return  havelife ;
    }
    public boolean Canmove(int x, int y)
    {
        
        if(((y==Y)&&(x==(X-1)))||((y==Y-1)&&(x==(X+1)))
        ||((y==Y-1)&&(x==(X-1)))||((y==Y+1)&&(x==(X+1)))
        ||(((y==Y+1)&&x==(X-1)))||((y==Y)&&(x==(X+1)))
        ||((y==Y-1)&&x==((X)))||((y==Y+1)&&(x==(X))))
        {
            
            
            return true;
        }
        
        
        return false;
        
    }
    
    public Point GeneratePossible_Moves()
    {
        return new Point();
    }
    public String Tell_me()
    {
        return "King= ("+p.x+','+p.y+")";
    }
    
    
    
}
