package JChess.Game;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import JChess.Game.Pieces.Piece;

public class GameLogic {

	Player currentPlayer;
	Player enemy;
	Piece inHand;
	private final int spaceSize = 600/8;
	
	public GameLogic(Player p1, Player p2){
		this.currentPlayer = p1;
		this.enemy = p2;
		inHand = p1.getInHand();
	}
	
	public boolean isMoveValid(){
        if (currentPlayer.checkthemove(inHand.returnCurrent(), inHand)) { // if the move is legal
            boolean flag = false;
            for (Piece p : currentPlayer.pieces) {// check if there is pieces in the WAY
            	flag = currentPlayer.checktheWay(inHand.returnCurrent(), p.returnOld(), inHand);
            	if (flag) break;//Means  there is a Piece in the Way
            }
        }
		return flag;
	}
	
	public boolean isPieceInWay(){
		return false;
	}
	
	public void killEnemy(){
		
	}
	
	public boolean checkCheck(){
		return false;
	}
	
	public boolean checkCheckmate(){
		return false;
	}
	
	public void movePiece(){
		
	}
	
	private int[] getChessBoardPos(Point p){
		int[] point = new int[2];
		point[0] = p.x/spaceSize;
		point[1] = p.y/spaceSize;
		return point;
	}
	
