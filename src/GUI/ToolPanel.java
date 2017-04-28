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
import javax.swing.border.LineBorder;

public class ToolPanel extends JPanel {

    //the panel with the history of piece moves in it
    private final HistoryList historyList = new HistoryList();
    //the two chess timer objects that need to be replaced
    private long whiteTime;
    private long blackTime;
    JLabel JLblack;
    JLabel JLwhite;
    long offsetStart;

    public ToolPanel() {
        setLayout(null);

        JLblack = new JLabel("  Black Time:");
        JLblack.setSize(150, 25);
        JLblack.setLocation(20, 34);
        JLblack.setBackground(new Color(230, 12, 0));
        JLblack.setOpaque(true);

        JLwhite = new JLabel("  White Time:");
        JLwhite.setSize(150, 25);
        JLwhite.setLocation(20, 230);
        JLwhite.setBackground(new Color(230, 12, 0));
        JLwhite.setOpaque(true);

        add(JLblack);
        add(JLwhite);

        JScrollPane historyScroll = new JScrollPane(historyList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        historyScroll.setSize(150, 150);
        historyScroll.setLocation(20, 70);
        add(historyScroll);
    }

    public void add_to_History(Object newItem) {
        historyList.addToList(newItem);
    }

    public void start_Again() {
        JLblack.setText("  Black Time:");
        JLwhite.setText("  White Time:");
        historyList.cleanList();
        historyList.addToList("Move History:");

    }
    public void updateTimer(boolean isWhite, String timeString){
        if (isWhite){
            JLwhite.setText("White Timer: "+timeString);
            //JLwhite.repaint();
        }
        else{
            JLblack.setText("Black Timer: "+timeString);
            //JLblack.repaint();
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private class HistoryList extends JList {
        private DefaultListModel listModel;

        HistoryList() {
            listModel = new DefaultListModel();
            this.setBackground(Color.ORANGE);
            setModel(listModel);
            listModel.addElement("Move History:");
        }

        public void cleanList() {
            listModel.clear();
        }

        public void addToList(Object newItem) {
            listModel.addElement(newItem);
        }
    }
}