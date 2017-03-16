
package MainFrame.ChessFrame.players;


import MainFrame.ChessFrame.players.Pieces.Castle;
import MainFrame.ChessFrame.players.Pieces.Elephent;
import MainFrame.ChessFrame.players.Pieces.Horse;
import MainFrame.ChessFrame.players.Pieces.Queen;
import MainFrame.ChessFrame.players.Pieces.Solider;
import MainFrame.ChessFrame.players.Pieces.king;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.CheckboxMenuItem;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.PrintJob;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.InvalidDnDOperationException;
import java.awt.dnd.peer.DragSourceContextPeer;
import java.awt.im.InputMethodHighlight;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.peer.ButtonPeer;
import java.awt.peer.CanvasPeer;
import java.awt.peer.CheckboxMenuItemPeer;
import java.awt.peer.CheckboxPeer;
import java.awt.peer.ChoicePeer;
import java.awt.peer.DialogPeer;
import java.awt.peer.FileDialogPeer;
import java.awt.peer.FontPeer;
import java.awt.peer.FramePeer;
import java.awt.peer.LabelPeer;
import java.awt.peer.ListPeer;
import java.awt.peer.MenuBarPeer;
import java.awt.peer.MenuItemPeer;
import java.awt.peer.MenuPeer;
import java.awt.peer.PanelPeer;
import java.awt.peer.PopupMenuPeer;
import java.awt.peer.ScrollPanePeer;
import java.awt.peer.ScrollbarPeer;
import java.awt.peer.TextAreaPeer;
import java.awt.peer.TextFieldPeer;
import java.awt.peer.WindowPeer;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;


public class player1 {
    
    /** Creates a new instance of player1 */
    public Castle WC1;
    public Castle WC2;
    public Horse WH1;
    public Horse WH2;
    public Queen WQ;
    public Elephent WE1;
    public Elephent WE2;
    public Solider[] WS=new Solider[8];
    public king WK;
    private int inHand=-1;
    private boolean kingischeck=false;
    private int choosenOne;
    String Color="white";
    private Point other;
    int ate_to_protect;
    
    
    
