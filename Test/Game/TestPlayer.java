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
	TestPlayerMocks mocks;

	Player player1;
	@Before
	public void setUp() throws Exception {
		player1 = new Player(false);
		mocks = new TestPlayerMocks();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInCheck() {
		Player.state = mocks.mockState1;//testing inCheck for mockState1
		assertTrue(player1.inCheck(mocks.mockState1)); //The king is in check if the queen can attack him
		Player.state = mocks.mockState2;
		assertTrue(player1.inCheck(mocks.mockState2)); //This will test to see if a white bishop behind the white queen will pu
		Player.state = mocks.mockState4;
		assertFalse(player1.inCheck(mocks.mockState4)); //This will test to see if a white bishop behind the white queen will pu
	}
	
	@Test
	public void testMove(){		
		Player.state = mocks.mockState1;//testing move for mockState1
		assertFalse(player1.move(new Point(1, 7), new Point(0, 7)));//king cannot move to a place that will not get him out of check
		Player.state = mocks.mockState1;
		assertTrue(player1.move(new Point(1, 7), new Point(1, 6)));//king can kill the queen to get out of check
		Player.state = mocks.mockState1;
		assertFalse(player1.move(new Point(1, 7), new Point(1, 0)));
		
		Player.state = mocks.mockState3;
		assertFalse(player1.move(new Point(3, 3), new Point(3, 2)));
		Player.state = mocks.mockState3;
		assertTrue(player1.move(new Point(3, 3), new Point(3, 4)));
	}
	@Test	
	public void testInCheckMate(){
		Player.state = mocks.mockState1;//testing inCheck for mockState1
		assertFalse(player1.inCheckMate());//the king is not in checkmate because he can get the queen
		Player.state = mocks.mockState2;
		assertTrue(player1.inCheckMate()); //This will test to see if a white bishop behind the white queen will pu
		Player.state = mocks.mockState3;
		assertFalse(player1.inCheckMate());
		Player.state = mocks.mockState4;
		assertFalse(player1.inCheckMate()); //This will test to see if a white bishop behind the white queen will pu
	}

	
}
