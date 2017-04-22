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
	Piece mockKing;
	Piece mockQueen;
	Player player1;
	@Before
	public void setUp() throws Exception {
		player1 = new Player(false);
	}
	
	/*This creates the first mock board state
	 * It creates a mock board with a mock black king 
	 * at b7 and a mock white queen at b6 
	 */
	private void createMock1(){		
		mockKing = mock(King.class); //creates mock king
		mockQueen = mock(Queen.class); //creates mock queen
		
		Point kingPoint = new Point(1, 7); //this is the point, b7, that the king is at
		Point queenPoint = new Point(1, 6); //this is the point, b6, that the queen is at
		
		when(mockKing.isWhite()).thenReturn(false); //the king is black
		when(mockQueen.isWhite()).thenReturn(true); //the queen is white
		when(mockKing.getPosition()).thenReturn(kingPoint); //the kings position should return a point 1, 7 (b7)
		when(mockQueen.getPosition()).thenReturn(queenPoint); //the queens position should return a point 1, 6 (b6)
		when(mockKing.getIdentifier()).thenReturn(5); //the king should return the number that identifies it as a king
		when(mockQueen.canMove(kingPoint)).thenReturn(true);// the queen has the ability to move forward one, as is chess rules
		
		mockState1 = mock(BoardState.class);//creates mock board
		
		LinkedList<Piece> blackPieces = new LinkedList(); //linked list with all of black pieces
		blackPieces.add(mockKing);
		LinkedList<Piece> whitePieces = new LinkedList(); //linked list with all of white pieces
		whitePieces.add(mockQueen);
		
		when(mockState1.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(mockState1.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0)); //when we ask for the piece at the queen's point, we should get the queen
		when(mockState1.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
		when(mockState1.getPieces(true)).thenReturn(whitePieces); //when we ask for white pieces, we should get white pieces
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInCheck() {
		createMock1();
		Player.state = mockState1;//testing inCheck for mockState1
		assertTrue(player1.inCheck(mockState1)); //The king is in check if the queen can attack him
	}
	
	public void testInCheckMate(){
		Player.state = mockState1;//testing inCheck for mockState1
		assertFalse(player1.inCheckMate());//the king is not in checkmate because he can geet the queen
	}

}