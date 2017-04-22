package Game;

import Game.BoardState;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import Game.Pieces.*;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by zachary on 4/17/2017.
 */
public class TestPiece {

    @Test
    public void testGetMovesBishop() {
        BoardState mockState = mock(BoardState.class);

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

        HashSet bishopMoveSet = new HashSet<>(bishop.getMoves());
        HashSet moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when nothing is in its way", moveSet, bishopMoveSet);

        when(mockState.getPieceAt(new Point(3, 2))).thenReturn(new Bishop(new Point(), true));
        moves.remove(new Point(3, 2));
        moves.remove(new Point(4, 1));
        moves.remove(new Point(5, 0));

        bishopMoveSet = new HashSet<>(bishop.getMoves());
        moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when Piece is in its way", moveSet, bishopMoveSet);
    }

    @Test
    public void testGetMovesRook() {
        BoardState mockState = mock(BoardState.class);

        Rook rook = new Rook(new Point(2, 3), true);
        rook.setBoardState(mockState);

        LinkedList<Point> moves = new LinkedList<>();
        //up
        moves.add(new Point(2,4));
        moves.add(new Point(2,5));
        moves.add(new Point(2,6));
        moves.add(new Point(2,7));
        //down
        moves.add(new Point(2,2));
        moves.add(new Point(2,1));
        moves.add(new Point(2,0));
        //left
        moves.add(new Point(1,3));
        moves.add(new Point(0,3));
        //right
        moves.add(new Point(3,3));
        moves.add(new Point(4,3));
        moves.add(new Point(5,3));
        moves.add(new Point(6,3));
        moves.add(new Point(7,3));

        HashSet rookMoveSet = new HashSet<>(rook.getMoves());
        HashSet moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when nothing is in its way", moveSet, rookMoveSet);

        when(mockState.getPieceAt(new Point(1, 3))).thenReturn(new Bishop(new Point(), true));
        moves.remove(new Point(1, 3));
        moves.remove(new Point(0, 3));

        rookMoveSet = new HashSet<>(rook.getMoves());
        moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when Piece is in its way", moveSet, rookMoveSet);
    }

    @Test
    public void testGetMovesKing() {
        BoardState mockState = mock(BoardState.class);

        King king = new King(new Point(2, 3), true);
        king.setBoardState(mockState);

        LinkedList<Point> moves = new LinkedList<>();

        //kings moves
        moves.add(new Point(2,4));
        moves.add(new Point(3,4));
        moves.add(new Point(3,3));
        moves.add(new Point(3,2));
        moves.add(new Point(2,2));
        moves.add(new Point(1,2));
        moves.add(new Point(1,3));
        moves.add(new Point(1,4));


        HashSet kingMoveSet = new HashSet<>(king.getMoves());
        HashSet moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when nothing is in its way", moveSet, kingMoveSet);

        when(mockState.getPieceAt(new Point(1, 3))).thenReturn(new Bishop(new Point(), true));
        moves.remove(new Point(1, 3));

        kingMoveSet = new HashSet<>(king.getMoves());
        moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when Piece is in its way", moveSet, kingMoveSet);
    }

    @Test
    public void testGetMovesPawn() {
        BoardState mockState = mock(BoardState.class);

        Pawn pawn = new Pawn(new Point(2, 2), true);
        pawn.setBoardState(mockState);

        LinkedList<Point> moves = new LinkedList<>();

        //one forward
        moves.add(new Point(2,3));
        //two forward
        moves.add(new Point(2,4));


        HashSet pawnMoveSet = new HashSet<>(pawn.getMoves());
        HashSet moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when nothing is in its way", moveSet, pawnMoveSet);

        when(mockState.getPieceAt(new Point(1, 3))).thenReturn(new Bishop(new Point(), true));
        moves.remove(new Point(1, 3));

        pawnMoveSet = new HashSet<>(pawn.getMoves());
        moveSet = new HashSet<>(moves);

        assertEquals("Wrong move set when Piece is in its way", moveSet, pawnMoveSet);
    }
}