    public player1() {
        String fileSeparator = new String(System.getProperty("file.separator"));
        WC1=new Castle("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wr.gif",8,8);
        WC2=new Castle("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wr.gif",1,8);
        WH1=new Horse("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wn.gif",2,8);
        WH2=new Horse("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wn.gif",7,8);
        WE1=new Elephent("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wb.gif",3,8);
        WE2=new Elephent("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wb.gif",6,8);
        WQ=new Queen("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wq.gif",4,8);
        WK=new king("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wk.gif",5,8);
        int j=1;
        for(int  i=0;i<=7;i++,j++) {
            WS[i]=new Solider("src" + fileSeparator + "Icons" + fileSeparator + "Player1Icons" +fileSeparator + "wp.gif",j,7);
        }
    }
    public Point returnPostion(int i) {
        
        switch(i) {
            
            case 17:return WC1.returnPostion();
            case 18:return WC2.returnPostion();
            case 19:return WH1.returnPostion();
            case 20:return WH2.returnPostion();
            case 21:return WE1.returnPostion();
            case 22:return WE2.returnPostion();
            case 23:return WQ.returnPostion();
            case 24:return WK.returnPostion();
            case 25:return  WS[0].returnPostion();
            case 26:return  WS[1].returnPostion();
            case 27:return  WS[2].returnPostion();
            case 28:return  WS[3].returnPostion();
            case 29:return  WS[4].returnPostion();
            case 30:return  WS[5].returnPostion();
            case 31:return  WS[6].returnPostion();
            case 32:return  WS[7].returnPostion();
        }
        return new Point(-1,-1);
    }
    public Point returnOldPostion(int i) {
        
        switch(i) {
            
            case 17:return WC1.returnOld();
            case 18:return WC2.returnOld();
            case 19:return WH1.returnOld();
            case 20:return WH2.returnOld();
            case 21:return WE1.returnOld();
            case 22:return WE2.returnOld();
            case 23:return WQ.returnOld();
            case 24:return WK.returnOld();
            case 25:return  WS[0].returnOld();
            case 26:return  WS[1].returnOld();
            case 27:return  WS[2].returnOld();
            case 28:return  WS[3].returnOld();
            case 29:return  WS[4].returnOld();
            case 30:return  WS[5].returnOld();
            case 31:return  WS[6].returnOld();
            case 32:return  WS[7].returnOld();
        }
        return new Point(-1,-1);
    }
    public Image returnIconImage(int i) {
        
        switch(i) {
            
            case 17:
            {return WC1.returnPieceImage();}
            case 18:
            {return WC2.returnPieceImage();}
            case 19:
            {return WH1.returnPieceImage();}
            case 20:
            {return WH2.returnPieceImage();}
            case 21:return WE1.returnPieceImage();
            case 22:return WE2.returnPieceImage();
            case 23:return WQ.returnPieceImage();
            case 24:return WK.returnPieceImage();
            case 25:return  WS[0].returnPieceImage();
            case 26:return  WS[1].returnPieceImage();
            case 27:return  WS[2].returnPieceImage();
            case 28:return  WS[3].returnPieceImage();
            case 29:return  WS[4].returnPieceImage();
            case 30:return  WS[5].returnPieceImage();
            case 31:return  WS[6].returnPieceImage();
            case 32:return  WS[7].returnPieceImage();
        }
        return null;
    }
    public void changePostion(Point newPoint,int i) {
        switch(i) {
            
            case 17:WC1.setPoint(newPoint);break;
            case 18:WC2.setPoint(newPoint);break;
            case 19:WH1.setPoint(newPoint);break;
            case 20:WH2.setPoint(newPoint);break;
            case 21: WE1.setPoint(newPoint);break;
            case 22: WE2.setPoint(newPoint);break;
            case 23: WQ.setPoint(newPoint);break;
            case 24: WK.setPoint(newPoint);break;
            case 25:  WS[0].setPoint(newPoint);break;
            case 26:  WS[1].setPoint(newPoint);break;
            case 27:  WS[2].setPoint(newPoint);break;
            case 28:  WS[3].setPoint(newPoint);break;
            case 29:  WS[4].setPoint(newPoint);break;
            case 30:  WS[5].setPoint(newPoint);break;
            case 31:  WS[6].setPoint(newPoint);break;
            case 32:  WS[7].setPoint(newPoint);break;
        }
        
    }
    public void changePostionToOld(Point newPoint,int i) {
        switch(i) {
            
            case 17:WC1.toOld(newPoint);break;
            case 18:WC2.toOld(newPoint);break;
            case 19:WH1.toOld(newPoint);break;
            case 20:WH2.toOld(newPoint);break;
            case 21: WE1.toOld(newPoint);break;
            case 22: WE2.toOld(newPoint);break;
            case 23: WQ.toOld(newPoint);break;
            case 24: WK.toOld(newPoint);break;
            case 25:  WS[0].toOld(newPoint);break;
            case 26:  WS[1].toOld(newPoint);break;
            case 27:  WS[2].toOld(newPoint);break;
            case 28:  WS[3].toOld(newPoint);break;
            case 29:  WS[4].toOld(newPoint);break;
            case 30:  WS[5].toOld(newPoint);break;
            case 31:  WS[6].toOld(newPoint);break;
            case 32:  WS[7].toOld(newPoint);break;
        }
    }
    
