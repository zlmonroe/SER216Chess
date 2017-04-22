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
		Piece king = getKing();
        for (Piece enemyPiece : tmp.getPieces(!isWhite)) {
            if (enemyPiece.canMove(king.getPosition())) {
                return true;
            }
        }
        return false;
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
				if(!inCheck(tmp)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Attempts to move the piece at point start to point end. Returns true if the move is successful, false if the move would result in check
	 * @param start
	 * @param end
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
		piece = state.getPieceAt(start);
		piece.setPosition(end);
		return true;
	}
	
	private Piece getKing(){
		LinkedList<Piece> pieces = state.getPieces(isWhite);
		for(int i = 0; i < pieces.size(); i++){
			if(5 == pieces.get(i).getIdentifier()){//5 is the identifier for king, we check if this piece is the king
				return pieces.get(i);
			}
		}
		return null;
	}

}
