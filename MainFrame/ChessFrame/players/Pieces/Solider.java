

package MainFrame.ChessFrame.players.Pieces;

import java.awt.Image;
import java.awt.Point;

public class Solider {
    
    /** Creates a new instance of Solider */
    private int  X,Y;
    private Point pixelPoint=new Point();
    private int pixelX,pixelY;
    private boolean havelife=true;
    private boolean movedbefore=false;
    private pieceIcon PieceIcon;
    private Point p=new Point();
    private Point old=new Point();
    private boolean myseen=false;
    public Solider(String NameIcon,int startX,int startY) {
        
        PieceIcon=new pieceIcon(NameIcon);
        
        
        
        X=startX;
        Y=startY;
        p.x=X;
        p.y=Y;
    }
    public Image returnPieceImage() {
        return PieceIcon.returnPieceIcon();
    }
    public boolean returnLife() {
        return  havelife ;
    }
    public int  returnX() {
        return X;
    }
    public void setPoint(Point newPoint) {
        old.x=p.x;
        old.y=p.y;
        X=p.x=newPoint.x;
        Y=p.y=newPoint.y;
        p.x=X;
        p.y=Y;
        movedbefore=true;
        myseen=false;
    }
    public Point returnOld() {
        return old;
    }
    public void setX(int newX) {
        X=newX;
        p.x=X;
    }
    public void setY(int newY) {
        Y=newY;
        p.y=Y;
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
    public int  returnY() {
        return Y;
    }
    public Point returnPostion() {
        return (Point)p.clone();
    }
    public boolean Inthispostion(int x,int y) {
        if(p.x==x&&p.y==y)
            return true;
        return false;
    }
    public boolean Canmove(int x, int y,String typeColor ) {
        
        if((typeColor.equals("black"))) {
            if((((y-1==Y)&&(x==(X)))) /*&&!Check_Solider_Sees(x,y)*/) {
                
                return true;
                
            } else if((((y-2==Y)&&(x==(X))))&&!movedbefore ) {
                
                return true;
            } else if((y-1==Y&&x+1==(X)||(y-1==Y&&x-1==(X)))&&myseen ) {
                return true;
            }
            
            
            else  return false;
        }
        
        else if (typeColor=="white") {
            if(((y+1==Y)&&(x==(X))) /*&&!Check_Solider_Sees(x,y)*/) {
                return true;
            } else if((((y+2==Y)&&(x==(X)))) &&!movedbefore) {
                return true;
            } else if((y+1==Y&&x+1==(X)||(y+1==Y&&x-1==(X)))&& myseen  ) {
                return true;
            }
            
            else
                return false;
        }
        return false;
        
        
        
    }
    public boolean PieceInMYway(int x, int y,Point othersPostion ,String typeColor ) {
        if(Y-y==2||Y-y==-2) {
            if((typeColor.equals("black"))) {
                
                if((((y-1==othersPostion.y)&&(x==(othersPostion.x))))&&!movedbefore ) {
                    return true;
                } else  return false;
            }
            
            else  if (typeColor.equals("white")) {
                
                if(((y+1==othersPostion.y)&&(x==(othersPostion.x)) &&!movedbefore)) {
                    
                    return true;
                    
                } else
                    return false;
            }
        }
        
        return false;
    }
    public void toOld(Point Old) {
        
        p.x=Old.x;
        p.y=Old.y;
        
    }
    public void setMYseen(boolean newBoolean) {
        myseen=newBoolean;
    }
    public boolean returnMyseen() {
        return myseen;
    }
    public boolean setSeenbychecking(Point newP,String Color) {
        myseen=false;
        if((Color.equals("black"))) {
            if((newP.y-1==Y&&newP.x+1==(X)||(newP.y-1==Y&&newP.x-1==(X)))) {
                
                myseen=true;
                return true;
            } else return false;
        } else if(Color.equals("white")) {
            if((newP.y+1==Y&&newP.x+1==(X)||(newP.y+1==Y&&newP.x-1==(X)))) {
                myseen=true;
                
                return true;
            } else return false;
        }
        return false;
    }
    public Point GeneratePossible_Moves() {
        return new Point();
    }
    public String Tell_me() {
        return "Soldier= ("+p.x+','+p.y+")";
    }
    
}
