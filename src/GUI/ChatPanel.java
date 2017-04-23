package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Created by tjcup on 4/22/2017.
 */
public class ChatPanel extends JPanel{
    private JPanel chatBox;
    public JTextArea textArea;
    public JTextField textField;
    public JButton sendButton;
    private JScrollPane textAreaScroll;

    public ChatPanel() {
        textArea = new JTextArea(6,20);
        textField = new JTextField(10);
        sendButton = new JButton();
        textAreaScroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setBorder(new TitledBorder("Chat History"));
        textArea.setEnabled(false);
        setSize(200, 300);
        sendButton.setSize(80,30);
        sendButton.setLocation(50,230);
        sendButton.setText("Send");
        textField.setSize(180,20);
        textField.setLocation(10,200);
        //setLocation(600,350);
        textAreaScroll.setSize(180, 190);
        textAreaScroll.setLocation(10, 0);
        setLayout(null);
        add(textAreaScroll);//This is a Java class object
        add(textField);//This is a TextField class they create below
        add(sendButton);//This is a class they create below
    }
    public void append(String message){
        textArea.append("Opponent: "+message+"\n");
    }
}
