package GUI;

import Game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ChessPanel extends JPanel {
    private Rectangle2D board;
    private final int boardSize = 600;
    Player P1, P2;

    ChessPanel() {
        //make new players
        P1 = new Player(true);
        P2 = new Player(false);
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j)%2==1) {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(i * (boardSize / 8), j * (boardSize / 8), boardSize / 8, boardSize / 8);
                }
                else {
                    g2.setColor(Color.BLUE);
                    g2.fillRect(i * (boardSize / 8), j * (boardSize / 8), boardSize / 8, boardSize / 8);
                }
            }
        }

    }

    public void drawPieces(int[][] pieceLocations, Graphics2D g2) {

    }
}