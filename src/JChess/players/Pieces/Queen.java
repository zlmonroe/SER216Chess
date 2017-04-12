
package JChess.players.Pieces;
import java.awt.Point;
public class Queen extends Piece {
    public Queen(String NameIcon, int startX, int startY) {
        super(NameIcon, startX, startY);
    }
    public boolean canMove(int x, int y, String s) {
        
        if (((y == Y) && (x > (X) || (x < X)))) {
            return true;
        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            return true;
        } else if ((x - y) == (X - Y)) {
            return true;
        } else return (x + y) == (X + Y);
    }
    public boolean PieceInMYway(int x, int y, Point othersPosition) {
        int j = y;
        int i = x;
        if (((y == Y) && (x > (X) || (x < (X))))) {
            if ((X < i))
                while ((i != X + 1)) {
                    i--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i)))//there Same Color piece
                    {
                        return true;
                    }
                }
            else if ((X > i)) {
                while ((i != X - 1)) {
                    i++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            }
        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            if ((Y < j)) {
                while ((j != Y + 1)) {
                    j--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            } else if ((Y > j)) {
                while ((j != Y - 1)) {
                    j++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            }
        } else if ((x - y) == (X - Y)) {
            if (x > X && y > Y) {
                while ((j != Y + 1) && (i != X + 1)) {
                    j--;
                    i--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            } else if (x < X && y < Y)
                while ((j != Y - 1) && (i != X - 1)) {
                    j++;
                    i++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
        } else if ((x + y) == (X + Y)) {
            if ((X < i) && (Y > j)) {
                while ((j != Y - 1) && (i != X + 1)) {
                    j++;
                    i--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            } else if ((X > i) && (Y < j)) {
                while ((j != Y + 1) && (i != X - 1)) {
                    j--;
                    i++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkKing(int x, int y, Point othersPosition) {
        int j = y;
        int i = x;
        if (((y == Y) && (x > (X) || (x < (X))))) {
            if ((X < i))
                while ((i != X)) {
                    i--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i)))//there Same Color piece
                    {
                        return true;
                    }
                }
            else if ((X > i)) {
                while ((i != X)) {
                    i++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            }
        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            if ((Y < j)) {
                while ((j != Y)) {
                    j--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            } else if ((Y > j)) {
                while ((j != Y)) {
                    j++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            }
        } else if ((x - y) == (X - Y)) {
            if (x > X && y > Y) {
                while ((j != Y) && (i != X)) {
                    j--;
                    i--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            } else if (x < X && y < Y)
                while ((j != Y) && (i != X)) {
                    j++;
                    i++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
        } else if ((x + y) == (X + Y)) {
            if ((X < i) && (Y > j)) {
                while ((j != Y) && (i != X)) {
                    j++;
                    i--;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            } else if ((X > i) && (Y < j)) {
                while ((j != Y) && (i != X)) {
                    j--;
                    i++;
                    if (((othersPosition.y) == j) && ((othersPosition.x == i))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public String Tell_me() {
        return "Queen= (" + p.x + ',' + p.y + ")";
    }
}
