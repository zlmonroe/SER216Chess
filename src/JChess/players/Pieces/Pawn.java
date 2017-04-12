

package JChess.players.Pieces;

import java.awt.Point;
import java.util.Objects;

public class Pawn extends Piece{

    /** Creates a new instance of Pawn */
    private boolean myseen;
    private boolean movedbefore;

    public Pawn(String NameIcon, int startX, int startY) {
        super(NameIcon, startX, startY);
        myseen=false;
        movedbefore=false;
    }

    public boolean canMove(int x, int y,String typeColor ) {
        
        if((typeColor.equals("black"))) {
            if((((y-1==Y)&&(x==(X)))) /*&&!Check_Solider_Sees(x,y)*/) {
                
                return true;
                
            } else if((((y-2==Y)&&(x==(X))))&&!movedbefore ) {
                
                return true;
            } else return (y - 1 == Y && x + 1 == (X) || (y - 1 == Y && x - 1 == (X))) && myseen;
        }
        
        else if (Objects.equals(typeColor, "white")) {
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
    public boolean PieceInMYway(int x, int y,Point othersPosition ,String typeColor ) {
        if(Y-y==2||Y-y==-2) {
            if((typeColor.equals("black"))) {
                
                if((((y-1==othersPosition.y)&&(x==(othersPosition.x))))&&!movedbefore ) {
                    return true;
                } else  return false;
            }
            
            else  if (typeColor.equals("white")) {
                
                if(((y+1==othersPosition.y)&&(x==(othersPosition.x)) &&!movedbefore)) {
                    
                    return true;
                    
                } else
                    return false;
            }
        }
        
        return false;
    }

    public void setMySeen(boolean newBoolean) {
        myseen=newBoolean;
    }
    public boolean returnMyseen() {
        return myseen;
    }
    public boolean setSeenbychecking(Point newP,String Color) {
        myseen = false;
        if ((Color.equals("black"))) {
            if ((newP.y - 1 == Y && newP.x + 1 == (X) || (newP.y - 1 == Y && newP.x - 1 == (X)))) {

                myseen = true;
                return true;
            } else return false;
        } else if (Color.equals("white")) {
            if ((newP.y + 1 == Y && newP.x + 1 == (X) || (newP.y + 1 == Y && newP.x - 1 == (X)))) {
                myseen = true;

                return true;
            } else return false;
        }
        return false;
    }
    public String Tell_me() {
        return "Soldier= ("+p.x+','+p.y+")";
    }
    
}
