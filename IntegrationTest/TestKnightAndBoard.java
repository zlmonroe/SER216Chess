import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.Piece;

public class TestKnightAndBoard {

	BoardState board1;
	
	@Before
	public void setUp() throws Exception {
		board1 = new BoardState();
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * fnc tests the knight moving in both directions, killing enemies, and not moving on its own color
	 */
	@Test
	public void testKnightBasicMoves() { 
		//Unlike pawn, we don't have to go back and forth between the players because there is nothing like en passant, which is turn dependent
		Piece knightWhite = board1.getPieceAt(new Point(1, 0));
		assertTrue(knightWhite.getIdentifier() ==1);
		assertTrue(knightWhite.isWhite());
		
		LinkedList<Point> moves = new LinkedList();
		moves.add(new Point(0, 2));
		moves.add(new Point(2, 2));
		
		assertEquals(new HashSet(moves), new HashSet(knightWhite.getMoves()));
		
		board1.move(new Point(1, 0),new Point(2, 2));
	}

}
