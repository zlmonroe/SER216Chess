package Game;

import java.awt.*;

/**
 * Created by tjcup on 4/19/2017.
 */
public class Move {
    public Point oldPoint,newPoint;

    /**
     * Represents a move from oldPoint to newPoint
     * @param oldPoint The starting position
     * @param newPoint The ending position
     */
    public Move(Point oldPoint, Point newPoint) {
        this.oldPoint = oldPoint;
        this.newPoint = newPoint;
    }
}
