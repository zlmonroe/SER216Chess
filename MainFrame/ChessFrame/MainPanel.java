
package MainFrame.ChessFrame;

import MainFrame.ChessFrame.players.player1;
import MainFrame.ChessFrame.players.player2;

import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.*;
import java.lang.String;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.net.*;


public class MainPanel extends JPanel {
    
    private  player1 P1=new player1();
    private  player2 P2=new player2();
    private final int Divide=600/8;
    private  int  move =0;
    private Rectangle2D rec;
    private  short players_turn=1;
    public  final ToolPanel myTool;
    private  final StatusPanel myStatus;
    private  boolean GameOver=false;
    private boolean Iam_Server=false;
    private boolean Iam_Client=false;
    private ServerSocket ServerSock;
    private Socket Sock;
    private BufferedReader in;
    private PrintWriter out;
    private String Box;
    private boolean local=true;
    private JButton startServer;
    private JButton startClient;
    private String MyIp_Address;
    private String MyPort_number;
    private boolean Game_started=true;
    private Recv_Thread Recv_from;
    private ChatPanel Refe_Chat;
    
    
    public void start_As_Server(String Ip,String Port,ChatPanel newChat) {
        
        Recv_from=new Recv_Thread();
        Refe_Chat=newChat;
        Game_started=false;
        
        MyIp_Address=Ip;
        MyPort_number=Port;
        
        
        
        
        
        start_Again();
        startServer=new JButton(" Start server");
        startServer.setSize(150,25);
        startServer.setLocation(200,300);
        startServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                try {
                    
                    
                    ServerSock=new ServerSocket(Integer.parseInt(MyPort_number));
                    
                    Thread Server=new Thread(new Runnable() {
                        public synchronized  void run() {
                            
                            try {
                                
                                Sock=ServerSock.accept();
                                
                                Refe_Chat.listen_chat();
                                in=new BufferedReader(new InputStreamReader(Sock.getInputStream()));
                                out=new PrintWriter(Sock.getOutputStream());
                                startServer.setVisible(false);
                                startServer=null;
                                Recv_from.start();
                                
                                Game_started=true;
                                
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
                    
                    JOptionPane.showConfirmDialog(null,"Server error","Error",JOptionPane.ERROR_MESSAGE);
                }
                startServer.setText("Waiting...");
                
                
            }
            
        });
        local=false;
        add(startServer);
        
        
        Iam_Server=true;
        repaint();
    }
    public void start_As_Client(String Ip,String Port,ChatPanel newChat) {
        
        
        Recv_from=new Recv_Thread();
        
        Refe_Chat=newChat;
        
        Game_started=false;
        
        
        
        start_Again();
        MyIp_Address=Ip;
        MyPort_number=Port;
        local=false;
        startClient=new JButton("Start Client");
        startClient.setSize(150,25);
        startClient.setLocation(200,300);
        
        startClient.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                try {
                    
                    Sock=new Socket(MyIp_Address,Integer.parseInt(MyPort_number));
                    in=new BufferedReader(new InputStreamReader(Sock.getInputStream()));
                    out=new PrintWriter(Sock.getOutputStream());
                    
                    
                    
                    Recv_from.start();
                    Game_started=true;
                    Refe_Chat.start_chat();
                    
                    
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showConfirmDialog(null,"Client error","Error",JOptionPane.ERROR_MESSAGE);
                }
                
                startClient.setVisible(false);
                startClient=null;
            }
        });
        
        
        Iam_Client=true;
        add(startClient);
        
        
    }
    
    
    public void start_Again() {
        P1=new player1();
        P2=new player2();
        move =0;
        players_turn=1;
        GameOver=false;
        local=true;
        myTool.start_Again();
        myStatus.start_Again();
        Iam_Server=false;
        Iam_Client=false;
        repaint();
        
    }
    
    
    public MainPanel(ToolPanel myToolPanel,StatusPanel myStatusPanel) {
        setBackground(Color.WHITE);
        
        setSize(600,600);
        setLocation(3,10);
        
        MousewhenMove mouseDragAndDrop=new MousewhenMove();
        Mousehere    mouseHereEvent=new Mousehere();
        addMouseMotionListener(mouseDragAndDrop);
        addMouseListener(mouseHereEvent);
        
        myTool=myToolPanel;
        myStatus=myStatusPanel;
        setLayout(null);
        
        
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        
        Graphics2D g2 = (Graphics2D)g;
        
        
        
        
        
        int iWidth = 600;
        int iHeight = 600;
        
        
        // Drawing the board
        for (int i=0; i<8; i=i+2) {
            for (int j=0; j<8; j=j+2) {
                
                g2.setColor(Color.BLUE);
                rec=new Rectangle2D.Double(j*iWidth/8,(1+i)*iWidth/8,Divide,Divide);
                g2.fill(rec);
                rec=new Rectangle2D.Double((1+j)*iWidth/8,i*iWidth/8,Divide,Divide);
                g2.fill(rec);
                
            }
        }
        
        /// Puting the pieces
        Point postionPoint;
        int postX;
        int postY;
        Image img;
        for (int i = 1; i <= 32; i++) {
            if(i<17) {
                if(i==P2.GetInhand()) {
                    postionPoint=P2.getPixelPoint(i);
                    
                } else {
                    postionPoint=P2.returnPostion(i);   }
                img=P2.returnIconImage(i);
                
            }
            
            else {
                
                
                if(i==P1.GetInhand()) {
                    
                    postionPoint=P1.getPixelPoint(i);
                    
                    
                } else {
                    postionPoint=P1.returnPostion(i);   }
                img=P1.returnIconImage(i);
            }
            
            
            if(i==P1.GetInhand())
                g2.drawImage(img,postionPoint.x-25,postionPoint.y-25,Divide-40,Divide-12 ,this);
            else if(i==P2.GetInhand())
                g2.drawImage(img,postionPoint.x-25,postionPoint.y-25,Divide-40,Divide-12 ,this);
            else {
                postX=rowToX(postionPoint.x);
                postY=colToY(postionPoint.y);
                g2.drawImage(img,postX+20,postY+4,Divide-40,Divide-12 ,this);
            }
            
            
        }
        
        
        
    }
    
    /// You can inherit from Adapter and avoid meaningless
    private class  Mousehere implements MouseListener {
        
        public void mouseClicked(MouseEvent e) {
            
            
        }
        
        public void mousePressed(MouseEvent e) {
            
            
        }
        
    
        
        public void mouseReleased(MouseEvent e) {
            boolean can_Send=false;
            
            if(!GameOver) {
                
                Point newP;
                Point samePostion;
                if(P1.GetInhand()!=-1) {
                    
                    
                    newP=P1.getPixelPoint(P1.GetInhand());
                    newP.x/=Divide;
                    newP.y/=Divide;
                    newP.x++;
                    newP.y++;
                    int otherindex;
                    
                    Point old=P1.returnOldPostion(P1.GetInhand());
                    int x=old.x;
                    int y=old.y;
                    Point present=P1.returnPostion(P1.GetInhand());
                    
                    
                    
                    ///////////////////////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////////////////////////
                    
                    
                    
                    
                    if(Iam_Server||local) {
                        
                        
                        // set the seen of the solider -white
                        if(P1.GetInhand()<33&&P1.GetInhand()>24) {
                            for(int i=1;i<17;i++) {
                                samePostion=P2.returnPostion(i);
                                if(samePostion.x==newP.x&&samePostion.y==newP.y) {
                                    if(P1.setSeentoSiliders(P1.GetInhand(),samePostion))
                                        break;
                                }
                            }
                        }
///////////////////////////////////////////////////////////////////////////////////
                        if(!(newP.x== present.x&&newP.y== present.y)/*&&!P1.returncheckKing()*/)
                            if(P1.checkthemove(newP,P1.GetInhand() )) // if the move is illegal
                            {
                            
                            
                            boolean flag=false;
                            
                            
                            for(int i=1;i<=32;i++) {
                                if(P1.GetInhand()!=i)// check if there is peices in the WAY
                                {
                                    if(i<17)
                                        flag=P1.checktheWay(newP,P2.returnPostion(i),P1.GetInhand());//Means there is somting in the Way so can't move
                                    else {
                                        flag=P1.checktheWay(newP,P1.returnPostion(i),P1.GetInhand());
                                    }
                                    
                                    if(flag==true)break;//Means  there is a Pice in the Way
                                }
                                
                                
                                
                                
                                //
                                
                                
                            }
                            
                            
                            if(!flag&&P1.Pice_already_there(newP))
                                //(if flag =false this means "The pice able to MOVE as logic""
                            {
                                // So We Check If the New Place Make  a Check To Black King !!!
                                boolean kin2=true;
                                Point myold=new Point();
                                Point o=P1.returnPostion(P1.GetInhand());
                                myold.x=o.x;
                                myold.y=o.y;
                                Point other=new Point();
                                Point f=new Point();
                                boolean kill=false;
                                int killed=-1;
                                boolean end_move=true;
                                
                                
                                
                                ////***  Start Here to Check the King
                                
                                for(int k=1;k<17;k++) {
                                    // I have to Check the Place
                                    
                                    other=P2.returnPostion(k);
                                    
                                    
                                    
                                    
                                    if(newP.x==other.x&&newP.y==other.y) {
                                        
                                        int inHand=P1.GetInhand();
                                        
                                        if(inHand>24&&P1.returnsoliderSeen(inHand)) {
                                            kill=true;
                                            
                                            
                                            f.x=other.x;
                                            f.y=other.y;
                                            
                                            P2.Killedpiec(k);
                                        } else if(inHand<=24) {
                                            kill=true;
                                            
                                            f.x=other.x;
                                            f.y=other.y;
                                            
                                            P2.Killedpiec(k);
                                        } else {
                                            P1.changePostion(myold,inHand);
                                            end_move=false;
                                            
                                            break;
                                        }
                                        
                                        
                                        killed=k;//!!!
                                        
                                        break;
                                        
                                    }
                                    
                                }
                                
                                
                                if(end_move)
                                    P1.changePostion(newP,P1.GetInhand());// Here is the mOve ended
                                
                                P1.checkKing(false);
                                if(P1.see_king_Check(P2))
                                    // if my king will be in check if i move
                                    //so i can't move and i will return back to old postion'
                                {
                                    P1.changePostion(myold,P1.GetInhand());
                                    P1.checkKing(true);
                                    end_move=false;
                                }
                                if(kill&&P1.returncheckKing()) {
                                    P2.changePostion(f,killed);
                                    
                                    
                                }
                                
                                
                                
                                if(!P1.returncheckKing()) {
                                    
                                    if(P2.see_king_Check(P1))
                                        // if my king will be in check if i move
                                        //so i can't move and i will return back to old postion'
                                    {
                                        
                                        P2.checkKing(true);
                                        end_move=false;
                                        if(P2.Check_Mate_GameOver(P1)) {
                                            GameOver();
                                            Box=Integer.toString(P2.GetInhand())+Integer.toString(newP.x)+Integer.toString(newP.y);
                                            can_Send=true;
                                        }
                                        
                                        else {
                                            Box=Integer.toString(P1.GetInhand())+Integer.toString(newP.x)+Integer.toString(newP.y);
                                            
                                            CheckStatus();
                                            can_Send=true;
                                            
                                        }
                                        
                                        
                                    }
                                    
                                    
                                    
                                    
                                    if(end_move) {
                                        Box=Integer.toString(P1.GetInhand())+Integer.toString(newP.x)+Integer.toString(newP.y);
                                        
                                        
                                        ChangeTurn();
                                        can_Send=true;
                                    }
                                    
                                    
                                    
                                }
                                
                            }
                            
                            }
                        
                        P1.SetInhand(-1);
                        
                        repaint();
                        
                        if(can_Send&&((Iam_Server||Iam_Client))) {
                            
                            Send_move();
                            //Send_to.resume();
                            
                            //          Recv_from.resume();
                            
                        }
                        
                        if(GameOver)
                            JOptionPane.showConfirmDialog(null,"Check Mate\n White won the game","Game Over",JOptionPane.PLAIN_MESSAGE);
                        
                    }
                }
                ///////////////////////////////Black/////////////////////////////////////////
                //////////////////////////////Black///////////////////////////////////////////
                //////////////////////////////Black//////////////////////////////////////////////
                //////////////////////////////Black//////////////////////////////////////////////
                else if(P2.GetInhand()!=-1)//white
                {
                    
                    if(Iam_Client||local) {
                        newP=P2.getPixelPoint(P2.GetInhand());
                        newP.x/=Divide;
                        newP.y/=Divide;
                        newP.x++;
                        newP.y++;
                        boolean Kingch=false;
                        Point old=P2.returnOldPostion(P2.GetInhand());
                        Point present=P2.returnPostion(P2.GetInhand());
                        
                        
                        // set the seen of the solider -black
                        // set the seen of the solider -black
                        // set the seen of the solider -black
                        if(P2.GetInhand()<17&&P2.GetInhand()>8) {
                            for(int i=17;i<33;i++) {
                                samePostion=P1.returnPostion(i);
                                
                                if(samePostion.x==newP.x&&samePostion.y==newP.y) {
                                    if(P2.setSeentoSiliders(P2.GetInhand(),samePostion)) {
                                        
                                        break;
                                    }
                                }
                            }
                        }
                        
                        
                        if(!(newP.x== present.x&&newP.y== present.y)/*&&!P2.returncheckKing()*/)
                            if(P2.checkthemove(newP,P2.GetInhand())) {
                            boolean flag=false;
                            for(int i=1;i<=32;i++) {
                                if(P2.GetInhand()!=i) {
                                    if(i<17)
                                        flag=P2.checktheWay(newP,P2.returnPostion(i),P2.GetInhand());
                                    else
                                        flag=P2.checktheWay(newP,P1.returnPostion(i),P2.GetInhand());
                                    
                                    if(flag)break;
                                }
                            }
                            
                            
                            
                            for(int i=1;i<=16&&!flag;i++) {
                                if(P2.GetInhand()!=i) {
                                    if(flag==false) {
                                        samePostion=P2.returnPostion(i);
                                        if(newP.x==samePostion.x&&newP.y==samePostion.y) {
                                            flag =true;
                                            break;
                                            
                                        }
                                    }
                                    
                                }
                                
                                if(flag)break;
                            }
                            
                            if(!flag) {
                                Point kingPostion2=P2.returnPostion(8);
                                Point myold=new Point();
                                Point o=P2.returnPostion(P2.GetInhand());
                                myold.x=o.x;
                                myold.y=o.y;
                                Point other=new Point();
                                Point f=new Point();
                                boolean kill=false;
                                boolean end_move=true;
                                int killed=-1;
                                
                                
                                
                                for(int k=17;k<33;k++) {
                                    other=P1.returnPostion(k);
                                    if(newP.x==other.x&&newP.y==other.y) {
                                        
                                        int inHand=P2.GetInhand();
                                        
                                        
                                        if(inHand>8&&P2.returnsoliderSeen(inHand)) {
                                            kill=true;
                                            
                                            other=P1.returnPostion(k);
                                            
                                            f.x=other.x;
                                            f.y=other.y;
                                            
                                            P1.Killedpiec(k);
                                        } else if(inHand<=8) {
                                            kill=true;
                                            
                                            other=P1.returnPostion(k);
                                            
                                            f.x=other.x;
                                            f.y=other.y;
                                            P1.Killedpiec(k);
                                        } else {
                                            end_move=false;
                                            P2.changePostion(myold,inHand);
                                        }
                                        
                                        
                                        
                                        killed=k;
                                        break;
                                        
                                    }
                                    
                                }
                                //boolean kin2=true;
                                if(end_move)
                                    P2.changePostion(newP,P2.GetInhand());
                                
                                P2.checkKing(false);
                                if(P2.see_king_Check(P1))
                                    // if my king will be in check if i move
                                    //so i can't move and i will return back to old postion'
                                {
                                    P2.changePostion(myold,P2.GetInhand());
                                    P2.checkKing(true);
                                    
                                    end_move=false;
                                    
                                    
                                }
                                if(kill&&P2.returncheckKing()) {
                                    
                                    P1.changePostion(f,killed);
                                }
                                
                                if(P2.returncheckKing()) {
                                    P2.changePostion(myold,P2.GetInhand());
                                }
                                
                                
                                if(!P2.returncheckKing()) {
                                    if(P1.see_king_Check(P2))
                                        // if my king will be in check if i move
                                        //so i can't move and i will return back to old postion'
                                    {
                                        
                                        P1.checkKing(true);
                                        end_move=false;
                                        
                                        
                                        if(P1.Check_Mate_GameOver(P2)) {
                                            Box=Integer.toString(P2.GetInhand())+Integer.toString(newP.x)+Integer.toString(newP.y);
                                            GameOver();
                                            
                                            can_Send=true;
                                        }
                                        
                                        else {
                                            Box=Integer.toString(P2.GetInhand())+Integer.toString(newP.x)+Integer.toString(newP.y);
                                            CheckStatus();
                                            can_Send=true;
                                        }
                                    }
                                    
                                    
                                    if(end_move) {
                                        Box=Integer.toString(P2.GetInhand())+Integer.toString(newP.x)+Integer.toString(newP.y);
                                        ChangeTurn();
                                        can_Send=true;
                                    }
                                    
                                }
                                
                            }
                            }
                        P2.SetInhand(-1);
                        
                        repaint();
                        
                        if(can_Send&&((Iam_Server||Iam_Client))) {
                            
                            //Send_to.resume();
                            Send_move();
                            ///     Recv_from.resume();
                            
                            
                        }
                        if(GameOver)
                            JOptionPane.showConfirmDialog(null,"Check Mate\n Black won the game","Game Over",JOptionPane.DEFAULT_OPTION);
                        
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
    public boolean BoardgetPostion(int x, int y)
    
    
    {
        if(!GameOver&&Game_started) {
            if((Iam_Server&&players_turn==1)||(local)||(Iam_Client&&players_turn==2)) {
                
                int newX=x/Divide;
                int newY=y/Divide;
                newX++;
                newY++;
                
                if(newX>8||newY>8||newX<1||newY<1) {
                    repaint();
                    return false;
                    
                }
                
                if(players_turn==1&&P1.GetInhand()==-1)//Player 1
                {
                    for(int i=17;i<=32;i++) {
                        Point p=P1.returnPostion(i);
                        if(p.x==newX&&p.y==newY) {
                            P1.SetInhand(i); whenHandleAndPice(x,y);return true;}
                    }
                } else   if(players_turn==2&&P2.GetInhand()==-1)//Player 2
                {
                    for(int i=1;i<=16;i++) {
                        Point p=P2.returnPostion(i);
                        if(p.x==newX&&p.y==newY) {
                            P2.SetInhand(i); whenHandleAndPice(x,y);return true;}
                    }
                }
                
                else if(players_turn==1&&P1.GetInhand()!=-1)//Player 1
                {
                    whenHandleAndPice(x,y);
                    return true;
                } else if(players_turn==2&&P2.GetInhand()!=-1)//Player 2
                {
                    whenHandleAndPice(x,y);
                    return true;
                }
                P1.SetInhand(-1);
                move=0;
                
                return false;
                
            }
        }
        return false;
    }
    public boolean whenHandleAndPice(int x,int y) {
        
        if(players_turn==1&&P1.GetInhand()!=-1) {
            P1.changePixel(x,y,P1.GetInhand());return true;
        } else if(players_turn==2&&P2.GetInhand()!=-1) {
            P2.changePixel(x,y,P2.GetInhand());return true;
        }
        return false;
    }
    private int rowToX(int r) {
        int myx;
        int iHeight = this.getHeight();
        myx=(r*iHeight/8) - Divide;
        return myx;
    }
    private int colToY(int c) {
        int myy;
        int iWidth = getWidth();
        myy = (c*iWidth/8) - Divide;
        return myy;
    }
    
    
    
    
    private class MousewhenMove implements MouseMotionListener {
        public void mouseDragged(MouseEvent e) {
            
            int x = e.getX();
            int y = e.getY();
            if(controll_game_type(x,y)) {
                
                repaint();
            }
            
        }
        public void mouseMoved(MouseEvent e) {
            
        }
        
        
    }
    
    public boolean controll_game_type(int x,int y) {
        
        if(Iam_Server==true||Iam_Client==true&&Game_started) {
            if(Iam_Server&&players_turn==1) {
                return BoardgetPostion(x,y);
            } else if(Iam_Client&&players_turn==2) {
                return BoardgetPostion(x,y);
            } else
                return false;
        } else {
            return BoardgetPostion(x,y);
        }
        
        
        // return false;
    }
    
    
    
    private void ChangeTurn() {
        if(players_turn==1) {
            players_turn=2;
            myTool.add_to_History("White : "+P1.Tell_me_About_last_move());
            myStatus.changeStatus(" Black player turn");
            myTool.change_to_Timer2();
        }
        
        else if(players_turn==2) {
            players_turn=1;
            myTool.add_to_History("Black : "+P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
            myStatus.changeStatus(" White player turn");
        }
        
        
    }
    
    private void NetChangeTurn() {
        if(players_turn==2) {
            
            myTool.add_to_History("White : "+P1.Tell_me_About_last_move());
            myStatus.changeStatus(" Black player turn");
            myTool.change_to_Timer2();
        }
        
        else if(players_turn==1) {
            
            myTool.add_to_History("Black : "+P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
            myStatus.changeStatus(" White player turn");
        }
        
    }
    
    private void NeTGameCheckStatus() {
        if(players_turn==1) {
            
            
            myTool.add_to_History("White : "+P1.Tell_me_About_last_move());
            myTool.change_to_Timer2();
        } else if(players_turn==2) {
            
            
            myTool.add_to_History("Black : "+P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
        }
        myStatus.changeStatus(" Check! ");
    }
    
    
    private void CheckStatus() {
        if(players_turn==1) {
            
            players_turn=2;
            myTool.add_to_History("White : "+P1.Tell_me_About_last_move());
            myTool.change_to_Timer2();
        } else if(players_turn==2) {
            
            players_turn=1;
            myTool.add_to_History("Black : "+P2.Tell_me_About_last_move());
            myTool.change_to_Timer1();
        }
        
        
        myStatus.changeStatus(" Check! ");
    }
    
    
    private void GameOver() {
        
        myStatus.changeStatus(" Check Mate! ");
        
        
        
        GameOver=true;
    }
    
    
    public void Send_move() {
        out.print(Box);
        out.print("\r\n");
        out.flush();
        
    }
    
    class Recv_Thread extends Thread {
        public synchronized  void run  () {
            
            
            
            
            while(true) {
                
                try {
                    
                    Box=in.readLine();
                    
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.out.println("ERROR");
                }
                
                
                
                if(Box!=null) {
                    
                    
                    
                    
                    
                    
                    
                    int newInHand=Integer.parseInt(Box);
                    int newX=Integer.parseInt(Box);
                    int newY=Integer.parseInt(Box);
                    
                    
                    /***
                     * Operation to Get
                     *1- The # of Pice
                     *2- The Location X
                     *3- The Location Y
                     *
                     **/
                    
                    newInHand/=100;
                    newX-=(newInHand*100);
                    newX/=10;
                    newY-=(newInHand*100)+(newX*10);
                    
                    
                    if(players_turn==1) {
                        
                        
                        
                        P1.SetInhand(newInHand);
                        players_turn=2;
                        
                        P1.changePostion(new Point(newX,newY),newInHand);
                        
                        P2.Killedpiec(P1.Get_Pice_already_there_from_enemy(new Point(newX,newY),P2));
                        P2.checkKing(false);
                        
                        if(P2.see_king_Check(P1))
                            // if my king will be in check if i move
                            //so i can't move and i will return back to old postion'
                        {
                            
                            P2.checkKing(true);
                            
                            
                            
                            if(P2.Check_Mate_GameOver(P1)) {
                                System.out.println("mate");
                                GameOver();
                                
                            }
                            
                            else {
                                
                                NeTGameCheckStatus();
                                
                            }
                        } else NetChangeTurn();
                        
                        
                        P1.SetInhand(-1);
                        
                        
                    } else {
                        P2.SetInhand(newInHand);
                        P2.changePostion(new Point(newX,newY),newInHand);
                        
                        P1.Killedpiec(P2.Get_Pice_already_there_from_enemy(new Point(newX,newY),P1));
                        players_turn=1;
                        
                        P1.checkKing(false);
                        if(P1.see_king_Check(P2))
                            // if my king will be in check if i move
                            //so i can't move and i will return back to old postion'
                        {
                            
                            P1.checkKing(true);
                            
                            
                            
                            if(P1.Check_Mate_GameOver(P2)) {
                                
                                System.out.println("mate");
                                GameOver();
                                
                            }
                            
                            else {
                                
                                NeTGameCheckStatus();
                                
                            }
                        } else NetChangeTurn();
                        
                        P2.SetInhand(-1);
                    }
                    //   CheckStatus();
                    
                    repaint();
                }
                
                
            }
        }
    }
    
}


