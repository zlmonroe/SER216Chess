package MainFrame.ChessFrame;

import MainFrame.ChessMenuBar.Chess_MainMenuBar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final Chess_MainMenuBar MyChessBar;
    public final ToolPanel Toolpanel;
    private final StatusPanel statusPanel;

    private final MainPanel Mainpanel;
    private final ChatPanel Chatpanel;
    private Container contentPane;

    public MainFrame() {

        Toolpanel = new ToolPanel();
        statusPanel = new StatusPanel();//Finished commenting!!
        Mainpanel = new MainPanel(Toolpanel, statusPanel);
        Chatpanel = new ChatPanel();//Chat panel has been commented!!
        contentPane = getContentPane();

        setTitle("Chess Game");
        setSize(800, 700);
        setResizable(false);

        contentPane.setLayout(null);
        contentPane.add(Chatpanel);
        contentPane.add(statusPanel);
        contentPane.add(Toolpanel);

        MyChessBar = new Chess_MainMenuBar(this);

        setJMenuBar(MyChessBar);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void start_Again() {
        Mainpanel.start_Again();
        contentPane.add(Mainpanel);
    }

    public void start_As_Server() {
        Mainpanel.start_As_Server(MyChessBar.getIpAddress(), MyChessBar.getPortnumber(), Chatpanel);

        contentPane.add(Mainpanel);

        setTitle("Server player");

    }

    public void start_As_Client() {

        Mainpanel.start_As_Client(MyChessBar.getIpAddress(), MyChessBar.getPortnumber(), Chatpanel);

        contentPane.add(Mainpanel);
        setTitle("Client player");
    }
}
