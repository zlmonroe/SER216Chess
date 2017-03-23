
package MainFrame.ChessFrame;

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
    private final myTextFiled TextFiled=new myTextFiled(10);
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
        setSize(200,300);
        setLocation(600,350);
        TextAreaScroll.setSize(180,190);
        TextAreaScroll.setLocation(10,0);
        
        setLayout(null);
        
        add(TextAreaScroll);//This is a Java class object
        add(TextFiled);//This is a TextField class they create below
        add(Sendbutton);//This is a class they create below
        
        add(TextAreaScroll);
        add(TextFiled);
        add(Sendbutton);
        
        Sendbutton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                textArea.append("\n"+TextFiled.getText());
                if(I_am_What) {
                    Send_text_server();
                    TextFiled.setText(null);
                } else {
                    Send_text_chat();
                    TextFiled.setText(null);
                }
                
            }
        });
        
        TextFiled.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                //System.out.println("okdddd  "+e.KEY_PRESSED+"  "+e.VK_PAGE_DOWN);
                
                
                if( e.getKeyChar()=='\n') {
                    textArea.append("\n"+TextFiled.getText());
                    
                    if(I_am_What) {
                        Send_text_server();
                        TextFiled.setText(null);
                    } else {
                        Send_text_chat();
                        TextFiled.setText(null);
                    }
                    
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
        //add(chatPanelScroll);
    }
    public void start_chat() {
        TextAreaScroll.setEnabled(true);
        TextFiled.setEnabled(true);
        Sendbutton.setEnabled(true);
        I_am_What=false;
        try {
            send_socket=new Socket("127.0.0.1",5002);
            in2=new BufferedReader(new InputStreamReader(send_socket.getInputStream()));
            out2=new PrintWriter(send_socket.getOutputStream());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        client_thread.start();
    }
    public void Send_text_chat() {
        out2.print(TextFiled.getText());
        out2.print("\r\n");
        
        out2.flush();
        
    }
    public void Send_text_server() {
        out1.print(TextFiled.getText());
        out1.print("\r\n");
        out1.flush();
    }
    public void listen_chat() {
        
        TextAreaScroll.setEnabled(true);
        TextFiled.setEnabled(true);
        Sendbutton.setEnabled(true);
        
        I_am_What=true;
        try {
            
            server_chat=new ServerSocket(5002);
            
            chat_socket=server_chat.accept();
            
            in1=new BufferedReader(new InputStreamReader( chat_socket.getInputStream()));
            
            out1=new PrintWriter(chat_socket.getOutputStream());
            
            //chat_socket.setSoTimeout(10000);
            
            myserv_thread.start();
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
    class client_chat extends Thread {
        public void run() {
            String receive = null;
            while(true) {
                try {
                    receive=in2.readLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                if(receive!=null) {
                    textArea.append("\n"+"Other: "+receive);
                }
            }
        }
    }
    class serv_chat extends Thread {
        public void run() {
            String receive = null;
            while(true) {
                try {
                    receive=in1.readLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                if(receive!=null) {
                    
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
class myTextFiled extends JTextField {
	private final JScrollPane TextAreaScroll=new JScrollPane();//I don't think this is necessary
    myTextFiled(int FiledLength) {
    	//the following three line can be done outside of the contrustor, I don't think they needed to make this class at all.
        super(FiledLength);
        setSize(180,20);
        setLocation(10,200);
        
        add(TextAreaScroll);//I don't know why this exists because it doesn't show up
        this.setToolTipText("Write Text Here");//this shows up when you hover over
        
    }
}
