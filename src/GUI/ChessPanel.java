package GUI;

import Game.ChessTimer;
import Game.Client;
import Game.Move;
import Game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

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
    ChessTimer time;

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
                    if(endPoint.getX()<8&&endPoint.getX()>=0&&endPoint.getY()<8&&endPoint.getY()>=0) {
                        dragImage.setPos((int) endPoint.getX() * 75 + 2, (int) endPoint.getY() * 75 + 2);
                        paintComponent(getGraphics());
                        drawPieces();
                        System.out.println(endPoint.getX() + "\t" + endPoint.getY());
                        startPoint.y = 7 - startPoint.y;
                        endPoint.y = 7 - endPoint.y;
                        client.sendMove(new Move(startPoint, endPoint));
                        time.pause();
                    }
                    else {
                        updatePieces();
                        paintComponent(getGraphics());
                    }
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
                    int x = e.getX();
                    int y = e.getY();
                    if(x>565)
                        x = 565;
                    else if (x<35)
                        x = 35;
                    if(y>565)
                        y = 565;
                    else if (y<35)
                        y = 35;
                    dragImage.setPos(x-35, y-35);
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
                if (pieceType != 0) {
                    img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("Icons/" + pieceExtentions[pieceType] + ".gif"));
                    pieces[i][j] = new pieceIcon(img, i, j);
                    //add(pieces[i][j]);
                } else {
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

    public String getPieceName(Point p) {
        final String[] pieceNames = {"pawn", "knight", "bishop", "rook", "queen", "king"};
        int type = (pieceLocations[p.x][7-p.y] - 1) / 2;
        if (type >= 0) {
            return ((pieceLocations[p.x][7-p.y] % 2 == 0) ? "Black " : "White ") + pieceNames[type];
        }
        return null;
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
