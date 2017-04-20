package GUI;
import javax.swing.*;
import java.awt.*;

/**
 * Created by zachary on 4/6/2017.
 */
public class MainGUIWindow extends JFrame {
    ToolPanel tools;
    StatusPanel status;
    ChatPanel chat;
    ChessPanel chess;

    MainGUIWindow() {

        //Begin GUI setup
        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; c.weighty = 1;

        tools = new ToolPanel();
        status = new StatusPanel();
        chat = new ChatPanel();
        chess = new ChessPanel();

        c.gridx = 0; c.gridy = 0; c.gridheight = 2;
        pane.add(chess, c);
        c.gridx = 0; c.gridy = 2; c.gridheight = 1; c.gridwidth = 3; c.weightx = .3; c.weighty = .05;
        pane.add(status, c);
        c.gridx = 1; c.gridy = 1; c.gridwidth = 1;
        pane.add(chat, c);
        c.gridx = 1; c.gridy = 0;
        pane.add(tools, c);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setSize(800, 700);
        this.setResizable(false);
        this.setVisible(true);

    }

    public static void main(String args[]) {
        new MainGUIWindow();
    }
}
