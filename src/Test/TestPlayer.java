package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Point;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.*;
import Game.*;


public class TestPlayer {
	BoardState mockState1;
	Player player1;
	@Before
	public void setUp() throws Exception {
		mockState1 = mock(BoardState.class);
		player1 = new Player(false);
		Player.state = mockState1;
		Point kingPoint = new Point(1, 7);
		Point queenPoint = new Point(1, 6);
		Point bishopPoint = new Point(2, 5);
		LinkedList<Piece> blackPieces = new LinkedList();
		blackPieces.add(new King(kingPoint, false));
		LinkedList<Piece> whitePieces = new LinkedList();
		whitePieces.add(new Queen(queenPoint, true));
		whitePieces.add(new Bishop(bishopPoint, true));
		when(mockState1.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0));
		when(mockState1.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0));
		when(mockState1.getPieceAt(bishopPoint)).thenReturn(whitePieces.get(1));
		when(mockState1.getPieces(false)).thenReturn(blackPieces);
		when(mockState1.getPieces(true)).thenReturn(blackPieces);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInCheck() {
		assertTrue(player1.inCheck(mockState1));
	}

}