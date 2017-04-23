package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tjcup on 4/21/2017.
 */
public class StartPanel extends JPanel {
    private JLabel label1;
    private JLabel label2;
    public JTextField IPAddressField;
    public JTextField PortNumField;
    public JButton startClient;

    public StartPanel(){
        label1 = new JLabel("Server IP Address");
        label2 = new JLabel("Port Number");
        IPAddressField = new JTextField();
        IPAddressField.setText("localhost");
        PortNumField = new JTextField();
        PortNumField.setText("5000");
        startClient = new JButton("Start Client");
        //this.setSize(300,300);

        IPAddressField.setSize(120,20);
        PortNumField .setSize(80,20);
        label1.setSize(105,20);
        label2.setSize(75,20);
        startClient.setSize(120,20);
        label1.setLocation(248,240);
        IPAddressField.setLocation(240,260);
        label2.setLocation(263,300);
        PortNumField.setLocation(260,320);
        startClient.setLocation(240,360);
        setLayout(null);
        add(label1);
        add(IPAddressField);
        add(label2);
        add(PortNumField);
        add(startClient);
        setBackground(Color.LIGHT_GRAY);
        setVisible(true);

    }

}
