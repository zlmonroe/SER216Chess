package Game;

import java.awt.Point;
import java.util.LinkedList;
import Game.Pieces.*;

public class Player {
	public static BoardState state;
	private MyPlayer enemy;
	private boolean isWhite;
	private LinkedList<Piece> piecesAttackingKing;
	public enum PieceID{
		PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING;
	}
	
	public boolean inCheck(BoardState tmp){
		Piece king = getKing();
		piecesAttackingKing = new LinkedList<Piece>();
        for (Piece enemyPiece : tmp.getPieces(!isWhite) {
            if (enemyPiece.canMove(king.getPosition())) {
                piecesAttackingKing.add(enemyPiece);
            }
        }
        return !piecesAttackingKing.isEmpty();
	}
	
	public boolean inCheckMate(){
		if(!inCheck(state)){
			return false;
		}
		LinkedList<Piece> pieces = state.getPieces(isWhite);
		for(Piece piece : piecesAttackingKing){
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
		piece.setPoint(end);
		return true;
	}
	
	private Piece getKing(){
		LinkedList<Piece> pieces = state.getPieces(isWhite);
		for(int i = 0; i < pieces.size(); i++){
			if(PieceID.KING == pieces.get(i).getID()){
				return pieces.get(i);
			}
		}
	}

}
