import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.Piece;

public class TestBishopAndBoard {

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
		Piece bishopWhite = board1.getPieceAt(new Point(2, 0));
		assertTrue(bishopWhite.getIdentifier() == 2);
		assertTrue(bishopWhite.isWhite());
		
		assertEquals(new HashSet(moves), new HashSet(bishopWhite.getMoves()));
		
		board1 = board1.move(new Point(3, 1), new Point(3, 3));
		
		bishopWhite = board1.getPieceAt(new Point(2, 0));
		assertTrue(bishopWhite.getIdentifier() == 2);
		assertTrue(bishopWhite.isWhite());
		
		moves.add(new Point(3, 1));
		moves.add(new Point(4, 2));
		moves.add(new Point(5, 3));
		moves.add(new Point(6, 4));
		moves.add(new Point(7, 5));
		
		assertEquals(new HashSet(moves), new HashSet(bishopWhite.getMoves()));
		
		board1 = board1.move(new Point(2, 0), new Point(5, 3));
		
		bishopWhite = board1.getPieceAt(new Point(5, 3));
		assertTrue(bishopWhite.getIdentifier() == 2);
		assertTrue(bishopWhite.isWhite());
		
		moves = new LinkedList<Point>();
		
		moves.add(new Point(2, 0));
		moves.add(new Point(3, 1));
		moves.add(new Point(4, 2));
		moves.add(new Point(6, 4));
		moves.add(new Point(7, 5));
		
		moves.add(new Point(6, 2));
		moves.add(new Point(4, 4));
		moves.add(new Point(3, 5));
		moves.add(new Point(2, 6));
		
		assertEquals(new HashSet(moves), new HashSet(bishopWhite.getMoves()));
		
		board1 = board1.move(new Point(5, 3), new Point(2, 6));
		
		bishopWhite = board1.getPieceAt(new Point(2, 6));
		assertTrue(bishopWhite.getIdentifier() == 2);
		assertTrue(bishopWhite.isWhite());
	}

}
