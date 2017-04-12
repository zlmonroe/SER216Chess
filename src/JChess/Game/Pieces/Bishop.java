package JChess.Game.Pieces;

import java.awt.*;

public class Bishop extends Piece {

    public Bishop(String NameIcon, int startX, int startY)
    {
       super(NameIcon, startX, startY);
    }

    public boolean canMove(int x, int y, String s) {
        return (x - y) == (X - Y) || (x + y) == (X + Y);
    }
    public boolean PieceInMYway(int x, int y,Point othersPosition) {
        int j=y;
        int i=x;

        if((x-y)==(X-Y)) {
            if(x>X&&y>Y) {
                while((j!=Y+1)&&(i!=X+1)) {
                    j--;i--;
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }
            
            
            else if(x<X&&y<Y)
                while((j!=Y-1)&&(i!=X-1)) {
                    j++;i++;

                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
        }
        
        else if(((x+y))==((X+Y))) {
            if((X<i)&&(Y>j)) {
                while(((j!=Y-1))&&((i!=X+1))) {
                    j++;i--;
                    
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }
            
            else  if((X>i)&&(Y<j)) {
                while((j!=X+1)&&(i!=X-1)) {
                    j--;i++;
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkKing(int x, int y,Point othersPosition) {
        int j=y;
        int i=x;
        
        if((x-y)==(X-Y)) {
            if(x>X&&y>Y) {
                while((j!=Y )&&(i!=X )) {
                    j--;i--;
                    
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }

            else if(x<X&&y<Y)
                while((j!=Y )&&(i!=X )) {
                    j++;i++;

                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
        }
        
        else if(((x+y))==((X+Y))) {
            if((X<i)&&(Y>j)) {
                while(((j!=Y ))&&((i!=X ))) {
                    j++;i--;

                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }
            
            else  if((X>i)&&(Y<j)) {
                while((j!=X )&&(i!=X )) {
                    j--;i++;
                    if(((othersPosition.y)==j)&&((othersPosition.x==i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public String Tell_me()
    {
        return "Bishop= ("+p.x+','+p.y+")";
    }
}
