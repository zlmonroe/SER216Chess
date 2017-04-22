package Test;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import Game.Pieces.*;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by zachary on 4/17/2017.
 */
public class TestPiece {
    private Bishop bishop;
    private King king;
    private Knight knight;
    private Pawn pawn;
    private Queen queen;
    private Rook rook;
    private Pawn piece;

    @After
    public void tearDown(){
        bishop = null;
        king = null;
        knight = null;
        pawn = null;
        queen = null;
        rook = null;
        piece = null;
    }

    @Test
    public void testGetMoves() {
        bishop = new Bishop(new Point(2, 3), true);
        LinkedList<Point> moves = new LinkedList<>();
        //down left
        moves.add(new Point(1, 2));
        moves.add(new Point(0, 1));
        //up left
        moves.add(new Point(1, 4));
        moves.add(new Point(0, 5));
        //down right
        moves.add(new Point(3, 2));
        moves.add(new Point(4, 1));
        moves.add(new Point(5, 0));
        //up right
        moves.add(new Point(3, 4));
        moves.add(new Point(4, 5));
        moves.add(new Point(5, 6));
        moves.add(new Point(6, 7));

        assertEquals("The bishop's moves are not correct", new HashSet<>(bishop.getMoves()), new HashSet<>(moves));

    }
}