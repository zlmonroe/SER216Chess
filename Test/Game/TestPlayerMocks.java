package Game;

import java.awt.Point;
import java.util.LinkedList;

import Game.Pieces.*;
import static org.mockito.Mockito.*;

public class TestPlayerMocks {
	
	Piece mockKing = mock(King.class); //creates mock king
	Piece mockQueen = mock(Queen.class); //creates mock queen;
	Piece mockBiship = mock(Bishop.class);

	/*This creates the base mock board state
	 * It creates a mock board with a mock black king 
	 * at b7 and a mock white queen at b6 
	 */
	private BoardState createMockBase(){
		BoardState tmpState = mock(BoardState.class);
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
		
		when(tmpState.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(tmpState.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0)); //when we ask for the piece at the queen's point, we should get the queen
		when(tmpState.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
		when(tmpState.getPieces(true)).thenReturn(whitePieces); //when we ask for white pieces, we should get white pieces
		return tmpState;
	}
	
	private BoardState createMock1(){
		BoardState tmpState = createMockBase();
		when(tmpState.move(new Point(1, 7), new Point(1, 6))).thenReturn(mockState1x2);
		when(tmpState.move(new Point(1, 7), new Point(0, 7))).thenReturn(mockState1x3);
		return tmpState;
	}
	
	private BoardState createMock2(){
		BoardState tmpState = createMockBase();
		when(tmpState.move(new Point(1, 7), new Point(1, 6))).thenReturn(mockState1x2);
		when(tmpState.move(new Point(1, 7), new Point(0, 7))).thenReturn(mockState1x3);
		return tmpState;
	}
	
	//mock1x2 is mock1 after the king has moved to 1, 6 and killed the queen
	private void createMock1x2(){
		Point kingPoint = new Point(1, 6);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(0,5), new Point(1,7), new Point(1,5),new Point(2,5), new Point(2,6), new Point(2,7)};
		createMockKing(kingPoint, kingMoves);
		
		//Creating mock fncs for mockState1
		LinkedList<Piece> blackPieces = new LinkedList(); //linked list with all of black pieces
		blackPieces.add(mockKing);
		
		when(mockState1x2.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(mockState1x2.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
		when(mockState1x2.getPieces(true)).thenReturn(new LinkedList<Piece>()); //white player has no pieces anymore, so the list is empty
	}
	
	//mock1x3 is mock1 after the king has attempted to move to 0, 7 which is invalid
	private void createMock1x3(){
		Point kingPoint = new Point(0, 7);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,6), new Point(1,7), new Point(1,6)};
		
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
		
		when(mockState1x3.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(mockState1x3.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0)); //when we ask for the piece at the queen's point, we should get the queen
		when(mockState1x3.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
		when(mockState1x3.getPieces(true)).thenReturn(whitePieces); //when we ask for white pieces, we should get white pieces
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
	
	private void createMockBishop(Point bishPoint, Point[] tmpPoints){
		
	}
}
