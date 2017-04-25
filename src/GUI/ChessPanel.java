package GUI;

import Game.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessPanel extends JPanel {
    private Rectangle2D board;
    private final int boardSize = 600;
    Player P1, P2;
    private int[][] pieces;
    private final String[] pieceExtentions = {null,"wp","bp","wn","bn","wb","bb","wr","br","wq","bq","wk","bk"};

    ChessPanel() {
        //make new players
        P1 = new Player(true);
        P2 = new Player(false);
        pieces = new int[8][8];
        pieces[7][7] = 4;
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
        drawPieces(pieces,g2);
    }

    public void drawPieces(int[][] pieceLocations, Graphics2D g2) {
        int pieceType;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                pieceType = pieceLocations[i][j];
                if (pieceType!=0){
                    try {
                        BufferedImage img = ImageIO.read(new File("src/Game/Pieces/Icons/"+pieceExtentions[pieceType]+".gif"));

                        g2.drawImage(img,600/8*i+600/32,600/8*j+600/64,null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
