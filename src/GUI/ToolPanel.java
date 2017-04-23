package GUI;

import Game.ChessTimer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ToolPanel extends JPanel {

    private final HistoryList historyList = new HistoryList();
    private ChessTimer chessTimer1;
    private ChessTimer chessTimer2;

    public ToolPanel() {

        setSize(200, 350);
        setLocation(600, 0);
        setLayout(null);

        JTextField JLturn1 = new JTextField(" P2  Turn ");
        JLturn1.setSize(60, 25);
        JLturn1.setLocation(20, 10);
        JLabel Screen1 = new JLabel();
        Screen1.setSize(100, 25);
        Screen1.setLocation(90, 25);

        JTextField JLblack = new JTextField("  Black ");
        JLblack.setSize(60, 25);
        JLblack.setLocation(20, 34);

        JLturn1.setEnabled(false);
        JLturn1.setBackground(Color.ORANGE);
        JLturn1.setDisabledTextColor(Color.BLACK);
        JLturn1.setFont(new Font("Arial", Font.BOLD, 12));

        JLblack.setEnabled(false);
        JLblack.setBackground(new Color(230, 12, 0));
        JLblack.setFont(new Font("Arial", Font.BOLD, 12));
        JLblack.setDisabledTextColor(Color.BLACK);

        JTextField JLturn2 = new JTextField(" P1  Turn ");
        JLturn2.setSize(60, 25);
        JLturn2.setLocation(20, 254);
        JLabel Screen2 = new JLabel();
        Screen2.setSize(100, 25);
        Screen2.setLocation(90, 254);

        JTextField JLwhite = new JTextField("  White ");
        JLwhite.setSize(60, 25);
        JLwhite.setLocation(20, 230);

        JLturn2.setEnabled(false);
        JLturn2.setBackground(Color.ORANGE);
        JLturn2.setDisabledTextColor(Color.BLACK);
        JLturn2.setFont(new Font("Arial", Font.BOLD, 12));

        JLwhite.setEnabled(false);
        JLwhite.setBackground(new Color(230, 12, 0));
        JLwhite.setFont(new Font("Arial", Font.BOLD, 12));
        JLwhite.setDisabledTextColor(Color.BLACK);

        add(JLturn1);
        add(JLblack);
        add(JLturn2);
        add(JLwhite);
        add(Screen1);
        add(Screen2);

        JScrollPane historyScroll = new JScrollPane(historyList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        historyScroll.setSize(150, 150);
        historyScroll.setLocation(20, 70);
        add(historyScroll);
    }

    public void add_to_History(Object newItem) {
        historyList.addElemen_tolist(newItem);
    }

    public void start_Again() {
        chessTimer1 = new ChessTimer(300);
        chessTimer2 = new ChessTimer(300);

        chessTimer1.start();
        chessTimer2.start();
        chessTimer2.pause();

        historyList.clean_list();
        historyList.addElemen_tolist("OldPlayerClass: New Moves");

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class HistoryList extends JList {
        HistoryList() {
            this.setBackground(Color.ORANGE);
            setModel(listModel);
            listModel.addElement("OldPlayerClass: New Moves");
        }

        public void clean_list() {
            listModel.clear();
        }

        public void addElemen_tolist(Object newItem) {
            listModel.addElement(newItem);
        }

        private DefaultListModel listModel = new DefaultListModel();
    }
}
