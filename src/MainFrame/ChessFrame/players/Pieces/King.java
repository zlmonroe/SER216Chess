package MainFrame.ChessFrame.players.Pieces;

public class King extends Piece {
    public King(String NameIcon, int startX, int startY)
    {
        super(NameIcon, startX, startY);
    }

    public boolean canMove(int x, int y, String s) {
        return ((y == Y) && (x == (X - 1))) || ((y == Y - 1) && (x == (X + 1)))
                || ((y == Y - 1) && (x == (X - 1))) || ((y == Y + 1) && (x == (X + 1)))
                || (((y == Y + 1) && x == (X - 1))) || ((y == Y) && (x == (X + 1)))
                || ((y == Y - 1) && x == ((X))) || ((y == Y + 1) && (x == (X)));
    }
    
    public String Tell_me()
    {
        return "King= ("+p.x+','+p.y+")";
    }
}