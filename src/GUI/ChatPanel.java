package GUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/*The ChatPanel class is a custom JPanel class. Basically, they made an entirely new JPanel class to make the chat panel.
 * It has a constructor that makes the panel to their specifications, along with functions specific to a ChatPanel
 */
@SuppressWarnings("serial")
public class ChatPanel extends JPanel {
	
    private final mytextArea textArea=new mytextArea(6,20);
    private final myTextField textField=new myTextField(10);
    private final mybutton Sendbutton=new mybutton();
    private final JScrollPane TextAreaScroll=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private Socket chat_socket;
    private ServerSocket server_chat;
    private BufferedReader in1;
    private PrintWriter out1;
    private BufferedReader in2;
    private PrintWriter out2;
    private serv_chat myserv_thread=new serv_chat();
    private Socket send_socket;
    private client_chat client_thread=new client_chat();
    private boolean I_am_What;
    
    /** Creates a new instance of ChatPanel */
    /*This just specifies how the panel should look and what other components are on it*/
    public ChatPanel() {
    	//Basic panel stuff
        setSize(200,300);
        //setLocation(600,350);
        TextAreaScroll.setSize(180,190);
        TextAreaScroll.setLocation(10,0);
        setLayout(null);
        
        add(TextAreaScroll);//This is a Java class object
        add(textField);//This is a TextField class they create below
        add(Sendbutton);//This is a class they create below
        
        //I don't think this is necessary at all. Deleting it does nothing
        add(TextAreaScroll);
        add(textField);
        add(Sendbutton);
        
        //Action listener for the send button 
        Sendbutton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
            	//adds whatever was in the text field to the text area
                textArea.append("\n"+textField.getText());
                
                if(I_am_What) {//if it is the server
                    Send_text_server();
                    textField.setText(null);//clears the text field
                } else {// if it is the client
                    Send_text_chat();
                    textField.setText(null);//clears the text field
                }
                
            }
        });
        
        //Action listener for "enter" key
        textField.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
            	if( e.getKeyChar()=='\n') {//if the key pressed is te enter key, ie new line, do this
            		//adds whatever was in the text field to the text area
                    textArea.append("\n"+textField.getText());
                    if(I_am_What) {//If it is the server
                        Send_text_server();
                        textField.setText(null);
                    } else {//if it is the client
                        Send_text_chat();
                        textField.setText(null);
                    }
                    
                }
            }
            //useless fncs that are part of the interface
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
    }
    
    public void start_chat() {
    	//Basic stuff for the chat panel: Makes all of this stuff usable
        TextAreaScroll.setEnabled(true);
        textField.setEnabled(true);
        Sendbutton.setEnabled(true);
        //sets this to false because it is the client. This fnc s only called for the client. 
        I_am_What=false;
        
        try {
            send_socket = new Socket("127.0.0.1",5002);//IP Address and port hard coded in.  Port and IP that the client will be sending stuff to.
            in2=new BufferedReader(new InputStreamReader(send_socket.getInputStream())); //buffered reader for input to the client
            out2=new PrintWriter(send_socket.getOutputStream()); //print writer for output from the client
        } 
        catch (UnknownHostException ex) {
            ex.printStackTrace();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        //begins the client thread
        client_thread.start();//i believe that the placement of this outside of the try block allows the client to run even when there is no server
    }
    
    public void Send_text_chat() {
        out2.print(textField.getText()); //sends the info in the text field to the server
        out2.print("\r\n");//idk
        out2.flush();//clears the output stream
    }
    
    public void Send_text_server() {
        out1.print(textField.getText()); //sends the info in the text field to the client
        out1.print("\r\n");//idk
        out1.flush();//clears the output stream
    }
    
    public void listen_chat() {
    	//Basic stuff for the chat panel: Makes all of this stuff usable
        TextAreaScroll.setEnabled(true);
        textField.setEnabled(true);
        Sendbutton.setEnabled(true);
        //sets this to false because it is the server. This fnc s only called for the server.
        I_am_What=true;
        try {
            server_chat=new ServerSocket(5002);//This is the port that the server will be expecting input from
            chat_socket=server_chat.accept();//Client socket ffrom the server's perspective
            in1=new BufferedReader(new InputStreamReader( chat_socket.getInputStream()));//buffered reader for input to the client
            out1=new PrintWriter(chat_socket.getOutputStream());//print writer for output from the client
            //begins the sever thread
            myserv_thread.start(); 
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    class client_chat extends Thread {
        public void run() {
            String receive = null;
            while(true) {
                try {//tries to read the line from the server
                    receive=in2.readLine();// this returns null if there is nothing
                } catch (IOException ex) {//ignores any exceptions
                    ex.printStackTrace();
                }
                
                if(receive!=null) {//if it is not null, print this on the test area of the chat panel
                    textArea.append("\n"+"Other: "+receive);
                }
            }
        }
    }
    class serv_chat extends Thread {
        public void run() {
            String receive = null;
            while(true) {
                try {//tries to read the line from the server
                    receive=in1.readLine();// this returns null if there is nothing
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if(receive!=null) {//if it is not null, print this on the test area of the chat panel
                    textArea.append("\n"+"Other: "+receive );
                }
            }
        }
    }
}
//This is their version of the JTextArea, I don't think we need it because it doesn't add extra functionality
class mytextArea extends JTextArea {
    mytextArea(int Row_num,int Col_num) {
        super(Row_num,Col_num);
        
          /* setSize(130,150);
           setLocation(30,0);*/
        setEditable(false);
        // TextAreaScroll =new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // this.add(TextAreaScroll);
        setBorder(TextBorder);
    }
    private final TitledBorder TextBorder=new TitledBorder("Chat History");
}
//This is their version of the JButton, I don't think we need it because it doesn't add extra functionality
class mybutton extends JButton {
    mybutton() {
        setSize(80,30);
        setLocation(50,230);
        setText("Send");
    }
}
//This is the class they created for the TextFeild. I don't think it is necessary because it doesn't add any new functionality to TextField
//This field is the one you enter info for the chat box
class myTextField extends JTextField {
	private final JScrollPane TextAreaScroll=new JScrollPane();//I don't think this is necessary
    myTextField(int FiledLength) {
    	//the following three line can be done outside of the contrustor, I don't think they needed to make this class at all.
        super(FiledLength);
        setSize(180,20);
        setLocation(10,200);
        
        add(TextAreaScroll);//I don't know why this exists because it doesn't show up
        this.setToolTipText("Write Text Here");//this shows up when you hover over
    }
}
