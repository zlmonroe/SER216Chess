import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.Piece;

public class TestRookAndBoard {

	BoardState board1;
	
	@Before
	public void setUp() throws Exception {
		board1 = new BoardState();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		LinkedList<Point> moves = new LinkedList<Point>();
		Piece rookWhite = board1.getPieceAt(new Point(0, 0));
		assertTrue(rookWhite.getIdentifier() == 3);
		assertTrue(rookWhite.isWhite());
		
		assertEquals(new HashSet(moves), new HashSet(rookWhite.getMoves()));
		
		board1 = board1.move(new Point(0, 1), new Point(0, 3));//moving paw out of way
		
		rookWhite = board1.getPieceAt(new Point(0, 0));
		assertTrue(rookWhite.getIdentifier() == 3);
		assertTrue(rookWhite.isWhite());
		
		moves.add(new Point(0, 1));
		moves.add(new Point(0, 2));
		
		assertEquals(new HashSet(moves), new HashSet(rookWhite.getMoves()));
		
		board1 = board1.move(new Point(0, 0), new Point(0, 2));
		
		rookWhite = board1.getPieceAt(new Point(0, 2));
		assertTrue(rookWhite.getIdentifier() == 3);
		assertTrue(rookWhite.isWhite());
		
		moves = new LinkedList<Point>();
		moves.add(new Point(1, 2));
		moves.add(new Point(2, 2));
		moves.add(new Point(3, 2));
		moves.add(new Point(4, 2));
		moves.add(new Point(5, 2));
		moves.add(new Point(6, 2));
		moves.add(new Point(7, 2));
		moves.add(new Point(0, 1));
		moves.add(new Point(0, 0));
		
		assertEquals(new HashSet(moves), new HashSet(rookWhite.getMoves()));
		
		board1 = board1.move(new Point(0, 2), new Point(4, 2));
		
		rookWhite = board1.getPieceAt(new Point(4, 2));
		assertTrue(rookWhite.getIdentifier() == 3);
		assertTrue(rookWhite.isWhite());
		
		moves = new LinkedList<Point>();
		moves.add(new Point(0, 2));
		moves.add(new Point(1, 2));
		moves.add(new Point(2, 2));
		moves.add(new Point(3, 2));
		moves.add(new Point(5, 2));
		moves.add(new Point(6, 2));
		moves.add(new Point(7, 2));
		
		moves.add(new Point(4, 3));
		moves.add(new Point(4, 4));
		moves.add(new Point(4, 5));
		moves.add(new Point(4, 6));
		
		Piece pawnBlack = board1.getPieceAt(new Point(4, 6));
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		Piece kingBlack = board1.getPieceAt(new Point(4, 7));
		assertTrue(kingBlack.getIdentifier() == 5);
		assertFalse(kingBlack.isWhite());
		
		assertEquals(new HashSet(moves), new HashSet(rookWhite.getMoves()));
	}

}
