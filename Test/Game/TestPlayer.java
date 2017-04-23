package Game;

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
	BoardState mockState1x2;
	BoardState mockState1x3;
	BoardState mockState2;
	BoardState mockState2x1;
	

	Player player1;
	@Before
	public void setUp() throws Exception {
		player1 = new Player(false);
		
		mockState1 = createMock1();//creates mock board
		mockState1x2 = mock(BoardState.class);//creates mock board
		mockState1x3 = mock(BoardState.class);//creates mock board
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInCheck() {
		Player.state = mockState1;//testing inCheck for mockState1
		assertTrue(player1.inCheck(mockState1)); //The king is in check if the queen can attack him
	}
	
	@Test
	public void testMove(){
		createMock1x2();
		createMock1x3();
		
		Player.state = mockState1;//testing move for mockState1
		assertFalse(player1.move(new Point(1, 7), new Point(0, 7)));//king cannot move to a place that will not get him out of check
		assertTrue(player1.move(new Point(1, 7), new Point(1, 6)));//king can kill the queen to get out of check
	}
	@Test	
	public void testInCheckMate(){
		createMock1x2();
		createMock1x3();
		Player.state = mockState1;//testing inCheck for mockState1
		assertFalse(player1.inCheckMate());//the king is not in checkmate because he can get the queen
	}

	
}
