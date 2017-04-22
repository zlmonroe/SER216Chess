

import Game.BoardState;
import Game.Pieces.Bishop;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by zachary on 4/17/2017.
 */
public class TestPiece {

    @Test
    public void testGetMoves() {
        BoardState mockState = mock(BoardState.class);
        when(mockState.getPieceAt(any(Point.class))).thenReturn(null);

        Bishop bishop = new Bishop(new Point(2, 3), true);
        bishop.setBoardState(mockState);

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