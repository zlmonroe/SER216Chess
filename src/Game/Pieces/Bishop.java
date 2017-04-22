package Game.Pieces;

import java.awt.*;
import java.util.LinkedList;

import static javafx.scene.input.KeyCode.X;

public class Bishop extends Piece {

    public Bishop(String nameIcon, Point start, boolean isWhite) {
        super(nameIcon, start, isWhite);
        this.identifier = 2;
    }

    public LinkedList<Point> getMoves() {
        LinkedList<Point>
    }
}
