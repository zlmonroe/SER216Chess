

package MainFrame.ChessFrame.players.Pieces;



public class Horse extends Piece {

    public Horse(String NameIcon,int startX,int startY) {
        super(NameIcon, startX, startY);
    }

    public boolean canMove(int x, int y, String s) {

        if((x+1==X)&&(y+2==Y)||(x+1==X)&&(y-2==Y)||(x-1==X)&&(y+2==Y)||(x-1==X)&&(y-2==Y)||(x+2==X)&&(y+1==Y)
        ||(x+2==X)&&(y-1==Y)||(x-2==X)&&(y+1==Y)||(x-2==X)&&(y-1==Y)) {

            return true;
        } else {
            return false;}
        
        
        
    }

    public String Tell_me() {
        return "Horse= ("+p.x+','+p.y+")";
    }
}
