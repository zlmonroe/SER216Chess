package GUI;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import java.awt.*;

import Game.OldPlayerClass;
import Game.Pieces.*;


/**
 * Created by zachary on 4/11/2017.
 */
public class ChessPanel extends JPanel {
    private Rectangle2D board;
    private final int boardSize = 600;
    OldPlayerClass P1, P2;

    ChessPanel() {
        //make new players
        P1 = new OldPlayerClass(P2, true);
        P2 = new OldPlayerClass(P1, false);
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

        drawPieces(P1, g2);
        drawPieces(P2, g2);
    }

    public void drawPieces(OldPlayerClass oldPlayerClass, Graphics2D g2) {
        for (Piece p : oldPlayerClass.pieces) {
            p.setPixels(boardSize / 8 * p.returnX(), boardSize / 8 * p.returnY());
            int x = p.getPixelX(), y = p.getPixelY();
            Image img = p.returnPieceImage();
            g2.drawImage(img, x + boardSize / 40, y, boardSize / 13, boardSize / 10, this);
        }
    }
}
