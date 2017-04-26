import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Game.BoardState;
import Game.Player;
import Game.Pieces.Piece;

public class TestPlayerAndBoard {
	
	Player player1;
	Player player2;
	
	BoardState board1;
	BoardState board2;

	@Before
	public void setUp() throws Exception {
		player1 = new Player(true);
		player2 = new Player(false);
		
		board1 = new BoardState();
		Player.state = board1;
		
		board2 = new BoardState();
		Piece king = board2.getPieceAt(new Point(4,7));
		for(int x = 0; x < 7 ; x++){
			for(int y = 7; y > 5 ; y--){
				board2 = board2.move(king.getPosition(), new Point(x, y));
				king = board2.getPieceAt(new Point(x, y));
			}
		}
		
		Piece pawn = board2.getPieceAt(new Point(4,1));
		for(int x = 0; x < 7 ; x++){
			for(int y = 1; y > -1 ; y--){
				board2 = board2.move(pawn.getPosition(), new Point(x, y));
				pawn = board2.getPieceAt(new Point(x, y));
			}
		}
		
		board2 = board2.move(king.getPosition(), new Point(4,7));
		board2 = board2.move(pawn.getPosition(), new Point(4,6));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPawnMoves() {
		assertTrue(player1.move(new Point(0,1), new Point(0,2)));
		assertTrue(player2.move(new Point(1,6), new Point(1,4)));
		
		Piece pawnWhite = Player.state.getPieceAt(new Point(0, 2));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		Piece pawnBlack = Player.state.getPieceAt(new Point(1,4));
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		assertFalse(player1.move(new Point(0,2), new Point(0,4)));//cannot move two after first turn
		assertFalse(player2.move(new Point(1,4), new Point(1,5)));//cannot move back
		
		assertTrue(player1.move(new Point(0,2), new Point(0,3)));
		assertTrue(player2.move(new Point(0,6), new Point(0,5)));
		
		pawnWhite = Player.state.getPieceAt(new Point(0, 3));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		pawnBlack = Player.state.getPieceAt(new Point(0,5));
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		assertTrue(player1.move(new Point(0,3), new Point(1,4)));
		assertFalse(player1.move(new Point(1,4), new Point(2,5)));//cannot move diagonally if no piece is there
		
		pawnWhite = Player.state.getPieceAt(new Point(1, 4));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
	
		assertTrue(player2.move(new Point(0,5), new Point(1,4)));
		
		pawnBlack = Player.state.getPieceAt(new Point(1,4));
		assertTrue(pawnBlack.getIdentifier() == 0);
		assertFalse(pawnBlack.isWhite());
		
		assertTrue(player1.move(new Point(6,1), new Point(6,3)));
		assertTrue(player2.move(new Point(6,6), new Point(6,4)));
		
		assertFalse(player1.move(new Point(6,4), new Point(6,3)));//pawns cant move forward with piece in way
		assertFalse(player2.move(new Point(6,3), new Point(6,4)));
		
	}
	
	@Test 
	public void testPawnCheckAndCheckMate() {
		Player.state = board2;
		
		//black king at 4,7 and white pawn at 4,6. in check and in check mate are false
		Piece kingBlack = Player.state.getPieceAt(new Point(4,7));
		assertTrue(kingBlack .getIdentifier() == 5);
		assertFalse(kingBlack .isWhite());
		
		Piece pawnWhite = Player.state.getPieceAt(new Point(4,6));
		assertTrue(pawnWhite.getIdentifier() == 0);
		assertTrue(pawnWhite.isWhite());
		
		assertFalse(player2.inCheck());
		assertFalse(player2.inCheckMate());
		
		board2 = board2.move(pawnWhite.getPosition(), new Point(5,6));
		Player.state = board2;
		
		assertTrue(player2.inCheck());
		assertFalse(player2.inCheckMate());
		assertTrue(player2.move(new Point(4,7), new Point(5,6)));
	}
}
