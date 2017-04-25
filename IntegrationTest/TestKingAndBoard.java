import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.Piece;

public class TestKingAndBoard {
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
		Piece kingWhite = board1.getPieceAt(new Point(4, 0));
		assertTrue(kingWhite.getIdentifier() == 5);
		assertTrue(kingWhite.isWhite());
		
		//assertEquals(new HashSet(moves), new HashSet(kingWhite.getMoves()));
		
		board1 = board1.move(new Point(4, 1), new Point(4, 3));
		
		moves.add(new Point(4, 1));
		
		//assertEquals(new HashSet(moves), new HashSet(kingWhite.getMoves()));
		
		board1 = board1.move(new Point(4, 0), new Point(4, 1));
		kingWhite = board1.getPieceAt(new Point(4, 1));
		assertTrue(kingWhite.getIdentifier() == 5);
		assertTrue(kingWhite.isWhite());
		
		moves = new LinkedList<Point>();
		
		moves.add(new Point(4, 0));
		moves.add(new Point(4, 2));
		moves.add(new Point(3, 2));
		moves.add(new Point(5, 2));
		
		assertEquals(new HashSet(moves), new HashSet(kingWhite.getMoves()));
		
		board1 = board1.move(new Point(4, 1), new Point(3, 2));
		kingWhite = board1.getPieceAt(new Point(3, 2));
		assertTrue(kingWhite.getIdentifier() == 5);
		assertTrue(kingWhite.isWhite());
		
		moves = new LinkedList<Point>();
		
		moves.add(new Point(4, 1));
		moves.add(new Point(4, 2));
		moves.add(new Point(2, 3));
		moves.add(new Point(3, 3));
		moves.add(new Point(2, 2));
		
		assertEquals(new HashSet(moves), new HashSet(kingWhite.getMoves()));
		
		board1 = board1.move(new Point(3, 2), new Point(2, 3));
		kingWhite = board1.getPieceAt(new Point(2, 3));
		assertTrue(kingWhite.getIdentifier() == 5);
		assertTrue(kingWhite.isWhite());
		
		moves = new LinkedList<Point>();
		
		moves.add(new Point(1, 2));
		moves.add(new Point(2, 2));
		moves.add(new Point(3, 2));
		moves.add(new Point(1, 3));
		moves.add(new Point(3, 3));
		moves.add(new Point(1, 4));
		moves.add(new Point(2, 4));
		moves.add(new Point(3, 4));
		
		assertEquals(new HashSet(moves), new HashSet(kingWhite.getMoves()));
		
		board1 = board1.move(new Point(2, 3), new Point(2, 5)); //skipping a move
		
		kingWhite = board1.getPieceAt(new Point(2, 5));
		assertTrue(kingWhite.getIdentifier() == 5);
		assertTrue(kingWhite.isWhite());
		
		moves = new LinkedList<Point>();
		
		moves.add(new Point(1, 4));
		moves.add(new Point(2, 4));
		moves.add(new Point(3, 4));
		moves.add(new Point(1, 5));
		moves.add(new Point(3, 5));
		moves.add(new Point(1, 6));
		moves.add(new Point(2, 6));
		moves.add(new Point(3, 6));
		
		assertEquals(new HashSet(moves), new HashSet(kingWhite.getMoves()));
	}		

}
