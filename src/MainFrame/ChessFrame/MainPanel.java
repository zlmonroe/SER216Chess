
package MainFrame.ChessFrame;

import MainFrame.ChessFrame.players.player1;
import MainFrame.ChessFrame.players.player2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainPanel extends JPanel {

    private player1 P1 = new player1();
    private player2 P2 = new player2();
    private final int Divide = 600 / 8;
    private int move = 0;
    private Rectangle2D rec;
    private short players_turn = 1;
    private final ToolPanel myTool;
    private final StatusPanel myStatus;
    private boolean GameOver = false;
    private boolean Iam_Server = false;
    private boolean Iam_Client = false;
    private ServerSocket ServerSock;
    private Socket Sock;
    private BufferedReader in;
    private PrintWriter out;
    private String Box;
    private boolean local = true;
    private JButton startServer;
    private JButton startClient;
    private String MyIp_Address;
    private String MyPort_number;
    private boolean Game_started = true;
    private Recv_Thread Recv_from;
    private ChatPanel Refe_Chat;


    public void start_As_Server(String Ip, String Port, ChatPanel newChat) {

        Recv_from = new Recv_Thread();
        Refe_Chat = newChat;
        Game_started = false;

        MyIp_Address = Ip;
        MyPort_number = Port;


        start_Again();
        startServer = new JButton(" Start server");
        startServer.setSize(150, 25);
        startServer.setLocation(200, 300);
        startServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {


                    ServerSock = new ServerSocket(Integer.parseInt(MyPort_number));

                    Thread Server = new Thread(new Runnable() {
                        public synchronized void run() {

                            try {

                                Sock = ServerSock.accept();

                                Refe_Chat.listen_chat();
                                in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
                                out = new PrintWriter(Sock.getOutputStream());
                                startServer.setVisible(false);
                                startServer = null;
                                Recv_from.start();

                                Game_started = true;

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    });


                    Server.start();

             /*in=new BufferedReader(new InputStreamReader(Sock.getInputStream()));
             out=new PrintWriter(Sock.getOutputStream());*/
                    // Sock.setSoTimeout(999999);
                    //  Refe_Chat.listen_chat();


                } catch (IOException ex) {
                    ex.printStackTrace();

                    JOptionPane.showConfirmDialog(null, "Server error", "Error", JOptionPane.ERROR_MESSAGE);
                }
                startServer.setText("Waiting...");


            }

        });
        local = false;
        add(startServer);


        Iam_Server = true;
        repaint();
    }

    public void start_As_Client(String Ip, String Port, ChatPanel newChat) {


        Recv_from = new Recv_Thread();

        Refe_Chat = newChat;

        Game_started = false;


        start_Again();
        MyIp_Address = Ip;
        MyPort_number = Port;
        local = false;
        startClient = new JButton("Start Client");
        startClient.setSize(150, 25);
        startClient.setLocation(200, 300);

        startClient.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {

                    Sock = new Socket(MyIp_Address, Integer.parseInt(MyPort_number));
                    in = new BufferedReader(new InputStreamReader(Sock.getInputStream()));
                    out = new PrintWriter(Sock.getOutputStream());


                    Recv_from.start();
                    Game_started = true;
                    Refe_Chat.start_chat();


                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showConfirmDialog(null, "Client error", "Error", JOptionPane.ERROR_MESSAGE);
                }

                startClient.setVisible(false);
                startClient = null;
            }
        });


        Iam_Client = true;
        add(startClient);


    }


    public void start_Again() {
        P1 = new player1();
        P2 = new player2();
        move = 0;
        players_turn = 1;
        GameOver = false;
        local = true;
        myTool.start_Again();
        myStatus.start_Again();
        Iam_Server = false;
        Iam_Client = false;
        repaint();

    }


    public MainPanel(ToolPanel myToolPanel, StatusPanel myStatusPanel) {
        setBackground(Color.WHITE);

        setSize(600, 600);
        setLocation(3, 10);

        MousewhenMove mouseDragAndDrop = new MousewhenMove();
        Mousehere mouseHereEvent = new Mousehere();
        addMouseMotionListener(mouseDragAndDrop);
        addMouseListener(mouseHereEvent);

        myTool = myToolPanel;
        myStatus = myStatusPanel;
        setLayout(null);


    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D) g;


        int iWidth = 600;
        int iHeight = 600;


        // Drawing the board
        for (int i = 0; i < 8; i = i + 2) {
            for (int j = 0; j < 8; j = j + 2) {

                g2.setColor(Color.BLUE);
                rec = new Rectangle2D.Double(j * iWidth / 8, (1 + i) * iWidth / 8, Divide, Divide);
                g2.fill(rec);
                rec = new Rectangle2D.Double((1 + j) * iWidth / 8, i * iWidth / 8, Divide, Divide);
                g2.fill(rec);

            }
        }

        /// Putting the pieces
        Point positionPoint;
        int postX;
        int postY;
        Image img;
        for (int i = 1; i <= 32; i++) {
            if (i < 17) {
                if (i == P2.petInHand()) {
                    positionPoint = P2.getPixelPoint(i);

                } else {
                    positionPoint = P2.returnPosition(i);
                }
                img = P2.returnIconImage(i);

            } else {


                if (i == P1.GetInhand()) {

                    positionPoint = P1.getPixelPoint(i);


                } else {
                    positionPoint = P1.returnPosition(i);
                }
                img = P1.returnIconImage(i);
            }


            if (i == P1.GetInhand())
                g2.drawImage(img, positionPoint.x - 25, positionPoint.y - 25, Divide - 40, Divide - 12, this);
            else if (i == P2.petInHand())
                g2.drawImage(img, positionPoint.x - 25, positionPoint.y - 25, Divide - 40, Divide - 12, this);
            else {
                postX = rowToX(positionPoint.x);
                postY = colToY(positionPoint.y);
                g2.drawImage(img, postX + 20, postY + 4, Divide - 40, Divide - 12, this);
            }


        }


    }

    /// You can inherit from Adapter and avoid meaningless
    /*When the mouse is released:
    Get the selected piece and its original position
    Get the new position that the user is trying to move it to
    Check if the move is legal for the piece type
    Check if other pieces are in the way
    Check if there are enemy pieces in the spot
    Check if I am a diagonal pawn or another piece
        If I am, set the piece to be killed
        If I am not, my move is invalid and we start again
    Move the piece (temporarily)
    If my King is in check, move back and start again
    If my King is not in check, check if my enemy is in checkmate
    If my enemy is in check, end the game
    If my enemy is not in check, repaint the screen, send the data to my enemy, and repaint.*/

    private class Mousehere implements MouseListener {
        //Fncs from the interface that are unecessary
        public void mouseClicked(MouseEvent e) {
        }

        public void mousePressed(MouseEvent e) {
        }

        //fnc for when the mouse is placed
        public void mouseReleased(MouseEvent e) {
            boolean can_Send = false;

            if (!GameOver) { //if the game is not over
                Point newP;
                Point samePosition;
                if (P1.GetInhand() != -1) { //Get in hand
                    Point present = P1.returnPosition(P1.GetInhand());//this is the original position

                    newP = P1.getPixelPoint(P1.GetInhand());// this, i think, is the new position of the piece
                    newP.x /= Divide; //divide is used to convert from the cooridinate in pixels to the rows
                    newP.y /= Divide; //divide is used to convert from the cooridinate in pixels to the rows
                    newP.x++; //I don't know why this happens...
                    newP.y++;
                    if (Iam_Server || local) {
                        // set the seen of the solider -white
                        if (P1.GetInhand() < 33 && P1.GetInhand() > 24) {//if the piece in hand is a pawn
                            for (int i = 1; i < 17; i++) {//for every black piece
                                samePosition = P2.returnPosition(i); //get the black pieces position
                                if (samePosition.x == newP.x && samePosition.y == newP.y) {//if the position is the same
                                    if (P1.setSeentoSiliders(P1.GetInhand(), samePosition))//if the pawn is moving diagonally
                                        break;
                                }
                            }
                        }
                        if (!(newP.x == present.x && newP.y == present.y)) //If the new position is not the same as the old
                            if (P1.checkthemove(newP, P1.GetInhand())) { // if the move is illegal
                                boolean flag = false;
                                for (int i = 1; i <= 32; i++) {
                                    if (P1.GetInhand() != i) {// check if there is pieces in the WAY
                                        if (i < 17)
                                            flag = P1.checktheWay(newP, P2.returnPosition(i), P1.GetInhand());//Means there is somting in the Way so can't move
                                        else {
                                            flag = P1.checktheWay(newP, P1.returnPosition(i), P1.GetInhand());
                                        }

                                        if (flag) break;//Means  there is a Piece in the Way
                                    }
                                }
                                if (!flag && P1.Piece_already_there(newP)) { //Piece_already_there checks if a piecce of the sme color is thee
                                    //(if flag =false this means "The piece able to MOVE as logic""
                                    // So We Check If the New Place Make  a Check To Black King !!!
                                    Point myold = new Point();
                                    Point o = P1.returnPosition(P1.GetInhand());
                                    myold.x = o.x;
                                    myold.y = o.y;
                                    Point other;
                                    Point f = new Point();
                                    boolean kill = false;
                                    int killed = -1;
                                    boolean end_move = true;
                                    ////***  Start Here to Check the King
                                    for (int k = 1; k < 17; k++) {// for all black pieces
                                        // I have to Check the Place
                                        other = P2.returnPosition(k); // get the black piecce
                                        if (newP.x == other.x && newP.y == other.y) {//checking if a black piece is on the new position
                                            int inHand = P1.GetInhand();
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
                                    if (end_move)//If the move is over, move the piece
                                        P1.changePosition(newP, P1.GetInhand());// Here is the mOve ended

                                    P1.checkKing(false);//set kingIsInCheck to false
                                    if (P1.see_king_Check(P2)) {//if our King is in check after the move
                                        // if my King will be in check if i move
                                        //so i can't move and i will return back to old position'
                                        P1.changePosition(myold, P1.GetInhand());
                                        P1.checkKing(true);
                                        end_move = false;
                                    }
                                    if (kill && P1.returncheckKing()) {//change the position f the killed piece back
                                        P2.changePosition(f, killed);//f is the point that can be killed
                                    }

                                    if (!P1.returncheckKing()) {
                                        if (P2.seeKingCheck(P1)) {
                                            // if my King will be in check if i move
                                            //so i can't move and i will return back to old position
                                            P2.checkKing(true);
                                            end_move = false;
                                            if (P2.Check_Mate_GameOver(P1)) {//I believe this checks if P2 is in check
                                                GameOver();//printing the move to the screen
                                                Box = Integer.toString(P2.petInHand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                                can_Send = true;
                                            } else {//printing the move to the screen
                                                Box = Integer.toString(P1.GetInhand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
                                                CheckStatus();
                                                can_Send = true;
                                            }
                                        }
                                        if (end_move) {//printing the move to the screen
                                            Box = Integer.toString(P1.GetInhand()) + Integer.toString(newP.x) + Integer.toString(newP.y);
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

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
    }

    ////////*---------------Mohamed Sami ------------------*//////////////////
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

                if (players_turn == 1 && P1.GetInhand() == -1)//Player 1
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
                } else if (players_turn == 1 && P1.GetInhand() != -1)//Player 1
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

        if (players_turn == 1 && P1.GetInhand() != -1) {
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


    private void ChangeTurn() {
        if (players_turn == 1) {
            players_turn = 2;
            myTool.add_to_History("White : " + P1.Tell_me_About_last_move());
            myStatus.changeStatus(" Black player turn");
            myTool.change_to_Timer2();
        } else if (players_turn == 2) {
            players_turn = 1;
            myTool.add_to_History("Black : " + P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
            myStatus.changeStatus(" White player turn");
        }


    }

    private void NetChangeTurn() {
        if (players_turn == 2) {

            myTool.add_to_History("White : " + P1.Tell_me_About_last_move());
            myStatus.changeStatus(" Black player turn");
            myTool.change_to_Timer2();
        } else if (players_turn == 1) {

            myTool.add_to_History("Black : " + P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
            myStatus.changeStatus(" White player turn");
        }

    }

    private void NeTGameCheckStatus() {
        if (players_turn == 1) {


            myTool.add_to_History("White : " + P1.Tell_me_About_last_move());
            myTool.change_to_Timer2();
        } else if (players_turn == 2) {


            myTool.add_to_History("Black : " + P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
        }
        myStatus.changeStatus(" Check! ");
    }


    private void CheckStatus() {
        if (players_turn == 1) {

            players_turn = 2;
            myTool.add_to_History("White : " + P1.Tell_me_About_last_move());
            myTool.change_to_Timer2();
        } else if (players_turn == 2) {

            players_turn = 1;
            myTool.add_to_History("Black : " + P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
        }


        myStatus.changeStatus(" Check! ");
    }


    private void GameOver() {

        myStatus.changeStatus(" Check Mate! ");


        GameOver = true;
    }


    public void Send_move() {
        out.print(Box);
        out.print("\r\n");
        out.flush();

    }

    class Recv_Thread extends Thread {
        public synchronized void run() {


            while (true) {

                try {

                    Box = in.readLine();

                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("ERROR");
                }


                if (Box != null) {


                    int newInHand = Integer.parseInt(Box);
                    int newX = Integer.parseInt(Box);
                    int newY = Integer.parseInt(Box);


                    /*
                     * Operation to Get
                     *1- The # of Piece
                     *2- The Location X
                     *3- The Location Y
                     *
                     */

                    newInHand /= 100;
                    newX -= (newInHand * 100);
                    newX /= 10;
                    newY -= (newInHand * 100) + (newX * 10);


                    if (players_turn == 1) {


                        P1.SetInhand(newInHand);
                        players_turn = 2;

                        P1.changePosition(new Point(newX, newY), newInHand);

                        P2.killedPiece(P1.Get_Piece_already_there_from_enemy(new Point(newX, newY), P2));
                        P2.checkKing(false);

                        if (P2.seeKingCheck(P1))
                        // if my King will be in check if i move
                        //so i can't move and i will return back to old position'
                        {

                            P2.checkKing(true);


                            if (P2.Check_Mate_GameOver(P1)) {
                                System.out.println("mate");
                                GameOver();

                            } else {

                                NeTGameCheckStatus();

                            }
                        } else NetChangeTurn();


                        P1.SetInhand(-1);


                    } else {
                        P2.setInHand(newInHand);
                        P2.changePosition(new Point(newX, newY), newInHand);

                        P1.killedPiece(P2.getPieceAlreadyThereFromEnemy(new Point(newX, newY), P1));
                        players_turn = 1;

                        P1.checkKing(false);
                        if (P1.see_king_Check(P2))
                        // if my King will be in check if i move
                        //so i can't move and i will return back to old position'
                        {

                            P1.checkKing(true);


                            if (P1.Check_Mate_GameOver(P2)) {

                                System.out.println("mate");
                                GameOver();

                            } else {

                                NeTGameCheckStatus();

                            }
                        } else NetChangeTurn();

                        P2.setInHand(-1);
                    }
                    //   CheckStatus();

                    repaint();
                }

            }
        }
    }

}


