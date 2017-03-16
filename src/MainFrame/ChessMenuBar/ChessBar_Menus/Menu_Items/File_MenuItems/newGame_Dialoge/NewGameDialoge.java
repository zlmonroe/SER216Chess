

package MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.File_MenuItems.newGame_Dialoge;

import MainFrame.ChessMenuBar.Chess_MainMenuBar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import MainFrame.ChessFrame.MainFrame;
import MainFrame.ChessFrame.MainPanel;
import MainFrame.ChessFrame.StatusPanel;
import MainFrame.ChessFrame.ToolPanel;
import MainFrame.ChessFrame.ChatPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;


public class NewGameDialoge extends JDialog {
    
    /** Creates a new instance of NewGameDialoge */
    public NewGameDialoge( final MainFrame ff) {
        super(ff, "New Game", true);
        
        setSize(300,350);
        setLocation(100,100);
        panel.setLayout(null);
        Container cp=getContentPane();
        
        panel.setSize(300,300);
        
        button1=new JButton("Start");
        button2=new JButton("Cancel");
        
        button1.setSize(80,24);
        button2.setSize(80,24);
        
        button1.setLocation(50,250);
        button2.setLocation(130,250);
        
        myCom.setSize(80,20);
        myCom.setLocation(50,50);
        
        type_game.setSize(80,24);
        type_game.setLocation(40,20);
        
        IP_address.setSize(80,24);
        IP_address.setLocation(50,100);
        
        ip_text.setLocation(130,100);
        ip_text.setSize(150,24);
        
        Port_number.setSize(80,24);
        Port_number.setLocation(50,130);
        
        port_text.setLocation(130,130);
        port_text.setSize(150,24);
        
        ip_text.setEnabled(false);
        port_text.setEnabled(false);
        IP_address.setEnabled(false);
        Port_number.setEnabled(false);
        
        As_server.setSize(80,24);
        As_server.setLocation(50,180);
        As_client.setSize(80,24);
        As_client.setLocation(150,180);
        
        
        Buttongroup.add(As_server);
        Buttongroup.add(As_client);
        
        Buttongroup.setSelected(As_server.getModel(),true);
        
        As_server.setEnabled(false);
        As_client.setEnabled(false);
        
        mynewFrame=ff;
        
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String selected=(String) myCom.getSelectedItem();
                
                
                
                
                if(selected=="Local")
                    mynewFrame.start_Again();
                else {
                    if(Buttongroup.getSelection()==As_server.getModel())
                        mynewFrame.start_As_Server();
                    else
                        mynewFrame.start_As_Client();
                }
                
                dispose();
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        
        panel.setLayout(null);
        
        
        panel.add(myCom);
        panel.add(button1);
        panel.add(button2);
        panel.add(type_game);
        panel.add(IP_address);
        panel.add(ip_text);
        panel.add(Port_number);
        panel.add(port_text);
        panel.add( As_server);
        panel.add(As_client);
        
        cp.add(panel);
        
        ip_text.setText("127.0.0.1");
        port_text.setText("5000");
        
        myCom.addItemListener(new MyItemLestin());
        myCom.addItem("Local");
        myCom.addItem("Via a net");
        
        
    }
    class MyItemLestin implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            String selected=((JComboBox)e.getSource()).getSelectedItem().toString();
            
            if(selected=="Local") {
                
                ip_text.setEnabled(false);
                port_text.setEnabled(false);
                
                IP_address.setEnabled(false);
                Port_number.setEnabled(false);
                As_server.setEnabled(false);
                As_client.setEnabled(false);
                
            } else if(selected=="Via a net") {
                ip_text.setEnabled(true);
                port_text.setEnabled(true);
                IP_address.setEnabled(true);
                Port_number.setEnabled(true);
                
                As_server.setEnabled(true);
                As_client.setEnabled(true);
                
                
            }
        }
        
    }
    public String GetIpAddress() {
        return ip_text.getText();
    }
    
    public String GetPortnumber() {
        return port_text.getText();
    }
    
    private final JButton button1;
    private final JButton button2;
    private final MyComboBox myCom=new MyComboBox();
    private final JPanel panel=new JPanel();
    
    private final JLabel type_game=new JLabel(" Game Type: ");
    private final JLabel IP_address=new JLabel(" IP Address : ");
    private final JLabel Port_number=new JLabel(" Port : ");
    private final JTextField ip_text=new JTextField(12);
    private final JTextField port_text=new JTextField(5);
    private final JRadioButton As_server=new JRadioButton("Server");
    private final JRadioButton As_client=new JRadioButton("Client");
    private MainFrame mynewFrame;
    private ButtonGroup Buttongroup = new ButtonGroup();
    
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        
    }
    
}

class MyComboBox extends JComboBox {
    MyComboBox() {
        
        
    }
}

