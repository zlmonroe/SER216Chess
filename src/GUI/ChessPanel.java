package GUI;

import Game.Client;
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

public class ChessPanel extends JPanel {
    private Rectangle2D board;
    private final int boardSize = 600;
    Player P1, P2;
    private int[][] pieceLocations;
    private final String[] pieceExtentions = {null,"wp","bp","wn","bn","wb","bb","wr","br","wq","bq","wk","bk"};
    private Point startPoint;
    private Point endPoint;
    private pieceIcon[][] pieces;
    pieceIcon dragImage;
    Client client;
    boolean isTurn;

    ChessPanel() {
        setDoubleBuffered(true);
        //make new players

        P1 = new Player(true);
        P2 = new Player(false);
        pieceLocations = new int[8][8];
        pieces = new pieceIcon[8][8];
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

        pieceLocations[3][0] = 10;
        pieceLocations[4][0] = 12;
        pieceLocations[3][7] = 9;
        pieceLocations[4][7] = 11;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                if(isTurn) {
                    startPoint.setLocation(e.getX() / 75, e.getY() / 75);
                    if((pieceLocations[(int) startPoint.getX()][(int) startPoint.getY()]%2==1&&client.isWhite)||(pieceLocations[(int) startPoint.getX()][(int) startPoint.getY()]%2==0&&!client.isWhite)) {
                        dragImage = pieces[(int) startPoint.getX()][(int) startPoint.getY()];
                    }else dragImage = null;
                    if (dragImage!=null) {
                        System.out.println("\nMove\n" + startPoint.getX() + "\t" + startPoint.getY());
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
                if(dragImage!=null&&isTurn) {
                    endPoint.setLocation(e.getX() / 75, e.getY() / 75);
                    dragImage.setPos((int) endPoint.getX() * 75 + 2, (int) endPoint.getY() * 75 + 2);
                    paintComponent(getGraphics());
                    drawPieces();
                    System.out.println(endPoint.getX() + "\t" + endPoint.getY());
                    startPoint.y = 7 - startPoint.y;
                    endPoint.y = 7 - endPoint.y;
                    client.sendMove(new Move(startPoint, endPoint));
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if(dragImage!=null&& isTurn) {
                    dragImage.setPos(e.getX() - 35, e.getY() - 35);
                    dragImage.paint(getGraphics());
                    repaint();
                }
            }
            public void mouseMoved(MouseEvent e) {

            }
        });
        updatePieces();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i+j)%2==1) {
                    g2.setColor(Color.BLUE);
                    g2.fillRect(i * (boardSize / 8), j * (boardSize / 8), boardSize / 8, boardSize / 8);
                }
                else {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(i * (boardSize / 8), j * (boardSize / 8), boardSize / 8, boardSize / 8);
                }
            }
        }
        for(int i = 0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    pieces[i][j].paint(g);
                }
            }
        }
    }

    public void updatePieces() {
        Graphics2D g2 = (Graphics2D)getGraphics();
        int pieceType;
        Image img;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                pieceType = pieceLocations[i][j];
                    try {
                        img = ImageIO.read(new File("src/Game/Pieces/Icons/"+pieceExtentions[pieceType]+".gif"));
                        pieces[i][j] = new pieceIcon(img,i,j);
                        add(pieces[i][j]);
                    } catch (IOException e) {
                        pieces[i][j] = null;
                    }

            }
        }
    }
    public void drawPieces(){
        setIgnoreRepaint(true);
        Graphics g = getGraphics();
        for(int i = 0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if (pieces[i][j] != null) {
                    pieces[i][j].paint(g);
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
        updatePieces();
        isTurn = !isTurn;
    }

    private class pieceIcon extends JComponent{
        private static final long serialVersionUID = -100287822141330157L;
        Image img;
        int x;
        int y;
        public void setPos(int x, int y){
            this.x = x;
            this.y = y;
        }

        pieceIcon(Image image, int x, int y){
            img = image;
            this.x = 75*x+2;
            this.y = 75*y+2;

        }
        public void paint(Graphics g){
            if(img!=null)
                g.drawImage(img,x,y,null);
        }
    }
}
