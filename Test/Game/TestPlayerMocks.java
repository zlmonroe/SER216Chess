package Game;

import java.awt.Point;
import java.util.LinkedList;

import Game.Pieces.*;
import static org.mockito.Mockito.*;

public class TestPlayerMocks {

	public BoardState mockState1;
	public BoardState mockState1x2;
	public BoardState mockState1x3;
	public BoardState mockState2;
	public BoardState mockState2x1;
	private int countBases;
	
	LinkedList<Piece> blackPieces = new LinkedList();
	LinkedList<Piece> whitePieces = new LinkedList();
	
	public TestPlayerMocks(){
		countBases = 0;
		mockState1x2 = createMock1x2();
		mockState1x3 = createMock1x3();
		mockState1 = createMock1(createMockBase());
		mockState2 = createMock2(createMockBase());
		mockState2x1 = createMock2x1();
	}

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
		
		Piece mockKing = createMockKing(kingPoint, kingMoves);
		Piece mockQueen = createMockQueen(queenPoint, queenMoves);
		
		//Creating mock fncs for mockState1ds
		//f
		if(countBases == 0){
			blackPieces.add(mockKing);
			whitePieces.add(mockQueen);
		}
		
		when(tmpState.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(tmpState.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0)); //when we ask for the piece at the queen's point, we should get the queen
		when(tmpState.getPieces(false)).thenReturn(blackPieces); //when we ask for black pieces, we should get black pieces
		when(tmpState.getPieces(true)).thenReturn(whitePieces); //when we ask for white pieces, we should get white pieces
		countBases++;
		return tmpState;
	}
	
	private BoardState createMock1(BoardState tmpState){
		when(tmpState.move(new Point(1, 7), new Point(1, 6))).thenReturn(mockState1x2);
		when(tmpState.move(new Point(1, 7), new Point(0, 7))).thenReturn(mockState1x3);
		return tmpState;
	}
	
	//mock1x2 is mock1 after the king has moved to 1, 6 and killed the queen
		private BoardState createMock1x2(){
			BoardState tmpState = mock(BoardState.class);
			Point kingPoint = new Point(1, 6);
			//list of all moves possible by the king (according to chess rules)
			Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(0,5), new Point(1,7), new Point(1,5),new Point(2,5), new Point(2,6), new Point(2,7)};
			Piece mockKing = createMockKing(kingPoint, kingMoves);
			//Creating mock fncs for mockState1
			LinkedList<Piece> myBlackPieces = new LinkedList(); //linked list with all of black pieces
			myBlackPieces.add(mockKing);
			
			when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
			when(tmpState.getPieces(false)).thenReturn(myBlackPieces); //when we ask for black pieces, we should get black pieces
			when(tmpState.getPieces(true)).thenReturn(new LinkedList<Piece>()); //white player has no pieces anymore, so the list is empty
			return tmpState;
		}
		
		//mock1x3 is mock1 after the king has attempted to move to 0, 7 which is invalid
		private BoardState createMock1x3(){
			BoardState tmpState = mock(BoardState.class);
			Point kingPoint = new Point(0, 7);
			//list of all moves possible by the king (according to chess rules)
			Point[] kingMoves = {new Point(0,6), new Point(1,7), new Point(1,6)};
			
			Point queenPoint = new Point(1, 6);	
			//list of all relevant moves possible by the queen (according to chess rules)
			Point[] queenMoves = {new Point(0,7), new Point(0,6), new Point(1,6), new Point(1,7), new Point(2,6), new Point(2,7)};
			
			Piece mockKing = createMockKing(kingPoint, kingMoves);
			Piece mockQueen = createMockQueen(queenPoint, queenMoves);
			
			//Creating mock fncs for mockState1
			LinkedList<Piece> myBlackPieces = new LinkedList(); //linked list with all of black pieces
			myBlackPieces.add(mockKing);
			LinkedList<Piece> myWhitePieces = new LinkedList(); //linked list with all of white pieces
			myWhitePieces.add(mockQueen);
			
			when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
			when(tmpState.getPieceAt(queenPoint)).thenReturn(myWhitePieces.get(0)); //when we ask for the piece at the queen's point, we should get the queen
			when(tmpState.getPieces(false)).thenReturn(myBlackPieces); //when we ask for black pieces, we should get black pieces
			when(tmpState.getPieces(true)).thenReturn(myWhitePieces); //when we ask for white pieces, we should get white pieces
			return tmpState;
		}
	
	private BoardState createMock2(BoardState tmpState){
		LinkedList<Piece> myWhitePieces = new LinkedList();
		Point[] bishopMoves = {new Point(4, 3), new Point(4, 3), new Point(3, 4), new Point(2, 5), new Point(6,1)};
		Point bishopPoint = new Point(5, 2);
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		myWhitePieces.add(whitePieces.get(0));
		myWhitePieces.add(mockBishop);
		
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces);
		when(tmpState.move(new Point(1, 7), new Point(1, 6))).thenReturn(mockState2x1);
		return tmpState;
	}
	
	private BoardState createMock2x1(){
		BoardState tmpState = mock(BoardState.class);
		Point kingPoint = new Point(1, 6);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(0,5), new Point(1,7), new Point(1,5),new Point(2,5), new Point(2,6), new Point(2,7)};
		Piece mockKing = createMockKing(kingPoint, kingMoves);
		//Creating mock fncs for mockState1
		LinkedList<Piece> myBlackPieces = new LinkedList(); //linked list with all of black pieces
		myBlackPieces.add(mockKing);
		
		Point[] bishopMoves = {new Point(4, 3), new Point(4, 3), new Point(3, 4), new Point(2, 5), new Point(6,1)};
		Point bishopPoint = new Point(5, 2);
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		LinkedList<Piece> myWhitePieces = new LinkedList();
		myWhitePieces.add(mockBishop);
		
		when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); //when we ask for the piece at the king's point, we should get the king
		when(tmpState.getPieces(false)).thenReturn(myBlackPieces);
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces);//when we ask for black pieces, we should get black pieces
		return tmpState;
	}
	
	private King createMockKing(Point kingPoint, Point[] tmpPoints){
		King tmpPiece= mock(King.class);
		 //this is the point, b7, that the king is at
		LinkedList<Point> kingMoves = new LinkedList();
		for(Point p: tmpPoints){
			kingMoves.add(p);
		}
		when(tmpPiece.isWhite()).thenReturn(false); //the king is black
		when(tmpPiece.getPosition()).thenReturn(kingPoint); //the kings position should return a point 1, 7 (b7)
		when(tmpPiece.getIdentifier()).thenReturn(5); //the king should return the number that identifies it as a king
		when(tmpPiece.getMoves()).thenReturn(kingMoves);
		return tmpPiece;
	}
	
	private Queen createMockQueen(Point queenPoint, Point[] tmpPoints){
		Queen tmpPiece= mock(Queen.class);
		 //this is the point, b6, that the queen is at
		LinkedList<Point> queenMoves = new LinkedList();
		//only includes points that the king can move to
		for(Point p: tmpPoints){
			queenMoves.add(p);
		}
		when(tmpPiece.isWhite()).thenReturn(true); //the queen is white
		when(tmpPiece.getPosition()).thenReturn(queenPoint); //the queens position should return a point 1, 6 (b6)
		when(tmpPiece.getMoves()).thenReturn(queenMoves);
		when(tmpPiece.canMove(new Point(1, 7))).thenReturn(true);// the queen has the ability to move forward, as is chess rules
		return tmpPiece;
	}
	
	private Bishop createMockBishop(Point bishPoint, Point[] tmpPoints){
		Bishop tmpPiece= mock(Bishop.class);
		 //this is the point, b6, that the Bishop is at
		LinkedList<Point> bishopMoves = new LinkedList();
		//only includes points that the king can move to
		for(Point p: tmpPoints){
			bishopMoves.add(p);
		}
		when(tmpPiece.isWhite()).thenReturn(true); //the Bishop is white
		when(tmpPiece.getPosition()).thenReturn(bishPoint); //the Bishops position should return a point 1, 6 (b6)
		when(tmpPiece.getMoves()).thenReturn(bishopMoves);
		when(tmpPiece.canMove(new Point(1, 7))).thenReturn(true);// the Bishop has the ability to move forward, as is chess rules
		return tmpPiece;
	}
}
