import static org.junit.Assert.*;

import java.awt.Point;
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

	@Test
	public void testPawnAndBoard() {
		//first set of pieces will test if the pawn can do en passant
		//if the board was made correctly, this spot should contain a white pawn
		Piece pawnWhite1 = board1.getPieceAt(new Point(0,1));
		//if the board was made correctly, this spot should contain a black pawn
		Piece pawnBlack1;
		
		//Tests for the first two pawns**************************************
		//id should be for pawn and color should be white
		assertTrue(pawnWhite1.getIdentifier() == 0);
		assertTrue(pawnWhite1.isWhite());
		
		//second set of pieces will test if the pawn can not do en passant because the pawn did not move two
		Piece pawnWhite2 = board1.getPieceAt(new Point(3,1));
		Piece pawnBlack2 = board1.getPieceAt(new Point(4,6));
		
		//third set of pieces will test if the pawn can not do en passant because too many turns have passed
		Piece pawnWhite3 = board1.getPieceAt(new Point(6,1));
		Piece pawnBlack3;
		
		//testing move set of the first white pawn. It should be able to move to 0,2 and 0,3
		testPawnMoves2(pawnWhite1, 0, 2, 3);
		// move the first white pawn
		board1 = board1.move(pawnWhite1.getPosition(), new Point(0,3));
		pawnBlack3 = board1.getPieceAt(new Point(7,6)); //Note, the pieces must be updated everytime there is a move
		
		testPawnMoves2(pawnBlack3, 7, 5, 4);
		
		board1 = board1.move(pawnBlack3.getPosition(), new Point(7,4));
		pawnWhite1 = board1.getPieceAt(new Point(0,3));
		
		testPawnMoves1(pawnWhite1, 0, 4);
		
		board1 = board1.move(pawnWhite1.getPosition(), new Point(0,4));
		pawnBlack1 = board1.getPieceAt(new Point(1,6));
		//id should be for pawn and color should be black
		assertTrue(pawnBlack1.getIdentifier() == 0);
		assertFalse(pawnBlack1.isWhite());
		
		testPawnMoves2(pawnBlack1, 1, 5, 4);		
	}
	
	private void testPawnMoves2(Piece pawn, int x, int y1, int y2){
		LinkedList<Point> moves = pawn.getMoves();
		//pawnWhite should be able to move to these two points, and no others
		assertTrue(moves.contains(new Point(x,y1)));
		assertTrue(moves.contains(new Point(x,y2)));
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(!((i == x && j == y1) || (i == x && j == y2))){
					assertFalse(moves.contains(new Point(i,j)));
				}
			}
		}
	}
	
	private void testPawnMoves1(Piece pawn, int x, int y){
		LinkedList<Point> moves = pawn.getMoves();
		//pawnWhite should be able to move to these two points, and no others
		assertTrue(moves.contains(new Point(x,y)));
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(!((i == x && j == y))){
					assertFalse(moves.contains(new Point(i,j)));
				}
			}
		}
	}

}
