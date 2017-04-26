package GUI;

import Game.ChessTimer;
import Game.Client;
import Game.Message;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainGUIWindow extends JFrame {
    private ToolPanel tools;
    private JLabel status;
    private ChatPanel chat;
    private ChessPanel chess;
    private StartPanel start;
    private Client client;
    private Timer timer;
    private ChessTimer chessTimer;


    MainGUIWindow() {

        //Begin GUI setup
        //client = new Client();
        Container pane = this.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; c.weighty = 1;

        tools = new ToolPanel();
        status = new JLabel();
        status.setBorder(new LineBorder(Color.CYAN, 3));
        status.setSize(300,60);
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setText("New Game");

        chat = new ChatPanel();
        chess = new ChessPanel();
        start = new StartPanel();
        chess.setSize(600,600);
        chessTimer = new ChessTimer(5*60);

        chat.sendButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                chat.textArea.append(chat.textField.getText()+"\n");
                client.sendChat(chat.textField.getText());
                chat.textField.setText(null);
            }
        });

        chat.textField.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                if( e.getKeyChar()=='\n') {
                    chat.textArea.append(chat.textField.getText()+"\n");
                    client.sendChat(chat.textField.getText());
                    chat.textField.setText(null);
                }
            }
            public void keyReleased(KeyEvent e) {
            }
        });

        start.startClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                client = new Client(start.IPAddressField.getText(), Integer.parseInt(start.PortNumField.getText()));
                if (client.isConnected) {
                    (new Thread(client)).start();
                    chess.client = client;
                    chess.isTurn = client.isWhite;
                    setTitle(client.isWhite?"You are White":"You are Black");
                    pane.remove(start);
                    c.gridx = 0;
                    c.gridy = 0;
                    c.gridheight = 2;
                    c.gridwidth = 1;
                    c.weightx = 1;
                    c.weighty = 1;
                    pane.add(chess, c);
                    repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    timer = new Timer(100, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            checkMessage();
                            chess.drawPieces();
                        }
                    });
                    timer.start();
                    chess.drawPieces();
                }
            }
        });



        c.gridx = 0; c.gridy = 0; c.gridheight = 2;
        pane.add(start, c);
        c.gridheight = 1; c.weightx = .3; c.weighty = .05;

        c.gridx = 1; c.gridy = 1; c.gridwidth = 1;
        pane.add(chat, c);
        c.gridx = 1; c.gridy = 0;
        pane.add(tools, c);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        pane.add(status, c);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        this.setSize(860, 700);
        this.setResizable(false);
        this.setVisible(true);


    }

    public static void main(String args[]) {
        new MainGUIWindow();
    }

    public void checkMessage(){
        if(!client.messages.isEmpty()) {
            Message msg = client.messages.removeFirst();
            if (msg.newMove != null){
                chess.movePiece(msg.newMove);
                repaint();
            }
            if (msg.newMessage != null) {
                chat.append(msg.newMessage);
            }
            else if (msg.newGameInfo!=null){
                remove(status);
                status.setText(msg.newGameInfo);
                add(status);
                status.repaint();
                chess.updatePieces();
                chess.repaint();
            }
            else {
                remove(status);
                status.setText("Game in progress");
                add(status);
                status.repaint();
                chess.updatePieces();
                chess.repaint();
            }

        }
    }
}
