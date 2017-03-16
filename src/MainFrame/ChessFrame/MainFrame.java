

package MainFrame.ChessFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.BevelBorder;
import MainFrame.ChessMenuBar.Chess_MainMenuBar;
import MainFrame.ChessFrame.ChatPanel;
import MainFrame.ChessFrame.StatusPanel;
import javax.swing.JScrollPane;


public class MainFrame extends JFrame {
    
    
    
    public MainFrame() {
        setTitle("Chess Game");
        setSize(800,700);
        setResizable(false);
        
        contentPane.setLayout(null);
        contentPane.add(Chatpanel);
        contentPane.add(Satuspanel);
        contentPane.add(Toolpanel);
        
        MyChessBar=new Chess_MainMenuBar(this);
        
        setJMenuBar(MyChessBar);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public void start_Again() {
        Mainpanel.start_Again();
        
        
        
        
        contentPane.add(Mainpanel);
        
        
        
        
        
        
    }
    
    public void start_As_Server() {
        Mainpanel.start_As_Server(MyChessBar.getIpAddress(),MyChessBar.getPortnumber(),Chatpanel);
        
        contentPane.add(Mainpanel);
        
        setTitle("Server player");
        
    }
    public void start_As_Client() {
        
        Mainpanel.start_As_Client(MyChessBar.getIpAddress(),MyChessBar.getPortnumber(),Chatpanel);
        
        
        contentPane.add(Mainpanel);
        setTitle("Client player");
    }
    
    private final Chess_MainMenuBar  MyChessBar;
    public final ToolPanel Toolpanel=new ToolPanel();
    private final StatusPanel Satuspanel=new StatusPanel();
    
    private final MainPanel Mainpanel=new MainPanel(Toolpanel,Satuspanel);
    private final ChatPanel Chatpanel=new ChatPanel();
    private  Container contentPane=getContentPane();
    
}



