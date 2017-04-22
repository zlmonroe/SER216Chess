package Game;

import java.awt.Point;
import java.util.LinkedList;
import Game.Pieces.*;

public class Player {
	public static BoardState state;
	private boolean isWhite;
	
	public Player(boolean w, BoardState s){
		isWhite = w;
		state = s;
	}
	
	public boolean inCheck(BoardState tmp){
		Piece king = getKing();
        for (Piece enemyPiece : tmp.getPieces(!isWhite)) {
            if (enemyPiece.canMove(king.getPosition())) {
                return true;
            }
        }
        return false;
	}
	
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
	}

}
