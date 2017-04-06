

package MainFrame.ChessFrame.players.Pieces;


import java.awt.Point;


public class Castle extends Piece {

    public Castle(String NameIcon,int  startX,int startY) {
        super(NameIcon, startX, startY);
    }


    public boolean canMove(int x, int y,String s) {
        return (y==Y &&(x>(X)||(x<(X)))) || (((y>Y)||(y<Y))&&(x==(X)));
    }
    public boolean pieceInMyWay(int x, int y, Point othersPosition) {
        int j=y;
        int i=x;
        if(((y==Y)&&(x>(X)||(x<(X))))) {
            
            if((X<i))
                
                while( (i!=X+1)) {
                i--;
                if(((othersPosition.y)==j)&&((othersPosition.x==i)))//there Same Color piece
                {
                    return true;
                }
                }
            
            else  if((X>i)) {
                while( (i!=X-1)) {
                    i++;
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }
        } else if((((y>Y)||(y<Y))&&(x==(X)))) {
            if((Y<j)) {
                while((j!=Y+1)) {
                    j--;
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            } else  if((Y>j)) {
                while((j!=Y-1)) {
                    j++;
                    
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
                
            }
        }
        return false;
        
        
    }
    public boolean checkKing(int x, int y,Point othersPostion) {
        int j=y;
        int i=x;
        if(((y==Y)&&(x>(X)||(x<(X))))) {
            
            if((X<i))
                
                while( (i!=X )) {
                i--;
                if(((othersPostion.y)==j)&&((othersPostion.x==i)))//there Same Color piece
                {
                    return true;
                }
                }
            
            else  if((X>i)) {
                while( (i!=X )) {
                    i++;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i))) {
                        return true;
                    }
                }
            }
        } else if((((y>Y)||(y<Y))&&(x==(X)))) {
            if((Y<j)) {
                while((j!=Y )) {
                    j--;
                    if(((othersPostion.y)==j)&&((othersPostion.x==i))) {
                        return true;
                    }
                }
            } else  if((Y>j)) {
                while((j!=Y )) {
                    j++;
                    
                    if(((othersPostion.y)==j)&&((othersPostion.x==i))) {
                        return true;
                    }
                }
                
            }
        }
        return false;
        
    }
    public String Tell_me() {
        return "Castle= ("+p.x+','+p.y+")";
    }
}
