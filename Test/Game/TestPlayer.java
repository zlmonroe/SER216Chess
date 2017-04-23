package Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.awt.Point;
import java.util.Collection;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.*;
import Game.*;


public class TestPlayer {
	BoardState mockState1;
	BoardState mockState2;
	BoardState mockState3;
	BoardState mockState4;
	
	Piece mockKing;
	Piece mockQueen;
	Player player1;
	@Before
	public void setUp() throws Exception {
		player1 = new Player(false);
		
		mockKing = mock(King.class); //creates mock king
		mockQueen = mock(Queen.class); //creates mock queen
		
		mockState1 = mock(BoardState.class);//creates mock board
	}
	
	/*This creates the first mock board state
	 * It creates a mock board with a mock black king 
	 * at b7 and a mock white queen at b6 
	 */
	private void createMock1(){
		Point kingPoint = new Point(1, 7);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(1,6), new Point(2,6), new Point(2,7)};
		Point queenPoint = new Point(1, 6);	
		//list of all relevant moves possible by the queen (according to chess rules)
		Point[] queenMoves = {new Point(0,7), new Point(0,6), new Point(1,6), new Point(1,7), new Point(2,6), new Point(2,7)};
		
		createMockKing(kingPoint, kingMoves);
		createMockQueen(queenPoint, queenMoves);
		
		//Creating mock fncs for mockState1
		LinkedList<Piece> blackPieces = new LinkedList(); //linked list with all of black pieces
		blackPieces.add(mockKing);
		LinkedList<Piece> whitePieces = new LinkedList(); //linked list with all of white pieces
		whitePieces.add(mockQueen);
		
		when(mockState1.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(mockState1.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0)); //when we ask for the piece at the queen's point, we should get the queen
		when(mockState1.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
		when(mockState1.getPieces(true)).thenReturn(whitePieces); //when we ask for white pieces, we should get white pieces
		when(mockState1.move(new Point(1, 7), new Point(0, 7))).thenReturn(mockState2);
	}
	
	//mock2 is mock1 after the king has moved to 1, 6 and killed the queen
	private void createMock2(){
		Point kingPoint = new Point(1, 6);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(0,5), new Point(1,7), new Point(1,5),new Point(2,5), new Point(2,6), new Point(2,7)};
		createMockKing(kingPoint, kingMoves);
		
		//Creating mock fncs for mockState1
		LinkedList<Piece> blackPieces = new LinkedList(); //linked list with all of black pieces
		blackPieces.add(mockKing);
		
		when(mockState2.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(mockState1.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
	}
	
	private void createMockKing(Point kingPoint, Point[] tmpPoints){
		 //this is the point, b7, that the king is at
		LinkedList<Point> kingMoves = new LinkedList();
		for(Point p: tmpPoints){
			kingMoves.add(p);
		}
		when(mockKing.isWhite()).thenReturn(false); //the king is black
		when(mockKing.getPosition()).thenReturn(kingPoint); //the kings position should return a point 1, 7 (b7)
		when(mockKing.getIdentifier()).thenReturn(5); //the king should return the number that identifies it as a king
		when(mockKing.getMoves()).thenReturn(kingMoves);
	}
	
	private void createMockQueen(Point queenPoint, Point[] tmpPoints){
		 //this is the point, b6, that the queen is at
		LinkedList<Point> queenMoves = new LinkedList();
		//only includes points that the king can move to
		for(Point p: tmpPoints){
			queenMoves.add(p);
		}
		when(mockQueen.isWhite()).thenReturn(true); //the queen is white
		when(mockQueen.getPosition()).thenReturn(queenPoint); //the queens position should return a point 1, 6 (b6)
		when(mockQueen.getMoves()).thenReturn(queenMoves);
		when(mockQueen.canMove(new Point(1, 7))).thenReturn(true);// the queen has the ability to move forward, as is chess rules
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
	
	public void testMove(){
		createMock1();
		Player.state = mockState1;//testing move for mockState1
		assertFalse(player1.move(new Point(1, 7), new Point(0, 7)));//king cannot move to a place that will not get him out of check
		assertTrue(player1.move(new Point(1, 7), new Point(1, 6)));//king can kill the queen to get out of check
	}
	
	public void testInCheckMate(){
		createMock1();
		Player.state = mockState1;//testing inCheck for mockState1
		assertFalse(player1.inCheckMate());//the king is not in checkmate because he can get the queen
	}

}
