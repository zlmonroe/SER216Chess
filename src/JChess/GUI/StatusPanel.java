
package JChess.GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class StatusPanel extends JPanel {
    //this class is for the box with the black border and red text that appears at the bottm of the screen
    private JLabel statusLabel = new JLabel();
    private LineBorder LabelBorder = new LineBorder(Color.BLACK.brighter(), 2);

    /**
     * Creates a new instance of StatusPanel
     */
    public StatusPanel() {
        //info for the panel itself
        setSize(580, 30);
        setLocation(10, 610);
        setLayout(null);

        //infor for the label within the panel
        statusLabel.setSize(570, 25);
        statusLabel.setLocation(5, 5);
        statusLabel.setText(" Start New Game ");
        statusLabel.setBackground(Color.lightGray);
        statusLabel.setFont(new Font("Aril", Font.BOLD, 11));
        statusLabel.setForeground(Color.RED.brighter());
        statusLabel.setBorder(LabelBorder);
        add(statusLabel);
    }

    public void start_Again() {//called when the game actually begins
        statusLabel.setText("  Game Started ");
    }

    public void changeStatus(Object str) {//fnc that changes what it says in reference to players turn (and possibly for end game)
        statusLabel.setText((String) str);
    }
}
