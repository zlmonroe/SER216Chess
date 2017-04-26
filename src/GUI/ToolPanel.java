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
    private ChessTimer chessTimer1;
    private ChessTimer chessTimer2;

    public ToolPanel() {
        setLayout(null);

        JLabel JLblack = new JLabel("  Black ");
        JLblack.setSize(60, 25);
        JLblack.setLocation(20, 34);
        JLblack.setBackground(new Color(230, 12, 0));
        JLblack.setOpaque(true);

        JLabel JLwhite = new JLabel("  White ");
        JLwhite.setSize(60, 25);
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
        chessTimer1 = new ChessTimer(300);
        chessTimer2 = new ChessTimer(300);

        chessTimer1.start();
        chessTimer2.start();
        chessTimer2.pause();

        historyList.cleanList();
        historyList.addToList("Move History:");

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