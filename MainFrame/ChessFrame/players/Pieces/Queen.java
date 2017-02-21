

package MainFrame.ChessFrame.players.Pieces;

import java.awt.Image;
import java.awt.Point;


public class Queen
{
    
    /** Creates a new instance of Queen */
    private int  X,Y;
    private Point pixelPoint=new Point();
    private int pixelX,pixelY;
    private boolean havelife=true;
    private pieceIcon PieceIcon;
    private Point p=new Point();
    private Point old=new Point();
    public Queen(String NameIcon,int startX,int startY)
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
        return (Point)p.clone();
    }
    public int  returnX()
    {
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
        return Y;
    }
    public void setPoint(Point newPoint)
    {
        old.x=p.x;
        old.y=p.y;
        X=p.x=newPoint.x;
        Y=p.y=newPoint.y;
    }
    public void setX(int newX)
    {
        X=newX;
        p.x=X;
    }
    public Point returnOld()
    {
        return old;
    }
    public void setY(int newY)
    {
        Y=newY;
        p.y=Y;
    }
    public void toOld(Point Old)
    {
        
        p.x=Old.x;
        p.y=Old.y;
        
    }
    public boolean returnLife()
    {
        return  havelife ;
    }
    public boolean Inthispostion(int x,int y)
    {
        if(p.x==x&&p.y==y)
            return true;
        return false;
    }
    public boolean Canmove(int x, int y)
    {
        
        
        if(((y==Y)&&(x>(X)||(x<X))))
        {
            return true;
            
        }
        else if((((y>Y)||(y<Y))&&(x==(X))))
        {
            return true;
        }
        else   if((x-y)==(X-Y))
        {
            return true;
            
        }
        else if((x+y)==(X+Y))
        {
            return true;
            
        }
        else
        {
            
            return false;
        }
        
    }
    public boolean PieceInMYway(int x, int y,Point othersPostion)
    {
        int j=y;
        int i=x;
        if(((y==Y)&&(x>(X)||(x<(X)))))
        {
            if((X<i))
                while( (i!=X+1))
                {
                i--;
                if(((othersPostion.y)==j)&&((othersPostion.x==i)))//there Same Color piece
                {
                    return true;
                }
                }
            
            else  if((X>i))
            {
                while( (i!=X-1))
                {
                    i++;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
        }
        
        else if((((y>Y)||(y<Y))&&(x==(X))))
        {
            if((Y<j))
            {
                while((j!=Y+1))
                {
                    j--;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
            else  if((Y>j))
            {
                while((j!=Y-1))
                {
                    j++;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
                
            }
        }
        else   if((x-y)==(X-Y))
        {
            if(x>X&&y>Y)
            {
                while((j!=Y+1)&&(i!=X+1))
                {
                    j--;i--;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
            
            
            else if(x<X&&y<Y)
                
                while((j!=Y-1)&&(i!=X-1))
                {
                j++;i++;
                
                
                if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                {
                    return true;
                }
                
                }
        }
        
        else if((x+y)==(X+Y))
        {
            
            if((X<i)&&(Y>j))
            {
                while((j!=Y-1)&&(i!=X+1))
                {
                    j++;i--;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                    
                }
                
            }
            
            
            else     if((X>i)&&(Y<j))
            {
                while((j!=Y+1)&&(i!=X-1))
                {
                    j--;i++;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
    public boolean checkKing(int x, int y,Point othersPostion)
    {
        int j=y;
        int i=x;
        if(((y==Y)&&(x>(X)||(x<(X)))))
        {
            if((X<i))
                while( (i!=X ))
                {
                i--;
                if(((othersPostion.y)==j)&&((othersPostion.x==i)))//there Same Color piece
                {
                    return true;
                }
                }
            
            else  if((X>i))
            {
                while( (i!=X ))
                {
                    i++;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
        }
        
        else if((((y>Y)||(y<Y))&&(x==(X))))
        {
            if((Y<j))
            {
                while((j!=Y ))
                {
                    j--;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
            else  if((Y>j))
            {
                while((j!=Y ))
                {
                    j++;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
                
            }
        }
        else   if((x-y)==(X-Y))
        {
            if(x>X&&y>Y)
            {
                while((j!=Y )&&(i!=X ))
                {
                    j--;i--;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
            
            
            else if(x<X&&y<Y)
                
                while((j!=Y  )&&(i!=X ))
                {
                j++;i++;
                
                
                if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                {
                    return true;
                }
                
                }
        }
        
        else if((x+y)==(X+Y))
        {
            
            if((X<i)&&(Y>j))
            {
                while((j!=Y )&&(i!=X ))
                {
                    j++;i--;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                    
                }
                
            }
            
            
            else     if((X>i)&&(Y<j))
            {
                while((j!=Y )&&(i!=X ))
                {
                    j--;i++;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i)))
                    {
                        return true;
                    }
                }
            }
            
        }
        return false;
    }
    public Point GeneratePossible_Moves()
    {
        return new Point();
    }
    
    public String Tell_me()
    {
        return "Queen= ("+p.x+','+p.y+")";
    }
    
}