	public void mouseReleased(MouseEvent e) {
        boolean can_Send = false;

        if (!GameOver) { //if the game is not over
            Point newP;
            Point samePosition;
            if (P1.getInHand() != -1) { //Get in hand
                Point present = P1.returnPosition(P1.getInHand());//this is the original position

                //Convert the point to rows and cols*******************************************************
                newP = P1.getPixelPoint(P1.getInHand());// this, i think, is the new position of the piece
                newP.x /= Divide; //divide is used to convert from the cooridinate in pixels to the rows
                newP.y /= Divide; //divide is used to convert from the cooridinate in pixels to the rows
                newP.x++; //I don't know why this happens...// they index from 1 :/
                newP.y++;
                
                if (Iam_Server || local) {
                    // set the seen of the solider -white
                	//Check if the piece is a diagonally moving pawn and it is valid**************
                	//move to pawn
                    if (P1.getInHand() < 33 && P1.getInHand() > 24) {//if the piece in hand is a pawn
                        for (int i = 1; i < 17; i++) {//for every black piece
                            samePosition = P2.returnPosition(i); //get the black pieces position
                            if (samePosition.x == newP.x && samePosition.y == newP.y) {//if the position is the same
                                if (P1.setSeentoSiliders(P1.getInHand(), samePosition))//if the pawn is moving diagonally
                                    break;
                            }
                        }
                    }
                    //Check if the move is valid and if there is a piece in the way. This should be oved to the pieces class
                    if (!(newP.x == present.x && newP.y == present.y)) //If the new position is not the same as the old
                    	//
                        if (P1.checkthemove(newP, P1.getInHand())) { // if the move is illegal
                            boolean flag = false;
                            //this part tests if something is in the way. This means that the piece is along the path, not at the place we are trying to move
                            for (int i = 1; i <= 32; i++) {
                                if (P1.getInHand() != i) {// check if there is pieces in the WAY
                                    if (i < 17)
                                        flag = P1.checktheWay(newP, P2.returnPosition(i), P1.getInHand());//Means there is somting in the Way so can't move
                                    else {
                                        flag = P1.checktheWay(newP, P1.returnPosition(i), P1.getInHand());
                                    }

                                    if (flag) break;//Means  there is a Piece in the Way
                                }
                            }
                            if (!flag && P1.Piece_already_there(newP)) { //Piece_already_there checks if a piecce of the sme color is thee
                                //(if flag =false this means "The piece able to MOVE as logic""
                                // So We Check If the New Place Make  a Check To Black King !!!
                                Point myold = new Point();
                                Point o = P1.returnPosition(P1.getInHand());
                                myold.x = o.x;
                                myold.y = o.y;
                                Point other;
                                Point f = new Point();
                                boolean kill = false;
                                int killed = -1;
                                boolean end_move = true;
                                ////***  Start Here to Check the King
                                //this part actually kills the other piece
                                for (int k = 1; k < 17; k++) {// for all black pieces
                                    // I have to Check the Place
                                    other = P2.returnPosition(k); // get the black piecce
                                    if (newP.x == other.x && newP.y == other.y) {//checking if a black piece is on the new position
                                        int inHand = P1.getInHand();
                                        if (inHand > 24 && P1.returnsoliderSeen(inHand)) {//if this piece is a pawn and it is moving diagonally
                                            kill = true; //says we have killed a piece
                                            f.x = other.x;
                                            f.y = other.y;
                                            P2.killedPiece(k); //I assume this kills the other players piece
                                        } else if (inHand <= 24) {// if it isn't a pawn, we don't have to check anything, just kill
                                            kill = true;

                                            f.x = other.x;
                                            f.y = other.y;

                                            P2.killedPiece(k);
                                        } else {// moves the piece back to the old piece
                                            P1.changePosition(myold, inHand);
                                            end_move = false;
                                            break;
                                        }
                                        killed = k;//The piece that is killed is called "killed"
                                        break;
                                    }
                                }
                                //this moves our piece
                                if (end_move)//If the move is over, move the piece
                                    P1.changePosition(newP, P1.getInHand());// Here is the mOve ended

                                //this part checks the king then movves the piece backif the king is in check
                                P1.checkKing(false);//set kingIsInCheck to false
                                if (P1.see_king_Check(P2)) {//if our King is in check after the move
                                    // if my King will be in check if i move
                                    //so i can't move and i will return back to old position'
                                    P1.changePosition(myold, P1.getInHand());
                                    P1.checkKing(true);
                                    end_move = false;
                                }
                                if (kill && P1.returncheckKing()) {//change the position f the killed piece back
                                    P2.changePosition(f, killed);//f is the point that can be killed
                                }

                                //checking to see if we've killed the other guy
                                if (!P1.returncheckKing()) {
                                    if (P2.seeKingCheck(P1)) {
                                        P2.checkKing(true);
                                        end_move = false;
                                        if (P2.Check_Mate_GameOver(P1)) {//I believe this checks if P2 is in check
                                            GameOver();//printing the move to the screen
                                            Box = Integer.toString(P2.petInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                            can_Send = true;
                                        } else {//printing the move to the screen
                                            Box = Integer.toString(P1.getInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                            CheckStatus();
                                            can_Send = true;
                                        }
                                    }
                                    if (end_move) {//printing the move to the screen
                                        Box = Integer.toString(P1.getInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                        ChangeTurn();
                                        can_Send = true;
                                    }
                                }
                            }
                        }
                    P1.SetInhand(-1);//set in hand to neg one because it is not p1's turn
                    repaint();
                    if (can_Send && ((Iam_Server || Iam_Client))) {//send the move to the other guy
                        Send_move();
                        //Send_to.resume();
                        //Recv_from.resume();
                    }
                    if (GameOver)//if we put P2's King in check, game over
                        JOptionPane.showConfirmDialog(null, "Check Mate\n White won the game", "Game Over", JOptionPane.PLAIN_MESSAGE);

                }
            }
            ///////////////////////////////Black/////////////////////////////////////////
            //////////////////////////////Black///////////////////////////////////////////
            //////////////////////////////Black//////////////////////////////////////////////
            //////////////////////////////Black//////////////////////////////////////////////
            //This probably does the same thing the white player does
            else if (P2.petInHand() != -1)//white
            {
                if (Iam_Client || local) {
                    newP = P2.getPixelPoint(P2.petInHand());
                    newP.x /= Divide;
                    newP.y /= Divide;
                    newP.x++;
                    newP.y++;
                    boolean Kingch = false;
                    Point old = P2.returnOldPosition(P2.petInHand());
                    Point present = P2.returnPosition(P2.petInHand());
                    // set the seen of the solider -black
                    // set the seen of the solider -black
                    // set the seen of the solider -black
                    if (P2.petInHand() < 17 && P2.petInHand() > 8) {
                        for (int i = 17; i < 33; i++) {
                            samePosition = P1.returnPosition(i);

                            if (samePosition.x == newP.x && samePosition.y == newP.y) {
                                if (P2.setSeentoSiliders(P2.petInHand(), samePosition)) {
                                    break;
                                }
                            }
                        }
                    }
                    if (!(newP.x == present.x && newP.y == present.y)/*&&!P2.returnCheckKing()*/)
                        if (P2.checkTheMove(newP, P2.petInHand())) {
                            boolean flag = false;
                            for (int i = 1; i <= 32; i++) {
                                if (P2.petInHand() != i) {
                                    if (i < 17)
                                        flag = P2.checkTheWay(newP, P2.returnPosition(i), P2.petInHand());
                                    else
                                        flag = P2.checkTheWay(newP, P1.returnPosition(i), P2.petInHand());

                                    if (flag) break;
                                }
                            }
                            for (int i = 1; i <= 16 && !flag; i++) {
                                if (P2.petInHand() != i) {
                                    if (!flag) {
                                        samePosition = P2.returnPosition(i);
                                        if (newP.x == samePosition.x && newP.y == samePosition.y) {
                                            flag = true;
                                            break;

                                        }
                                    }
                                }
                                if (flag) break;
                            }
                            if (!flag) {
                                Point kingPosition2 = P2.returnPosition(8);
                                Point myold = new Point();
                                Point o = P2.returnPosition(P2.petInHand());
                                myold.x = o.x;
                                myold.y = o.y;
                                Point other;
                                Point f = new Point();
                                boolean kill = false;
                                boolean end_move = true;
                                int killed = -1;
                                for (int k = 17; k < 33; k++) {
                                    other = P1.returnPosition(k);
                                    if (newP.x == other.x && newP.y == other.y) {
                                        int inHand = P2.petInHand();
                                        if (inHand > 8 && P2.returnSoliderSeen(inHand)) {
                                            kill = true;
                                            other = P1.returnPosition(k);
                                            f.x = other.x;
                                            f.y = other.y;
                                            P1.killedPiece(k);
                                        } else if (inHand <= 8) {
                                            kill = true;
                                            other = P1.returnPosition(k);
                                            f.x = other.x;
                                            f.y = other.y;
                                            P1.killedPiece(k);
                                        } else {
                                            end_move = false;
                                            P2.changePosition(myold, inHand);
                                        }
                                        killed = k;
                                        break;
                                    }
                                }
                                //boolean kin2=true;
                                if (end_move)
                                    P2.changePosition(newP, P2.petInHand());
                                P2.checkKing(false);
                                if (P2.seeKingCheck(P1))
                                // if my King will be in check if i move
                                //so i can't move and i will return back to old position'
                                {
                                    P2.changePosition(myold, P2.petInHand());
                                    P2.checkKing(true);
                                    end_move = false;
                                }
                                if (kill && P2.returnCheckKing()) {
                                    P1.changePosition(f, killed);
                                }
                                if (P2.returnCheckKing()) {
                                    P2.changePosition(myold, P2.petInHand());
                                }
                                if (!P2.returnCheckKing()) {
                                    if (P1.see_king_Check(P2))
                                    // if my King will be in check if i move
                                    //so i can't move and i will return back to old position'
                                    {
                                        P1.checkKing(true);
                                        end_move = false;
                                        if (P1.Check_Mate_GameOver(P2)) {
                                            Box = Integer.toString(P2.petInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                            GameOver();
                                            can_Send = true;
                                        } else {
                                            Box = Integer.toString(P2.petInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                            CheckStatus();
                                            can_Send = true;
                                        }
                                    }
                                    if (end_move) {
                                        Box = Integer.toString(P2.petInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                        ChangeTurn();
                                        can_Send = true;
                                    }
                                }
                            }
                        }
                    P2.setInHand(-1);
                    repaint();
                    if (can_Send && ((Iam_Server || Iam_Client))) {
                        //Send_to.resume();
                        Send_move();
                        ///     Recv_from.resume();
                    }
                    if (GameOver)
                        JOptionPane.showConfirmDialog(null, "Check Mate\n Black won the game", "Game Over", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
    }

	public boolean boardGetPosition(int x, int y)


    {
        if (!GameOver && Game_started) {
            if ((Iam_Server && players_turn == 1) || (local) || (Iam_Client && players_turn == 2)) {

                int newX = x / Divide;
                int newY = y / Divide;
                newX++;
                newY++;

                if (newX > 8 || newY > 8 || newX < 1 || newY < 1) {
                    repaint();
                    return false;

                }

                if (players_turn == 1 && P1.getInHand() == -1)//Player 1
                {
                    for (int i = 17; i <= 32; i++) {
                        Point p = P1.returnPosition(i);
                        if (p.x == newX && p.y == newY) {
                            P1.SetInhand(i);
                            whenHandleAndPiece(x, y);
                            return true;
                        }
                    }
                } else if (players_turn == 2 && P2.petInHand() == -1)//Player 2
                {
                    for (int i = 1; i <= 16; i++) {
                        Point p = P2.returnPosition(i);
                        if (p.x == newX && p.y == newY) {
                            P2.setInHand(i);
                            whenHandleAndPiece(x, y);
                            return true;
                        }
                    }
                } else if (players_turn == 1 && P1.getInHand() != -1)//Player 1
                {
                    whenHandleAndPiece(x, y);
                    return true;
                } else if (players_turn == 2 && P2.petInHand() != -1)//Player 2
                {
                    whenHandleAndPiece(x, y);
                    return true;
                }
                P1.SetInhand(-1);
                move = 0;

                return false;

            }
        }
        return false;
    }

    public boolean whenHandleAndPiece(int x, int y) {

        if (players_turn == 1 && P1.getInHand() != -1) {
            P1.changePixel(x, y, P1.GetInhand());
            return true;
        } else if (players_turn == 2 && P2.petInHand() != -1) {
            P2.changePixel(x, y, P2.petInHand());
            return true;
        }
        return false;
    }

    private int rowToX(int r) {
        int myx;
        int iHeight = this.getHeight();
        myx = (r * iHeight / 8) - Divide;
        return myx;
    }

    private int colToY(int c) {
        int myy;
        int iWidth = getWidth();
        myy = (c * iWidth / 8) - Divide;
        return myy;
    }


    private class MousewhenMove implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();
            if (controll_game_type(x, y)) {

                repaint();
            }

        }

        public void mouseMoved(MouseEvent e) {

        }


    }

    public boolean controll_game_type(int x, int y) {

        if (Iam_Server || Iam_Client && Game_started) {
            if (Iam_Server && players_turn == 1) {
                return boardGetPosition(x, y);
            } else if (Iam_Client && players_turn == 2) {
                return boardGetPosition(x, y);
            } else
                return false;
        } else {
            return boardGetPosition(x, y);
        }


        // return false;
    }

	
}
