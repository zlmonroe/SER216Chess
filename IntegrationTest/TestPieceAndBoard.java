import static org.junit.Assert.*;

import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Pieces.*;

public class TestPieceAndBoard {
	
	BoardState board1;
	BoardState board2;

	@Before
	public void setUp() throws Exception {
		board1 = new BoardState();
		board2 = createBoard2();
	}
	
	private BoardState createBoard2(){
		BoardState tmp = new BoardState();
		
		/*moving all white pieces up one
		 *this will delete the pawns
		 *this is only allowed because the player class is
		 *not checking if the moves are correct
		 */
		tmp = tmp.move(new Point(0, 0), new Point(0,1));
		tmp = tmp.move(new Point(1, 0), new Point(1,1));
		tmp = tmp.move(new Point(2, 0), new Point(2,1));
		tmp = tmp.move(new Point(3, 0), new Point(3,1));
		tmp = tmp.move(new Point(4, 0), new Point(4,1));
		tmp = tmp.move(new Point(5, 0), new Point(5,1));
		tmp = tmp.move(new Point(6, 0), new Point(6,1));
		tmp = tmp.move(new Point(7, 0), new Point(7,1));
		
		//moving all black pieces down one
		tmp = tmp.move(new Point(0, 7), new Point(0,6));
		tmp = tmp.move(new Point(1, 7), new Point(1,6));
		tmp = tmp.move(new Point(2, 7), new Point(2,6));
		tmp = tmp.move(new Point(3, 7), new Point(3,6));
		tmp = tmp.move(new Point(4, 7), new Point(4,6));
		tmp = tmp.move(new Point(5, 7), new Point(5,6));
		tmp = tmp.move(new Point(6, 7), new Point(6,6));
		tmp = tmp.move(new Point(7, 7), new Point(7,6));
		
		return tmp;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//testing the basic moves of the pawn
	@Test
	public void testPawnBasicMoves() {
		Piece pawnWhite = board1.getPieceAt(new Point(0,1));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		//test the pawn's ability to move two forward
		testPawnMoves2Up(pawnWhite, 0, 2, 3);//pawn should be allowed to move two, even if we choose to move it only one, as we will later
		//move on the board and test a successful move
		board1 = board1.move(new Point(0,1), new Point(0,2));
		pawnWhite = board1.getPieceAt(new Point(0,2));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		Piece pawnBlack = board1.getPieceAt(new Point(1,6));
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		//test the pawn's ability to move two forward
		testPawnMoves2Up(pawnBlack, 1, 5, 4); //pawn should be allowed to move to these spaces
		//move on the board and test a successful move
		board1 = board1.move(new Point(1,6), new Point(1,4));
		pawnBlack = board1.getPieceAt(new Point(1,4));
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		pawnWhite = board1.getPieceAt(new Point(0,2));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		testPawnMoves1(pawnWhite, 0,3);//pawn should be allowed to move only one
		//move on the board and test a successful move
		board1 = board1.move(new Point(0,2), new Point(0,3));
		
		pawnBlack = board1.getPieceAt(new Point(1,4));
		testPawnMoves1Dia(pawnBlack, 1, 0, 3); //test that the pawn is allowed to move both one forward (1,3) and one diagonal, down to the left (0,3)
		
		board1 = board1.move(new Point(1,4), new Point(0,3));
		
		pawnBlack = board1.getPieceAt(new Point(0,3)); //piece at his point should now be the black pawn
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		pawnWhite = board1.getPieceAt(new Point(2,1));//a different white piece
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		testPawnMoves2Up(pawnWhite, 2, 2, 3);
		board1 = board1.move(new Point(2,1), new Point(2,3));
		
		pawnBlack = board1.getPieceAt(new Point(2,6));//a different black piece
		testPawnMoves2Up(pawnBlack, 2, 5, 4);
		
		board1 = board1.move(new Point(2,6), new Point(2,4));
		
		pawnBlack = board1.getPieceAt(new Point(2,4));
		pawnWhite = board1.getPieceAt(new Point(2,3));
		
		testPawnMovesEmpty(pawnWhite); //neither pawn should be able to move now
		testPawnMovesEmpty(pawnBlack);
		
		pawnWhite = board1.getPieceAt(new Point(1,1));
		
		testPawnMoves2Up(pawnWhite, 1, 2, 3);
		
		board1 = board1.move(new Point(1,1), new Point(1,2));
		pawnBlack = board1.getPieceAt(new Point(0,3));
		
		testPawnMoves1Dia(pawnBlack, 0, 1, 2);//test pawn moving right diagonal
		
		board1 = board1.move(new Point(0,3), new Point(1,2));
		pawnWhite = board1.getPieceAt(new Point(7,1));
		
		testPawnMoves2Up(pawnWhite, 7, 2, 3);//I make sure to go ack and forth between black and white, as proper game logic
		
		board1 = board1.move(new Point(7,1), new Point(7,2));
		pawnBlack = board1.getPieceAt(new Point(1,2));
		
		testPawnMoves1(pawnBlack, 1, 1);
		
		board1 = board1.move(new Point(1,2), new Point(1,1));
		pawnWhite = board1.getPieceAt(new Point(7,2));
		
		testPawnMoves1(pawnWhite, 7, 3);
		
		board1 = board1.move(new Point(7,2), new Point(7,3));
		pawnBlack = board1.getPieceAt(new Point(1,1));
		
		testPawnMoves2Dia(pawnBlack, 0, 2, 0);//test for moving in both diagonal directions
	}

	/*
	 * Tests if the pawn can do basic en passant. Also tests moving forward one and two
	 */
	/*@Test
	public void testPawnEnPassant() {
		//first set of pieces will test if the pawn can do en passant
		//if the board was made correctly, this spot should contain a white pawn
		Piece pawnWhite1 = board1.getPieceAt(new Point(0,1));
		Piece pawnBlack1;
		Piece pawnBlackE;// extra black pawn for black's turn
		assertTrue(pawnWhite1.getIdentifier() == 0);
		assertTrue(pawnWhite1.isWhite());
		
		//testing move set of the first white pawn. It should be able to move to 0,2 and 0,3
		testPawnMoves2Up(pawnWhite1, 0, 2, 3);
		// move the first white pawn
		board1 = board1.move(pawnWhite1.getPosition(), new Point(0,3));
		
		pawnBlackE = board1.getPieceAt(new Point(7,6)); //Note, the pieces must be updated everytime there is a move
		testPawnMoves2Up(pawnBlackE, 7, 5, 4);
		board1 = board1.move(pawnBlackE.getPosition(), new Point(7,4));
		
		pawnWhite1 = board1.getPieceAt(new Point(0,3));//Note, the pieces must be updated everytime there is a move because  the board changes
		testPawnMoves1(pawnWhite1, 0, 4);
		board1 = board1.move(pawnWhite1.getPosition(), new Point(0,4));
		
		pawnBlack1 = board1.getPieceAt(new Point(1,6));
		//id should be for pawn and color should be black
		assertTrue(pawnBlack1.getIdentifier() == 0);
		assertFalse(pawnBlack1.isWhite());
		testPawnMoves2Up(pawnBlack1, 1, 5, 4);	
		board1 = board1.move(pawnBlackE.getPosition(), new Point(7,4));
		
		pawnWhite1 = board1.getPieceAt(new Point(0,4));
		testPawnMoves1Dia(pawnWhite1, 0, 1, 5);
		// move the first white pawn
		board1 = board1.move(pawnWhite1.getPosition(), new Point(1,5));
	}
	
	public void testWrongEnPassant(){
		
	}*/
	
	//pawn has two moves with different ys
	private void testPawnMoves2Up(Piece pawn, int x, int y1, int y2){
		LinkedList<Point> movesL = new LinkedList<Point>();
		movesL.add(new Point(x, y1));
		movesL.add(new Point(x, y2));
		HashSet<Point> moves = new HashSet(movesL);
		
		assertEquals(new HashSet(pawn.getMoves()), moves);
	}
	//pawn has one diagonal move
	private void testPawnMoves1Dia(Piece pawn, int x1, int x2, int y){
		LinkedList<Point> movesL = new LinkedList<Point>();
		movesL.add(new Point(x1, y));
		movesL.add(new Point(x2, y));
		HashSet<Point> moves = new HashSet(movesL);
		
		assertEquals(new HashSet(pawn.getMoves()), moves);
	}
	
	//pawn has two diagonal moves only
	private void testPawnMoves2Dia(Piece pawn, int x1, int x2,  int y){
		LinkedList<Point> movesL = new LinkedList<Point>();
		movesL.add(new Point(x1, y));
		movesL.add(new Point(x2, y));
		HashSet<Point> moves = new HashSet(movesL);
		
		assertEquals(new HashSet(pawn.getMoves()), moves);
	}
	//pawn has one move
	private void testPawnMoves1(Piece pawn, int x, int y){
		LinkedList<Point> movesL = new LinkedList<Point>();
		movesL.add(new Point(x, y));
		HashSet<Point> moves = new HashSet(movesL);
		
		assertEquals(new HashSet(pawn.getMoves()), moves);
	}
	
	private void testPawnMovesEmpty(Piece pawn){
		assertTrue(pawn.getMoves().isEmpty());
	}

}
