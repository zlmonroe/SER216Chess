package GUI;

import Game.Move;
import Game.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class ChessPanel extends JPanel {
    private Rectangle2D board;
    private final int boardSize = 600;
    Player P1, P2;
    private int[][] pieceLocations;
    private final String[] pieceExtentions = {null,"wp","bp","wn","bn","wb","bb","wr","br","wq","bq","wk","bk"};
    private Point startPoint;
    private Point endPoint;
    Image dragImage;

    ChessPanel() {
        //make new players
        P1 = new Player(true);
        P2 = new Player(false);
        pieceLocations = new int[8][8];
        startPoint = new Point();
        endPoint = new Point();
        for(int i=0;i<8;i++){
            pieceLocations[i][1] = 2;
            pieceLocations[i][6] = 1;
        }
        pieceLocations[0][0] = 8;
        pieceLocations[7][0] = 8;
        pieceLocations[0][7] = 7;
        pieceLocations[7][7] = 7;

        pieceLocations[1][0] = 4;
        pieceLocations[6][0] = 4;
        pieceLocations[1][7] = 3;
        pieceLocations[6][7] = 3;

        pieceLocations[2][0] = 6;
        pieceLocations[5][0] = 6;
        pieceLocations[2][7] = 5;
        pieceLocations[5][7] = 5;

        pieceLocations[3][0] = 12;
        pieceLocations[4][0] = 10;
        pieceLocations[3][7] = 11;
        pieceLocations[4][7] = 9;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                startPoint.setLocation(e.getX()/75,e.getY()/75);
                System.out.println(startPoint.getX()+"\t"+startPoint.getY());
                try {
                    dragImage = ImageIO.read(new File("src/Game/Pieces/Icons/"+pieceExtentions[pieceLocations[(int)startPoint.getX()][(int)startPoint.getY()]]+".gif"));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint.setLocation(e.getX()/75,e.getY()/75);
                paint(getGraphics());
                getGraphics().drawImage(dragImage,(int)endPoint.getX()*75+2,(int)endPoint.getY()*75+2,null);
                System.out.println(endPoint.getX()+"\t"+endPoint.getY());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                getGraphics().drawImage(dragImage,e.getX()-35,e.getY()-35,null);
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
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
        drawPieces(pieceLocations,g2);
    }

    public void drawPieces(int[][] pieceLocations, Graphics2D g2) {
        int pieceType;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                pieceType = pieceLocations[i][j];
                if (pieceType!=0){
                    try {
                        Image img = ImageIO.read(new File("src/Game/Pieces/Icons/"+pieceExtentions[pieceType]+".gif"));
                        //add(new draggablePic(img,i,j));//.paint(g2);
                        g2.drawImage(img,600/8*i+2,600/8*j+2,null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Perform the move
     * @param move The move to perform
     */
    public void movePiece(Move move) {
        pieceLocations[move.newPoint.x][7-move.newPoint.y] = pieceLocations[move.oldPoint.x][7-move.oldPoint.y];
        pieceLocations[move.oldPoint.x][7-move.oldPoint.y] = 0;
    }

}
