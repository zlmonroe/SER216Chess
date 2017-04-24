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
	public BoardState mockState2x2;
	public BoardState mockState3;
	public BoardState mockState3x2;
	public BoardState mockState3x3;
	private int countBases;
	
	//these lists are instance varibles so that multiple mocks can have access to them
	LinkedList<Piece> blackPieces = new LinkedList();
	LinkedList<Piece> whitePieces = new LinkedList();
	
	public TestPlayerMocks(){
		countBases = 0;
		/*Mock state group 1 tests if the king can get out of check by killing
		 * the attacking piece
		 */
		mockState1x2 = createMock1x2();//fnc begins at line 89
		mockState1x3 = createMock1x3();//fnc begins at line 113
		mockState1 = createMock1(createMockBase());//fnc begins at line 32 (createMockBase) and 72 (createMock1)
		
		/*Mock state group 2 tests a situation where the king is in checkmate
		 */
		mockState2x2 = createMock2x2();//fnc begins at line 162
		mockState2 = createMock2(createMockBase());//fnc begins at line 32 (createMockBase) and 149 (createMock2)
		
		/*Mock state group 3 tests a situation where the king can get out of check if
		 * a piece moves in front off an attacking piece
		 */
		mockState3x2 = createMock3x2();//fnc begins at line 260
		mockState3x3 = createMock3x3();
		mockState3 = createMock3();//fnc begins at line 32 (createMockBase) and 224 (createMock3)
	}

	/*This creates the base mock board state
	 * It will b used by other states to avoid repeat code
	 * It creates a mock board with a mock black king 
	 * at b7 and a mock white queen at b6 
	 */
	private BoardState createMockBase(){
		BoardState tmpState = mock(BoardState.class);
		//point the king is at
		Point kingPoint = new Point(1, 7);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(1,6), new Point(2,6), new Point(2,7)};
		//Point the queen is at
		Point queenPoint = new Point(1, 6);	
		//list of all relevant moves possible by the queen (according to chess rules)
		Point[] queenMoves = {new Point(0,7), new Point(0,6), new Point(1,6), new Point(1,7), new Point(2,6), new Point(2,7)};			//make sure we dont add these pieces twice

		if(countBases==0){
			King mockKing = createMockKing(kingPoint, kingMoves);
			Queen mockQueen = createMockQueen(queenPoint, queenMoves);
				
			blackPieces.add(mockKing);
			whitePieces.add(mockQueen);
		}
		
		//mock functions for make state
		//when we ask for the piece at the king's point, we should get the king
		when(tmpState.getPieceAt(kingPoint)).thenReturn(blackPieces.get(0)); 
		//when we ask for the piece at the queen's point, we should get the queen
		when(tmpState.getPieceAt(queenPoint)).thenReturn(whitePieces.get(0)); 
		//when we ask for black pieces, we should get black pieces
		when(tmpState.getPieces(false)).thenReturn(blackPieces); 
		//when we ask for white pieces, we should get white pieces
		when(tmpState.getPieces(true)).thenReturn(whitePieces); 
		countBases++;
		//return this base state to be used by other states
		return tmpState;
	}
	
	/*
	 * Mock1 is a state with the king at 1,7 and the queen at 1, 6. 
	 * The king is in check and can only get out by killing the queen.
	 * We will test to moves fo the king, one is invalid because the king will stay in check
	 * and one is valid because he will be out of check
	 */
	private BoardState createMock1(BoardState tmpState){
		/*When we ask for the board state after we move the king (at 1, 7) to 1, 6
		 * we should get that board state, represent by mockState1x2
		 */
		when(tmpState.move(new Point(1, 7), new Point(1, 6))).thenReturn(mockState1x2);
		/*When we ask for the board state after we move the king (at 1, 7) to 0, 7
		 * we should get that board state, represent by mockState1x3
		 */
		when(tmpState.move(new Point(1, 7), new Point(0, 7))).thenReturn(mockState1x3);
		return tmpState;
	}
	
	//mock1x2 is mock1 after the king has moved to 1, 6 and killed the queen
		private BoardState createMock1x2(){
			BoardState tmpState = mock(BoardState.class);
			//king's new position
			Point kingPoint = new Point(1, 6);
			
			//list of all moves possible by the king (according to chess rules)
			Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(0,5), new Point(1,7), new Point(1,5),new Point(2,5), new Point(2,6), new Point(2,7)};
			Piece mockKing = createMockKing(kingPoint, kingMoves);
			
			//linked list with all of black pieces (updated for this specific board)
			LinkedList<Piece> myBlackPieces = new LinkedList(); 
			myBlackPieces.add(mockKing);
			
			//Creating mock fncs
			//when we ask for the piece at the king's point, we should get the kin
			when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); 
			//when we ask for black pieces, we should get black pieces
			when(tmpState.getPieces(false)).thenReturn(myBlackPieces); 
			//white player has no pieces anymore, so the list is empty
			when(tmpState.getPieces(true)).thenReturn(new LinkedList<Piece>()); 
			return tmpState;
		}
		
		//mock1x3 is mock1 after the king has attempted to move to 0, 7 which is invalid because the king will stay in check
		private BoardState createMock1x3(){
			BoardState tmpState = mock(BoardState.class);
			//king's new position
			Point kingPoint = new Point(0, 7);
			//list of all moves possible by the king (according to chess rules)
			Point[] kingMoves = {new Point(0,6), new Point(1,7), new Point(1,6)};
			
			Piece mockKing = createMockKing(kingPoint, kingMoves);
			
			Point queenPoint = new Point(1, 6);	
			//list of all relevant moves possible by the queen (according to chess rules)
			Point[] queenMoves = {new Point(0,7), new Point(0,6), new Point(1,6), new Point(1,7), new Point(2,6), new Point(2,7)};
				
			Queen mockQueen = createMockQueen(queenPoint, queenMoves);
			
			//linked list with all of black pieces
			LinkedList<Piece> myBlackPieces = new LinkedList(); 
			myBlackPieces.add(mockKing);
			//linked list with all of white pieces
			LinkedList<Piece> myWhitePieces = new LinkedList(); 
			myWhitePieces.add(mockQueen);
			
			//Creating mock fncs 
			//when we ask for the piece at the king's point, we should get the king
			when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); 
			//when we ask for the piece at the queen's point, we should get the queen
			when(tmpState.getPieceAt(queenPoint)).thenReturn(myWhitePieces.get(0)); 
			//when we ask for black pieces, we should get black pieces
			when(tmpState.getPieces(false)).thenReturn(myBlackPieces);
			//when we ask for white pieces, we should get white pieces
			when(tmpState.getPieces(true)).thenReturn(myWhitePieces); 
			return tmpState;
		}
		
		/*
		 * Mock2 is a state with the king at 1,7, and the other players queen at 1, 6 and bishop at 5, 2. 
		 * The king is in checkmate 
		 */
	private BoardState createMock2(BoardState tmpState){
		//linked list with all of white pieces. This mock has an extra white piece
		LinkedList<Piece> myWhitePieces = new LinkedList();
		
		//list of all relevant moves possible by the bishop (according to chess rules)
		//(Relevant moves is defined as the moves that the bishop can make around the king to kill him)
		Point[] bishopMoves = {new Point(4, 3), new Point(4, 3), new Point(3, 4), new Point(2, 5), new Point(1,6)};
		//bshops position
		Point bishopPoint = new Point(5, 2);
		//creating a bishop piece
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		
		//add the original queen and new bishop to our list
		myWhitePieces.add(whitePieces.get(0));
		myWhitePieces.add(mockBishop);
		
		//Creating mock fncs 
		//when we ask for white pieces, we should get our white pieces
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces);
		/*When we ask for the board state after we move the king (at 1, 7) to 1, 6
		 * we should get that board state, represent by mockState2x2
		 */
		when(tmpState.move(new Point(1, 7), new Point(1, 6))).thenReturn(mockState2x2);
		return tmpState;
	}
	//mock2x2 is mock2 after the king has attempted to move to 1, 6 and kill the quee, which is invalid because he will still be in check
	private BoardState createMock2x2(){
		BoardState tmpState = mock(BoardState.class);
		Point kingPoint = new Point(1, 6);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,7), new Point(0,6), new Point(0,5), new Point(1,7), new Point(1,5),new Point(2,5), new Point(2,6), new Point(2,7)};
		Piece mockKing = createMockKing(kingPoint, kingMoves);
		//Creating mock fncs for mockState1
		LinkedList<Piece> myBlackPieces = new LinkedList(); //linked list with all of black pieces
		myBlackPieces.add(mockKing);
		
		//list of all relevant moves possible by the bishop (according to chess rules)
		//(Relevant moves is defined as the moves that the bishop can make around the king to kill him)
		Point[] bishopMoves = {new Point(4, 3), new Point(4, 3), new Point(3, 4), new Point(2, 5), new Point(1,6)};
		//bshops position
		Point bishopPoint = new Point(5, 2);
		//creating a bishop piece
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		//add the bishop to our list (the queen is dead)
		LinkedList<Piece> myWhitePieces = new LinkedList();
		myWhitePieces.add(mockBishop);
		
		//when we ask for the piece at the king's point, we should get the king
		when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); 
		//when we ask for black pieces, we should get black pieces
		when(tmpState.getPieces(false)).thenReturn(myBlackPieces);
		//when we ask for white pieces, we should get white pieces
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces);
		
		return tmpState;
	}
	/*
	 * Mock3 is a state with the king at 0,7, ad rook at 3,3 and the other players queen at 2, 6 and bishop at 5, 2. 
	 * The king is in check, and can only be removed from check if the rook moves in front of the bishop at
	 * 4,3 or 3,4
	 */
	private BoardState createMock3(){
		BoardState tmpState = mock(BoardState.class);
		//king's new position
		Point kingPoint = new Point(0, 7);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,6), new Point(1,7), new Point(1,6)};
		
		Piece mockKing = createMockKing(kingPoint, kingMoves);
		
		Point queenPoint = new Point(2, 6);	
		//list of all relevant moves possible by the queen (according to chess rules)
		Point[] queenMoves = {new Point(1,7), new Point(0,6), new Point(1,6), new Point(2,7)};
			
		Queen mockQueen = createMockQueen(queenPoint, queenMoves);
		
		//linked list with all of black pieces
		LinkedList<Piece> myBlackPieces = new LinkedList(); 
		myBlackPieces.add(mockKing);
		//linked list with all of white pieces
		LinkedList<Piece> myWhitePieces = new LinkedList(); 
		myWhitePieces.add(mockQueen);
		
		//list of all relevant moves possible by the bishop (according to chess rules)
		//(Relevant moves is defined as the moves that the bishop can make around the king to kill him)
		Point[] bishopMoves = {new Point(4, 3), new Point(3, 4), new Point(2, 5), new Point(1,6), new Point(0, 7)};
		//bshops position
		Point bishopPoint = new Point(5, 2);
		//creating a bishop piece
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		myWhitePieces.add(mockBishop);
		
		//list of all relevant moves possible by the rook (according to chess rules)
		//(Relevant moves is defined as the moves that the rook can make to get the king out of check plus
		//a few moves that will not get the king out of check)
		Point[] rookMoves = {new Point(4, 3), new Point(3, 4), new Point(3, 2), new Point(2,3)};
		//rooks position
		Point rookPoint = new Point(3, 3);
		//creating a bishop piece
		Rook mockRook = createMockRook(rookPoint, rookMoves);
		myBlackPieces.add(mockRook);
		
		//Creating mock fncs 
		//when we ask for the piece at the king's point, we should get the king
		when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0)); 
		//when we ask for the piece at the queen's point, we should get the queen
		when(tmpState.getPieceAt(rookPoint)).thenReturn(myBlackPieces.get(1)); 
		//when we ask for black pieces, we should get black pieces
		when(tmpState.getPieces(false)).thenReturn(myBlackPieces);
		//when we ask for white pieces, we should get white pieces
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces); 
		/*When we ask for the board state after we move the rook (at 3, 3) to 3, 2
		 * we should get that board state, represent by mockState3x2
		 */
		when(tmpState.move(new Point(3, 3), new Point(3, 2))).thenReturn(mockState3x2);
		/*When we ask for the board state after we move the rook (at 3, 3) to 3, 4
		 * we should get that board state, represent by mockState3x3
		 */
		when(tmpState.move(new Point(3, 3), new Point(3, 4))).thenReturn(mockState3x3);
		return tmpState;
	}
	
	//Mock3x2 is mock 3 after the rook has attempted to move from 3, 3 to 3, 2, an invalid move because the king will stay in check
	private BoardState createMock3x2(){
		BoardState tmpState = mock(BoardState.class);
		//king's new position
		Point kingPoint = new Point(0, 7);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,6), new Point(1,7), new Point(1,6)};
		
		Piece mockKing = createMockKing(kingPoint, kingMoves);
		
		Point queenPoint = new Point(2, 6);	
		//list of all relevant moves possible by the queen (according to chess rules)
		Point[] queenMoves = {new Point(1,7), new Point(0,6), new Point(1,6), new Point(1,7), new Point(2,7)};
			
		Queen mockQueen = createMockQueen(queenPoint, queenMoves);
		
		//linked list with all of black pieces
		LinkedList<Piece> myBlackPieces = new LinkedList(); 
		myBlackPieces.add(mockKing);
		//linked list with all of white pieces
		LinkedList<Piece> myWhitePieces = new LinkedList(); 
		myWhitePieces.add(mockQueen);
		
		//list of all relevant moves possible by the bishop (according to chess rules)
		//(Relevant moves is defined as the moves that the bishop can make around the king to kill him)
		Point[] bishopMoves = {new Point(4, 3), new Point(3, 4), new Point(2, 5), new Point(1,6), new Point(0, 7)};
		//bshops position
		Point bishopPoint = new Point(5, 2);
		//creating a bishop piece
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		
		//list of all relevant moves possible by the rook (according to chess rules)
		//(Relevant moves is defined as the moves that the rook can make to get the king out of check plus
		//a few moves that will not get the king out of check)
		Point[] rookMoves = {new Point(4, 3), new Point(3, 4), new Point(3, 5), new Point(3,3)};
		//rooks position
		Point rookPoint = new Point(3, 2);
		//creating a bishop piece
		Rook mockRook = createMockRook(rookPoint, rookMoves);
		myWhitePieces.add(mockBishop);
		myBlackPieces.add(mockRook);
		
		//Creating mock fncs 
		//when we ask for white pieces, we should get our white pieces
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces);
		when(tmpState.getPieces(false)).thenReturn(myBlackPieces);
		//make sure we get the right piece when it is asked for
		when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0));
		when(tmpState.getPieceAt(bishopPoint)).thenReturn(myWhitePieces.get(1));
		when(tmpState.getPieceAt(rookPoint)).thenReturn(myBlackPieces.get(1));
		return tmpState;
		
	}
	
	//Mock3x3 is mock 3 after the rook has attempted to move from 3, 3 to 3, 4, a valid move because the king will be out of check
	private BoardState createMock3x3(){
		BoardState tmpState = mock(BoardState.class);
		//king's position
		Point kingPoint = new Point(0, 7);
		//list of all moves possible by the king (according to chess rules)
		Point[] kingMoves = {new Point(0,6), new Point(1,7), new Point(1,6)};
		
		Piece mockKing = createMockKing(kingPoint, kingMoves);
		
		Point queenPoint = new Point(2, 6);	
		//list of all relevant moves possible by the queen (according to chess rules)
		Point[] queenMoves = {new Point(1,7), new Point(0,6), new Point(1,6), new Point(1,7), new Point(2,7)};
			
		Queen mockQueen = createMockQueen(queenPoint, queenMoves);
		
		//linked list with all of black pieces
		LinkedList<Piece> myBlackPieces = new LinkedList(); 
		myBlackPieces.add(mockKing);
		//linked list with all of white pieces
		LinkedList<Piece> myWhitePieces = new LinkedList(); 
		myWhitePieces.add(mockQueen);
		
		
		//list of all relevant moves possible by the bishop (according to chess rules)
		//(Relevant moves is defined as the moves that the bishop can make around the king to kill him)
		Point[] bishopMoves = {new Point(4, 3), new Point(3, 4)};
		//bshops position
		Point bishopPoint = new Point(5, 2);
		//creating a bishop piece
		Bishop mockBishop = createMockBishop(bishopPoint, bishopMoves);
		
		//list of all relevant moves possible by the rook (according to chess rules)
		//(Relevant moves is defined as the moves that the rook can make to get the king out of check plus
		//a few moves that will not get the king out of check)
		Point[] rookMoves = {new Point(4, 2), new Point(3, 2), new Point(3, 5), new Point(3,3), new Point(4,4)};
		//rooks position
		Point rookPoint = new Point(3, 4);
		//creating a bishop piece
		Rook mockRook = createMockRook(rookPoint, rookMoves);
		myWhitePieces.add(mockBishop);
		myBlackPieces.add(mockRook);
		
		//Creating mock fncs 
		//when we ask for pieces, we should get our pieces
		when(tmpState.getPieces(true)).thenReturn(myWhitePieces);
		when(tmpState.getPieces(false)).thenReturn(myBlackPieces);
		//make sure we get the right piece when it is asked for
		when(tmpState.getPieceAt(kingPoint)).thenReturn(myBlackPieces.get(0));
		when(tmpState.getPieceAt(bishopPoint)).thenReturn(myWhitePieces.get(1));
		when(tmpState.getPieceAt(rookPoint)).thenReturn(myBlackPieces.get(1));
		return tmpState;
		
	}
	
	private King createMockKing(Point kingPoint, Point[] tmpPoints){
		King tmpPiece= mock(King.class);
		LinkedList<Point> kingMoves = new LinkedList();
		for(Point p: tmpPoints){
			kingMoves.add(p);
		}
		
		//the king is black
		when(tmpPiece.isWhite()).thenReturn(false); 
		//the kings position should return his position point
		when(tmpPiece.getPosition()).thenReturn(kingPoint); 
		//the king should return the number that identifies it as a king
		when(tmpPiece.getIdentifier()).thenReturn(5); 
		//the kings position should return his position point
		when(tmpPiece.getMoves()).thenReturn(kingMoves);
		
		return tmpPiece;
	}
	//creates a queen at the point queenPoint and a list of moves out of tmpPoints
	private Queen createMockQueen(Point queenPoint, Point[] tmpPoints){
		Queen tmpPiece= mock(Queen.class);
		LinkedList<Point> queenMoves = new LinkedList();
		for(Point p: tmpPoints){
			queenMoves.add(p);
		}
		
		//the queen is white
		when(tmpPiece.isWhite()).thenReturn(true); 
		//the queens position should return her point
		when(tmpPiece.getPosition()).thenReturn(queenPoint); 
		//the queens getMoves should return her list of moves
		when(tmpPiece.getMoves()).thenReturn(queenMoves);
		
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
		
		//the Bishop is white
		when(tmpPiece.isWhite()).thenReturn(true); 
		//the Bishops position should return the bishops point
		when(tmpPiece.getPosition()).thenReturn(bishPoint); 
		//the Bishops position should return the bishops moves
		when(tmpPiece.getMoves()).thenReturn(bishopMoves);
		
		return tmpPiece;
	}
	
	private Rook createMockRook(Point rookPoint, Point[] tmpPoints){
		Rook tmpPiece= mock(Rook.class);
		 //this is the point, b6, that the Bishop is at
		LinkedList<Point> rookMoves = new LinkedList();
		//only includes points that the king can move to
		for(Point p: tmpPoints){
			rookMoves.add(p);
		}
		
		//the rook is black
		when(tmpPiece.isWhite()).thenReturn(false); 
		//the rooks position should return the rooks point
		when(tmpPiece.getPosition()).thenReturn(rookPoint); 
		//the rooks position should return the rooks moves
		when(tmpPiece.getMoves()).thenReturn(rookMoves);
		
		return tmpPiece;
	}
}
