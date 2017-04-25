import static org.junit.Assert.*;

import java.awt.Point;
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
		Piece knight = board1.getPieceAt(new Point(1, 0));
		
		LinkedList<Point> moves = new LinkedList();
		
	}

}
