package JChess.Game;

import java.awt.Point;
import java.util.LinkedList;

import JChess.Game.Pieces.Piece;

public class MyPlayer {
	public static ChessboardState state;
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
            if (enemyPiece.canMove(king.getPoint().x,king.getPoint().y)) {
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
		LinkedList<Piece> ePieces = state.getPieces(!isWhite);
		for(Piece ePiece : piecesAttackingKing){
			for(Piece piece: pieces){
				if(piece.getMoves().contains(ePiece.getPoint())){
					return false;
				}
			}
		}
		
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
	
	private int getKing(){
		for(int i = 0; i < pieces.size(); i++){
			if(PieceID.KING == pieces.get(i).getID()){
				return i;
			}
		}
	}

}