    public void changePixel(int newPixelX,int newPixelY,int i) {
        choosenOne=i;
        switch(choosenOne) {
            
            case 17:WC1.setPixels(newPixelX,newPixelY);break;
            case 18:WC2.setPixels(newPixelX,newPixelY);break;
            case 19:WH1.setPixels(newPixelX,newPixelY);break;
            case 20:WH2.setPixels(newPixelX,newPixelY);break;
            case 21: WE1.setPixels(newPixelX,newPixelY);break;
            case 22: WE2.setPixels(newPixelX,newPixelY);break;
            case 23: WQ.setPixels(newPixelX,newPixelY);break;
            case 24: WK.setPixels(newPixelX,newPixelY);break;
            case 25:  WS[0].setPixels(newPixelX,newPixelY);break;
            case 26:  WS[1].setPixels(newPixelX,newPixelY);break;
            case 27:  WS[2].setPixels(newPixelX,newPixelY);break;
            case 28:  WS[3].setPixels(newPixelX,newPixelY);break;
            case 29:  WS[4].setPixels(newPixelX,newPixelY);break;
            case 30:  WS[5].setPixels(newPixelX,newPixelY);break;
            case 31:  WS[6].setPixels(newPixelX,newPixelY);break;
            case 32:  WS[7].setPixels(newPixelX,newPixelY);break;
        }
    }
    public Point getPixelPoint(int i) {
        choosenOne=i;
        switch(choosenOne) {
            
            case 17:return WC1.getpixelPoint();
            case 18:return WC2.getpixelPoint();
            case 19:return WH1.getpixelPoint();
            case 20:return WH2.getpixelPoint();
            case 21: return WE1.getpixelPoint();
            case 22: return WE2.getpixelPoint();
            case 23: return WQ.getpixelPoint();
            case 24: return WK.getpixelPoint();
            case 25: return  WS[0].getpixelPoint();
            case 26:  return WS[1].getpixelPoint();
            case 27: return  WS[2].getpixelPoint();
            case 28:  return WS[3].getpixelPoint();
            case 29: return  WS[4].getpixelPoint();
            case 30: return WS[5].getpixelPoint();
            case 31: return WS[6].getpixelPoint();
            case 32: return WS[7].getpixelPoint();
        }
        return null;
    }
    public boolean checkthemove(Point newP,int i) {
        choosenOne=i;
        switch(choosenOne) {
            
            case 17:return WC1.Canmove(newP.x,newP.y);
            case 18:return WC2.Canmove(newP.x,newP.y);
            case 19:return WH1.Canmove(newP.x,newP.y);
            case 20:return WH2.Canmove(newP.x,newP.y);
            case 21: return WE1.Canmove(newP.x,newP.y);
            case 22: return WE2.Canmove(newP.x,newP.y);
            case 23: return WQ.Canmove(newP.x,newP.y);
            case 24: return WK.Canmove(newP.x,newP.y);
            case 25: return  WS[0].Canmove(newP.x,newP.y,Color);
            case 26:  return WS[1].Canmove(newP.x,newP.y,Color);
            case 27: return  WS[2].Canmove(newP.x,newP.y,Color);
            case 28:  return WS[3].Canmove(newP.x,newP.y,Color);
            case 29: return  WS[4].Canmove(newP.x,newP.y,Color);
            case 30: return WS[5].Canmove(newP.x,newP.y,Color) ;
            case 31: return WS[6].Canmove(newP.x,newP.y,Color);
            case 32: return WS[7].Canmove(newP.x,newP.y,Color);
        }
        return false;
    }
    public boolean setSeentoSiliders(int i,Point P) {
        switch(i) {
            case 25:   return  WS[0].setSeenbychecking(P,"white");
            case 26:   return WS[1].setSeenbychecking(P,"white");
            case 27:  return   WS[2].setSeenbychecking(P,"white");
            case 28:  return    WS[3].setSeenbychecking(P,"white");
            case 29:  return  WS[4].setSeenbychecking(P,"white");
            case 30:  return  WS[5].setSeenbychecking(P,"white");
            case 31:  return  WS[6].setSeenbychecking(P,"white");
            case 32: return WS[7].setSeenbychecking(P,"white");
        }
        return false;
    }
    public boolean returnsoliderSeen(int i) {
        switch(i) {
            case 25:   return  WS[0].returnMyseen();
            case 26:   return WS[1].returnMyseen();
            case 27:  return   WS[2].returnMyseen();
            case 28:  return    WS[3].returnMyseen();
            case 29:  return  WS[4].returnMyseen();
            case 30:  return  WS[5].returnMyseen();
            case 31:  return  WS[6].returnMyseen();
            case 32: return WS[7].returnMyseen();
        }
        return false;
    }
    public boolean checktheWay(Point newP,Point postionFromOthers,int i) {
        switch(i) {
            
            
            case 17:return WC1.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 18:return WC2.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 21: return WE1.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 22: return WE2.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 23: return WQ.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 25: return  WS[0].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 26:  return WS[1].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 27: return  WS[2].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 28:  return WS[3].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 29: return  WS[4].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 30: return WS[5].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 31: return WS[6].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 32: return WS[7].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            
        }
        return false;
    }
    public boolean Killedpiec(int i) {
        Point out=new Point(13,13);
        switch(i) {
            
            case 17:  WC1.setPoint(out);return true;
            case 18:   WC2.setPoint(out);return true;
            case 19:  WH1.setPoint(out);return true;
            case 20:  WH2.setPoint(out);return true;
            case 21:   WE1.setPoint(out);return true;
            case 22:   WE2.setPoint(out);return true;
            case 23:   WQ.setPoint(out);return true;
            case 24:    return false;
            case 25:    WS[0].setPoint(out);return true;
            case 26:   WS[1].setPoint(out);return true;
            case 27:   WS[2].setPoint(out);return true;
            case 28:    WS[3].setPoint(out);return true;
            case 29:    WS[4].setPoint(out);return true;
            case 30:   WS[5].setPoint(out);return true;
            case 31:   WS[6].setPoint(out);return true;
            case 32:   WS[7].setPoint(out);return true;
        }
        
        return false;
    }
    public boolean checKing(Point p1,Point p2,int i) {
        switch(i) {
            
            case 17:return  WC1.checkKing(p1.x,p1.y,p2);
            case 18:return WC2.checkKing(p1.x,p1.y,p2);
            case 19:return  WH1.Canmove(p1.x,p1.y);
            case 20:return  WH2.Canmove(p1.x,p1.y);
            case 21:return   WE1.checkKing(p1.x,p1.y,p2);
            case 22:return   WE2.checkKing(p1.x,p1.y,p2);
            case 23:return   WQ.checkKing(p1.x,p1.y,p2);
            
            case 25:return    WS[0].Canmove(p1.x,p1.y,Color);
            case 26:return   WS[1].Canmove(p1.x,p1.y,Color);
            case 27:return   WS[2].Canmove(p1.x,p1.y,Color);
            case 28:return    WS[3].Canmove(p1.x,p1.y,Color);
            case 29:return    WS[4].Canmove(p1.x,p1.y,Color);
            case 30: return  WS[5].Canmove(p1.x,p1.y,Color);
            case 31:return   WS[6].Canmove(p1.x,p1.y,Color);
            case 32:return   WS[7].Canmove(p1.x,p1.y,Color);
        }
        return false;
    }
    
    
    
