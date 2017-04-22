
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
	Piece mockKing;
	Piece mockQueen;
	Player player1;
	@Before
	public void setUp() throws Exception {
		player1 = new Player(false);
		Player.state = mockState1;
		Point kingPoint = new Point(1, 7);
		Point queenPoint = new Point(1, 6);
		LinkedList<Piece> blackPieces = new LinkedList();
		blackPieces.add(new King(kingPoint, false));
		LinkedList<Piece> whitePieces = new LinkedList();
		whitePieces.add(new Queen(queenPoint, true));
		when(mockState1.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0));
		when(mockState1.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0));
		when(mockState1.getPieceAt(bishopPoint)).thenReturn(whitePieces.get(1));
		when(mockState1.getPieces(false)).thenReturn(blackPieces);
		when(mockState1.getPieces(true)).thenReturn(blackPieces);
		
	}
	
	private void createMock1(){
		
		mockKing = mock(King.class);
		mockQueen = mock(Queen.class);
		
		Point kingPoint = new Point(1, 7);
		Point queenPoint = new Point(1, 6);
		
		when(mockKing.isWhite()).thenReturn(false);
		when(mockQueen.isWhite()).thenReturn(true);
		when(mockKing.getPosition()).thenReturn(kingPoint);
		when(mockQueen.getPosition()).thenReturn(queenPoint);
		
		mockState1 = mock(BoardState.class);
		
		LinkedList<Piece> blackPieces = new LinkedList();
		blackPieces.add(mockKing);
		LinkedList<Piece> whitePieces = new LinkedList();
		whitePieces.add(mockQueen);
		
		when(mockState1.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0));
		when(mockState1.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0));
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