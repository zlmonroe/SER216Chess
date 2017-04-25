package Game;

import java.awt.Point;
import java.util.LinkedList;
import Game.Pieces.*;

public class Player {
	public static BoardState state;
	private boolean isWhite;
	
	public Player(boolean w){
		isWhite = w;
	}
	
	/**
	 * Returns true if the player would be in check with the temporary board state provided
	 * @param tmp - temporary board state 
	 * @return 
	 */
	public boolean inCheck(BoardState tmp){
		Piece king = getKing(tmp);
        for (Piece enemyPiece : tmp.getPieces(!isWhite)) {
            if (enemyPiece.getMoves().contains(king.getPosition())) {
                return true;
            }
        }
        return false;
	}
	
	public boolean inCheck(){
		return inCheck(state);
	}
	
	/**
	 * Returns true if there is no possible move that the player can make to move them out of check
	 * @return
	 */
	
	public boolean inCheckMate(){
		if(!inCheck(state)){
			return false;
		}
		LinkedList<Piece> pieces = state.getPieces(isWhite);
		for(Piece piece : pieces){
			for(Point move: piece.getMoves()){
				BoardState tmp = state.move(piece.getPosition(), move);
				if(tmp == null){//added for testing
				}
				else if(!inCheck(tmp)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Attempts to move the piece at point start to point end. Returns true if the move is successful, 
	 * false if the move would result in check. Does not change th board state if realMove is false
	 * @param start
	 * @param end
	 * @param realMove
	 * @return
	 */
	public boolean move(Point start, Point end){
		Piece piece = state.getPieceAt(start);
		LinkedList<Point> pieceMoves = piece.getMoves();
		if(!pieceMoves.contains(end)){
			return false;
		}
		BoardState tmp = state.move(start, end);
		if(inCheck(tmp)){
			return false;
		}
		state = tmp;
		return true;
	}
	
	/**
	 * Checks to see if the player has any possible moves
	 * @return
	 */
	public boolean hasMoves(){
		LinkedList<Piece> pieces = state.getPieces(isWhite);
		for (Piece piece : pieces) {
			LinkedList<Point> pieceMoves = piece.getMoves();
			for(Point move : pieceMoves){
				BoardState tmp = state.move(piece.getPosition(), move);
				if(tmp == null){//added for testing
				}
				else if(!inCheck(tmp)){
					return true;
				}
			}
	    }
		return false;
	}
	
	private Piece getKing(BoardState board){
		LinkedList<Piece> pieces = board.getPieces(isWhite);
		for(int i = 0; i < pieces.size(); i++){
			if(5 == pieces.get(i).getIdentifier()){//5 is the identifier for king, we check if this piece is the king
				return pieces.get(i);
			}
		}
		return null;
	}

}
