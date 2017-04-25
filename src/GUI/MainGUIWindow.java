package GUI;

import Game.ChessTimer;
import Game.Client;
import Game.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MainGUIWindow extends JFrame {
    private ToolPanel tools;
    private StatusPanel status;
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
        status = new StatusPanel();
        chat = new ChatPanel();
        chess = new ChessPanel();
        start = new StartPanel();
        chessTimer = new ChessTimer(5*60);

        chat.sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chat.textArea.append(chat.textField.getText()+"\n");
                client.send(chat.textField.getText());
                chat.textField.setText(null);
            }
        });

        chat.textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if( e.getKeyChar()=='\n') {
                    chat.textArea.append(chat.textField.getText()+"\n");
                    client.send(chat.textField.getText());
                    chat.textField.setText(null);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        start.startClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                client= new Client(start.IPAddressField.getText(),Integer.parseInt(start.PortNumField.getText()));
                (new Thread(client)).start();
                pane.remove(start);
                c.gridx = 0; c.gridy = 0; c.gridheight = 2;c.gridwidth = 1; c.weightx=1; c.weighty=1;
                pane.add(chess, c);
                repaint();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                timer = new Timer(100, new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        loop();
                    }
                });
                timer.start();
            }
        });



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

        this.setSize(860, 700);
        this.setResizable(false);
        this.setVisible(true);


    }

    public static void main(String args[]) {
        new MainGUIWindow();
    }

    public void loop(){
        //while (true){
            if(!client.messages.isEmpty()) {
                Message msg = client.messages.removeFirst();
                if (msg.newMessage != null)
                    chat.append(msg.newMessage);
                if (msg.newMove != null)
                    chess.movePiece(msg.newMove);
            }
            paint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        //}
    }
    private void paint(){
        this.repaint();
        for(Component c: getComponents())
            c.repaint();
    }
}
