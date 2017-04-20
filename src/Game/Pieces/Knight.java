

package Game.Pieces;

//

public class Knight extends Piece {

    public Knight(String NameIcon, int startX, int startY) {
        super(NameIcon, startX, startY);
    }

    public boolean canMove(int x, int y, String s) {

        return (x + 1 == X) && (y + 2 == Y) || (x + 1 == X) && (y - 2 == Y) || (x - 1 == X) && (y + 2 == Y)
                || (x - 1 == X) && (y - 2 == Y) || (x + 2 == X) && (y + 1 == Y) || (x + 2 == X) && (y - 1 == Y)
                || (x - 2 == X) && (y + 1 == Y) || (x - 2 == X) && (y - 1 == Y);

    }

    public String Tell_me() {
        return "Knight= ("+p.x+','+p.y+")";
    }
}