    public int returnChosen() {
        return choosenOne;
    }
    public void SetInhand(int i ) {
        inHand=i;
    }
    public int GetInhand() {
        return inHand;
    }
    public boolean CanMove(int x,int y) {
        return true;
    }
    public void checkKing(boolean newkingcheck) {
        kingischeck=newkingcheck;
    }
    public boolean returncheckKing() {
        
        
        return kingischeck;
    }
    
    public boolean if_MyKing_In_check(player2 Black) {
        boolean isCheckmate=false;
        boolean flag=false;
        
        
        
        
        
        
        return false;
    }
    public boolean Check_Mate_GameOver(player2 Enemy) {
        
        
        
        
        if(! KingGenerate_moves(Enemy)) {
            
            inHand=-1;
            return false;
        } else if(!CastleGenerate_moves(Enemy,WC1)) {
            
            inHand=-1;
            return false;
        } else if(!CastleGenerate_moves(Enemy,WC2)) {
            
            inHand=-1;
            return false;
        } else if(!ElephentGenerate_moves(Enemy,WE1)) {
            
            inHand=-1;
            return false;
        } else if(!ElephentGenerate_moves(Enemy,WE2)) {
            
            inHand=-1;
            return false;
        } else if(!HosreGenerate_moves(Enemy,WH1)) {
            
            inHand=-1;
            return false;
        } else if(!HosreGenerate_moves(Enemy,WH2)) {
            
            inHand=-1;
            return false;
        }
        
        else if(!QueenGenerate_moves(Enemy)) {
            
            inHand=-1;
            return false;
        }
        
        for(int i=0;i<=7;i++) {
            inHand=25+i;
            if(!SoliderGenerate_moves(Enemy,WS[i])) {
                
                inHand=-1;
                System.out.println("I Killed Solider 1");
                return false;
            }
        }
        
        inHand=-1;
        return true;
        
        
        
        
    }
    public boolean  see_king_Check(player2 Black) {
        
        Point My_King_Postion=WK.returnPostion();
        boolean flag=false;
        
        
        //////////    Start Checking the King ////////////
        for(int i=1;i<17;i++) {
            if(i<9) {
                if(Black.checkthemove(My_King_Postion,i)) {
                    
                    flag=true;
                    for(int j=1;j<33;j++) {
                        
                        if(j<17) {
                            
                            if(Black.checktheWay(My_King_Postion,Black.returnPostion(j),i)) {
                                //Means there is something in the Way so can't move'
                                
                                flag=false;
                                
                                
                            }
                        }
                        
                        else {
                            
                            if(j!=24)
                                if(Black.checktheWay(My_King_Postion,returnPostion(j),i)) {
                                
                                flag=false;
                                //Means there is something in the Way so can't move'
                                
                                
                                }
                            
                        }
                        
                    }
                    
                    if(flag) {
                        
                        break;
                    }
                    
                }
            } else {
                // For soliders
                if(Black.setSeentoSiliders(i,My_King_Postion)) {
                    
                    break;
                    
                }
            }
            //   if(stillIn_Check){ return true;}//Here Means White king is in check !!!
            if(i==16) {
                
                return false; }
        }
        
        return true;
    }
    
    
    public boolean Pice_already_there(Point newP) {
        Point samePostion;
        for(int i=17;i<=32;i++) {
            if(GetInhand()!=i)// There is no need to check the inHand pice
            {
                
                
                //Check if there is White Pices in the new Point
                //If so we Can't move (Same Color)!!
                
                
                
                samePostion=returnPostion(i);
                if(newP.x==samePostion.x&&newP.y==samePostion.y) {
                    
                    return false;
                    
                }
            }
        }
        
        
        return true;
    }
    public boolean Pice_already_there_from_enemy(Point newP,player2 enemy) {
        Point samePostion;
        for(int i=1;i<=16;i++) {
            samePostion=enemy.returnPostion(i);
            if(newP.x==samePostion.x&&newP.y==samePostion.y) {
                
                return false;
                
            }
        }
        
        
        return true;
    }
    public int Get_Pice_already_there_from_enemy(Point newP,player2 enemy) {
        Point samePostion;
        for(int i=1;i<=16;i++) {
            samePostion=enemy.returnPostion(i);
            if(newP.x==samePostion.x&&newP.y==samePostion.y) {
                
                return i;
                
            }
        }
        
        
        return -1;
    }
    
    
    public boolean KingGenerate_moves(player2 enemy) {
        boolean somthing_killed=false;
        Point Oldp=new Point();
        
        Point PlaceCheck=new Point();
        inHand=24;
        
        int x=WK.returnX();
        int y=WK.returnY();
        Oldp.x=x;
        Oldp.y=y;
        
        if(x+1<=8) {
            
            
            WK.setX(x+1);
            WK.setY(y);
            PlaceCheck.x=x+1;
            PlaceCheck.y=y;
            
            
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there(PlaceCheck))
                if(!see_king_Check(enemy)) {
                
                
                WK.setPoint(Oldp);
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                return false;
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y+1<=8) {
            
            
            WK.setX(x);
            WK.setY(y+1);
            PlaceCheck.x=x;
            PlaceCheck.y=y+1;
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                
                WK.setPoint(Oldp);
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                return false;
                
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        if(y-1>0) {
            
            WK.setX(x);
            WK.setY(y-1);
            
            PlaceCheck.x=x;
            PlaceCheck.y=y-1;
            
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                
                WK.setPoint(Oldp);
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
                return false;
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(x-1>0) {
            
            WK.setX(x-1);
            WK.setY(y);
            
            PlaceCheck.x=x-1;
            PlaceCheck.y=y;
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                WK.setPoint(Oldp);
                return false;
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y-1>0&&x-1>0) {
            
            WK.setX(x-1);
            WK.setY(y-1);
            
            PlaceCheck.x=x-1;
            PlaceCheck.y=y-1;
            
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                WK.setPoint(Oldp);
                return false;
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y+1<=8&&x+1<=8) {
            
            WK.setX(x+1);
            WK.setY(y+1);
            
            PlaceCheck.x=x+1;
            PlaceCheck.y=y+1;
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                WK.setPoint(Oldp);
                return false;
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y-1>0&&x+1<=8) {
            
            WK.setX(x+1);
            WK.setY(y-1);
            
            PlaceCheck.x=x+1;
            PlaceCheck.y=y-1;
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                WK.setPoint(Oldp);
                return false;
                }
            
        }
        WK.setPoint(Oldp);
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y+1<=8&&x-1>0) {
            
            
            WK.setX(x-1);
            WK.setY(y+1);
            
            PlaceCheck.x=x-1;
            PlaceCheck.y=y+1;
            if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy)) {
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                WK.setPoint(Oldp);
                return false;
                }
            
        }
        
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        
        WK.setPoint(Oldp);
        return true;
        
        
        
        
    }
    
    public boolean CastleGenerate_moves(player2 enemy,Castle WC) {
        boolean somthing_killed=false;
        Point Oldp1=new Point();
        
        Point PlaceCheck=new Point();
        int x1=WC.returnX();
        int y1=WC.returnY();
        
        if(WC==WC1) {
            inHand=17;
        } else {
            inHand=18;
        }
        /////////////////////
        
        Oldp1.x=x1;
        Oldp1.y=y1;
        
        PlaceCheck.y=y1;
        
        if(x1!=20) {
            for(int i=1;i<=8;i++) {
                WC.setX(i);
                PlaceCheck.x=i;
                
                if(Check_The_Way_to_Postion(enemy,Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy)) {
                        WC.setX(Oldp1.x);
                        WC.setY(Oldp1.y);
                        
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            WC.setX(Oldp1.x);
            PlaceCheck.x=Oldp1.x;
            
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            for(int i=1;i<=8;i++) {
                WC.setY(i);
                PlaceCheck.y=i;
                if(Check_The_Way_to_Postion(enemy,Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck) )
                        if(!see_king_Check(enemy)) {
                        WC.setX(Oldp1.x);
                        WC.setY(Oldp1.y);
                        
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            WC.setY(Oldp1.y);
        }
        //////////////////////////////////////////
        ///////////////////////////
        
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        WC.setX(Oldp1.x);
        WC.setY(Oldp1.y);
        return true;
    }
    
    public  boolean ElephentGenerate_moves(player2 enemy,Elephent WE) {
        boolean somthing_killed=false;
        Point Oldp1=new Point();
        Point PlaceCheck=new Point();
        
        Oldp1=WE.returnPostion();
        
        if(WE==WE1) {
            inHand=21;
        } else {
            inHand=22;
        }
        
        if(Oldp1.x!=20) {
            for(int x= Oldp1.x,y= Oldp1.y;x>=1&&y<=8;x--,y++) {
                
                WE.setX(x);
                WE.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy)) {
                        
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        WE.setPoint(Oldp1);
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            for(int x=Oldp1.x,y=Oldp1.y;y>=1&&x<=8;x++,y--) {
                
                WE.setX(x);
                WE.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy)) {
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        WE.setPoint(Oldp1);
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            WE.setPoint(Oldp1);
            
        }
        
        WE.setPoint(Oldp1);
        
        
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        return true;
    }
    
    public boolean HosreGenerate_moves(player2 enemy,Horse WH) {
        Point oldp1=new Point();
        boolean somthing_killed=false;
        oldp1=WH.returnPostion();
        
        Point PlaceCheck=new Point();
        
        if(WH==WH1) {
            inHand=19;
        } else {
            inHand=20;
        }
        
        
        int x=oldp1.x;
        int y=oldp1.y;
        
        if(x!=20) {
            
            if(x+1<=8&&y+1<=8) {
                WH.setX(x+1);
                WH.setY(y+2);
                PlaceCheck.x=x+1;
                PlaceCheck.y=y+2;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    WH.setPoint(oldp1);
                    
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    
                    return false;
                    }
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x+1<=8&&y-2>=1) {
                WH.setX(x+1);
                WH.setY(y-2);
                PlaceCheck.x=x+1;
                PlaceCheck.y=y-2;
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x+2<=8&&y+1<=8) {
                WH.setX(x+2);
                WH.setY(y+1);
                PlaceCheck.x=x+2;
                PlaceCheck.y=y+1;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            if(x+2<=8&&y-1>=1) {
                WH.setX(x+2);
                WH.setY(y-1);
                PlaceCheck.x=x+2;
                PlaceCheck.y=y-1;
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-1>=1&&y+2<=8) {
                WH.setX(x-1);
                WH.setY(y+2);
                PlaceCheck.x=x-1;
                PlaceCheck.y=y+2;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-1>=1&&y-2>=1) {
                WH.setX(x-1);
                WH.setY(y-2);
                PlaceCheck.x=x-1;
                PlaceCheck.y=y-2;
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-2>=1&&y+1<=8) {
                WH.setX(x-2);
                WH.setY(y+1);
                PlaceCheck.x=x-2;
                PlaceCheck.y=y+1;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-2>=1&&y-1>=1) {
                WH.setX(x-2);
                WH.setY(y-1);
                PlaceCheck.x=x-2;
                PlaceCheck.y=y-1;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy)) {
                    if(somthing_killed) {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    WH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
        }
        WH.setPoint(oldp1);
        
        
        return true;
    }
    
    public boolean QueenGenerate_moves(player2 enemy) {
        boolean somthing_killed=false;
        
        Point Oldp1=new Point();
        
        Oldp1=WQ.returnPostion();
        
        Point PlaceCheck=new Point();
        
        inHand=23;
        
        if(Oldp1.x!=20) {
            for(int x= Oldp1.x,y= Oldp1.y;x>=1&&y<=8;x--,y++) {
                
                WQ.setX(x);
                WQ.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy)) {
                        WQ.setPoint(Oldp1);
                        
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            for(int x=Oldp1.x,y=Oldp1.y;y>=1&&x<=8;x++,y--) {
                
                WQ.setX(x);
                WQ.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy)) {
                        WQ.setPoint(Oldp1);
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            WQ.setPoint(Oldp1);
            PlaceCheck.x=Oldp1.x;
            PlaceCheck.y=Oldp1.y;
            
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            
            for(int i=1;i<=8;i++) {
                WQ.setX(i);
                
                PlaceCheck.x=i;
                if(Check_The_Way_to_Postion(enemy, Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck) )
                        if(!see_king_Check(enemy)) {
                        WQ.setX(Oldp1.x);
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            WQ.setX(Oldp1.x);
            PlaceCheck.x=Oldp1.x;
            
            if(somthing_killed) {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            
            for(int i=1;i<=8;i++) {
                WQ.setY(i);
                PlaceCheck.y=i;
                if(Check_The_Way_to_Postion(enemy, Oldp1)) {
                    if(kill_to_protect_king(enemy,returnPostion(inHand))) {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck) )
                        if(!see_king_Check(enemy)) {
                        WQ.setY(Oldp1.y);
                        if(somthing_killed) {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed) {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            WQ.setY(Oldp1.y);
        }
        
        
        if(somthing_killed) {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        
        return true;
        
    }
    public boolean SoliderGenerate_moves(player2 enemy, Solider Sold) {
        Point Oldp1=new Point();
        Oldp1=Sold.returnPostion();
        Point PlaceCheck=new Point();
        PlaceCheck.x=Oldp1.x;
        PlaceCheck.y=Oldp1.y;
        
        
        
        if(Oldp1.x!=20) {
            if(Sold.Canmove(Oldp1.x,Oldp1.y-2,Color)&&Oldp1.y-2>=1) {
                Sold.setY(Oldp1.y-2);
                
                PlaceCheck.y=Oldp1.y-1;
                
                if(Pice_already_there(PlaceCheck))
                    if(Pice_already_there_from_enemy(PlaceCheck,enemy)) {
                    if(!see_king_Check(enemy)) {
                        
                        Sold.setPoint(Oldp1);
                        return false;
                    }
                    }
                
            }
            if(Sold.Canmove(Oldp1.x,Oldp1.y-1,Color)&&Oldp1.y-1>=1) {
                Sold.setY(Oldp1.y-1);
                PlaceCheck.y=Oldp1.y-1;
                
                
                if(Pice_already_there(PlaceCheck))
                    if(Pice_already_there_from_enemy(PlaceCheck,enemy)) {
                    
                    if(!see_king_Check(enemy)) {
                        Sold.setPoint(Oldp1);
                        System.out.println("dff");
                        System.out.println("dff");
                        System.out.println("dff");
                        return false;
                    }
                    }
            }
            
            if(!Pice_already_there_from_enemy(new Point(Oldp1.x-1,Oldp1.y-1),enemy))
                if(kill_to_protect_king(enemy,new Point(Oldp1.x-1,Oldp1.y-1))) {
                
                if(!see_king_Check(enemy)) {
                    enemy.changePostion(other,ate_to_protect);
                    Sold.setPoint(Oldp1);
                    
                    return false;
                }
                enemy.changePostion(other,ate_to_protect);
                }
            
            if(!Pice_already_there_from_enemy(new Point(Oldp1.x+1,Oldp1.y-1),enemy))
                if(kill_to_protect_king(enemy,new Point(Oldp1.x+1,Oldp1.y-1))) {
                if(!see_king_Check(enemy)) {
                    enemy.changePostion(other,ate_to_protect);
                    Sold.setPoint(Oldp1);
                    
                    return false;
                }
                enemy.changePostion(other,ate_to_protect);
                
                }
            
        }
        
        Sold.setPoint(Oldp1);
        return true;
    }
    
    
    public boolean Check_The_Way_to_Postion(player2 enemy,Point newP) {
        boolean flag=false;
        
        
        for(int i=1;i<=32;i++) {
            if(inHand!=i)// check if there is peices in the WAY
            {
                if(i<17)
                    flag=checktheWay(newP,enemy.returnPostion(i),inHand);//Means there is somting in the Way so can't move
                else {
                    flag=checktheWay(newP,returnPostion(i),inHand);
                }
                
                if(flag==true) {
                    return false;}//Means  there is a Pice in the Way
            }
        }
        return true;
        
        
    }
    public boolean kill_to_protect_king(player2 enemy,Point newP) {
        
        for(int i=1;i<17;i++) {
            
            other=enemy.returnPostion(i);
            if(other.x==newP.x&&other.y==newP.y) {
                ate_to_protect=i;
                
                
                
                enemy.Killedpiec(i);
                return true;
            }
        }
        
        return false;
    }
    
    public String Tell_me_About_last_move() {
        switch(inHand) {
            
            case 17:return WC1.Tell_me();
            case 18:return WC2.Tell_me();
            case 19:return WH1.Tell_me();
            case 20:return WH2.Tell_me();
            case 21:return WE1.Tell_me();
            case 22:return WE2.Tell_me();
            case 23:return WQ.Tell_me();
            case 24:return WK.Tell_me();
            case 25:return  WS[0].Tell_me();
            case 26:return  WS[1].Tell_me();
            case 27:return  WS[2].Tell_me();
            case 28:return  WS[3].Tell_me();
            case 29:return  WS[4].Tell_me();
            case 30:return  WS[5].Tell_me();
            case 31:return  WS[6].Tell_me();
            case 32:return  WS[7].Tell_me();
        }
        return null;
    }
}





