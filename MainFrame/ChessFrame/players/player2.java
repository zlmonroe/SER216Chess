

package MainFrame.ChessFrame.players;
import MainFrame.ChessFrame.players.Pieces.Castle;
import MainFrame.ChessFrame.players.Pieces.Elephent;
import MainFrame.ChessFrame.players.Pieces.Horse;
import MainFrame.ChessFrame.players.Pieces.Queen;
import MainFrame.ChessFrame.players.Pieces.Solider;
import MainFrame.ChessFrame.players.Pieces.king;
import java.awt.Image;
import java.awt.Point;
import java.util.Enumeration;




public class player2 implements Enumeration
{
    public Castle BC1;
    public Castle BC2;
    public Horse BH1;
    public Horse BH2;
    public Elephent BE1;
    public Elephent BE2;
    public Queen BQ;
    private king BK;
    public Solider[] BS=new Solider[8];
    private int choosenOne;
    private int inHand=-1;
    private boolean kingischeck=false;
    private Point other;
    private int ate_to_protect;
    
    private String Color="black";
    /** Creates a new instance of player2 */
    public player2()
    {
        String fileSeparator = new String(System.getProperty("file.separator"));
        BC1=new Castle("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "br.gif",1,1);
        BC2=new Castle("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "br.gif",8,1);
        BH1=new Horse("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bn.gif",2,1);
        BH2=new Horse("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bn.gif",7,1);
        BE1=new Elephent("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bb.gif",3,1);
        BE2=new Elephent("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bb.gif",6,1);
        BQ=new Queen("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bq.gif",4,1);
        BK=new king("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bk.gif",5,1);
        int j=1;
        for(int  i=0;i<=7;i++,j++)
        {
            BS[i]=new Solider("src" + fileSeparator + "Icons" + fileSeparator + "Player2Icons" + fileSeparator+ "bp.gif",j,2);
        }
    }
    public void setChoosen(int newChoosen)
    {
        choosenOne=newChoosen;
    }
    public Point returnPostion(int i)
    {
        
        switch(i)
        {
            case 1:return BC1.returnPostion();
            case 2:return BC2.returnPostion();
            case 3:return BH1.returnPostion();
            case 4:return BH2.returnPostion();
            case 5:return BE1.returnPostion();
            case 6:return BE2.returnPostion();
            case 7:return BQ.returnPostion();
            case 8:return BK.returnPostion();
            case 9:return  BS[0].returnPostion();
            case 10:return  BS[1].returnPostion();
            case 11:return  BS[2].returnPostion();
            case 12:return  BS[3].returnPostion();
            case 13:return  BS[4].returnPostion();
            case 14:return  BS[5].returnPostion();
            case 15:return  BS[6].returnPostion();
            case 16:return  BS[7].returnPostion();
            
        }
        return new Point(-1,-1);
        
    }
    public Point returnOldPostion(int i)
    {
        
        switch(i)
        {
            case 1:return BC1.returnOld();
            case 2:return BC2.returnOld();
            case 3:return BH1.returnOld();
            case 4:return BH2.returnOld();
            case 5:return BE1.returnOld();
            case 6:return BE2.returnOld();
            case 7:return BQ.returnOld();
            case 8:return BK.returnOld();
            case 9:return  BS[0].returnOld();
            case 10:return  BS[1].returnOld();
            case 11:return  BS[2].returnOld();
            case 12:return  BS[3].returnOld();
            case 13:return  BS[4].returnOld();
            case 14:return  BS[5].returnOld();
            case 15:return  BS[6].returnOld();
            case 16:return  BS[7].returnOld();
            
        }
        return new Point(-1,-1);
        
    }
    public Image returnIconImage(int i)
    {
        switch(i)
        {
            case 1:return BC1.returnPieceImage();
            case 2:return BC2.returnPieceImage();
            case 3:return BH1.returnPieceImage();
            case 4:return BH2.returnPieceImage();
            case 5:return BE1.returnPieceImage();
            case 6:return BE2.returnPieceImage();
            case 7:return BQ.returnPieceImage();
            case 8:return BK.returnPieceImage();
            case 9:return  BS[0].returnPieceImage();
            case 10:return  BS[1].returnPieceImage();
            case 11:return  BS[2].returnPieceImage();
            case 12:return  BS[3].returnPieceImage();
            case 13:return  BS[4].returnPieceImage();
            case 14:return  BS[5].returnPieceImage();
            case 15:return  BS[6].returnPieceImage();
            case 16:return  BS[7].returnPieceImage();
            
        }
        return  null;
        
    }
    public void changePostion(Point newPoint,int i)
    {
        switch(i)
        {
            case 1:BC1.setPoint(newPoint);break;
            case 2: BC2.setPoint(newPoint);break;
            case 3: BH1.setPoint(newPoint);break;
            case 4: BH2.setPoint(newPoint);break;
            case 5: BE1.setPoint(newPoint);break;
            case 6: BE2.setPoint(newPoint);break;
            case 7: BQ.setPoint(newPoint);break;
            case 8: BK.setPoint(newPoint);break;
            case 9:  BS[0].setPoint(newPoint);break;
            case 10:  BS[1].setPoint(newPoint);break;
            case 11:  BS[2].setPoint(newPoint);break;
            case 12:  BS[3].setPoint(newPoint);break;
            case 13:  BS[4].setPoint(newPoint);break;
            case 14:  BS[5].setPoint(newPoint);break;
            case 15:  BS[6].setPoint(newPoint);break;
            case 16:  BS[7].setPoint(newPoint);break;
            
        }
        
    }
    public void changePostionToOld(Point newPoint,int i)
    {
        switch(i)
        {
            case 1:BC1.toOld(newPoint);break;
            case 2: BC2.toOld(newPoint);break;
            case 3: BH1.toOld(newPoint);break;
            case 4: BH2.toOld(newPoint);break;
            case 5: BE1.toOld(newPoint);break;
            case 6: BE2.toOld(newPoint);break;
            case 7: BQ.toOld(newPoint);break;
            case 8: BK.toOld(newPoint);break;
            case 9:  BS[0].toOld(newPoint);break;
            case 10:  BS[1].toOld(newPoint);break;
            case 11:  BS[2].toOld(newPoint);break;
            case 12:  BS[3].toOld(newPoint);break;
            case 13:  BS[4].toOld(newPoint);break;
            case 14:  BS[5].toOld(newPoint);break;
            case 15:  BS[6].toOld(newPoint);break;
            case 16:  BS[7].toOld(newPoint);break;
            
        }
        
    }
    public void changePixel(int newPixelX,int newPixelY)
    {
        switch(choosenOne)
        {
            case 1:BC1.setPixels(newPixelX,newPixelY);break;
            case 2: BC2.setPixels(newPixelX,newPixelY);break;
            case 3: BH1.setPixels(newPixelX,newPixelY);break;
            case 4: BH2.setPixels(newPixelX,newPixelY);break;
            case 5: BE1.setPixels(newPixelX,newPixelY);break;
            case 6: BE2.setPixels(newPixelX,newPixelY);break;
            case 7: BQ.setPixels(newPixelX,newPixelY);break;
            case 8: BK.setPixels(newPixelX,newPixelY);break;
            case 9:  BS[0].setPixels(newPixelX,newPixelY);break;
            case 10:  BS[1].setPixels(newPixelX,newPixelY);break;
            case 11:  BS[2].setPixels(newPixelX,newPixelY);break;
            case 12:  BS[3].setPixels(newPixelX,newPixelY);break;
            case 13:  BS[4].setPixels(newPixelX,newPixelY);break;
            case 14:  BS[5].setPixels(newPixelX,newPixelY);break;
            case 15:  BS[6].setPixels(newPixelX,newPixelY);break;
            case 16:  BS[7].setPixels(newPixelX,newPixelY);break;
            
        }
    }
    public Point getPixelPoint(int i)
    {
        choosenOne=i;
        switch(choosenOne)
        {
            case 1:return  BC1.getpixelPoint();
            case 2:return   BC2.getpixelPoint();
            case 3:return   BH1.getpixelPoint();
            case 4:return   BH2.getpixelPoint();
            case 5:return   BE1.getpixelPoint();
            case 6: return  BE2.getpixelPoint();
            case 7:return   BQ.getpixelPoint();
            case 8:return   BK.getpixelPoint();
            case 9:return    BS[0].getpixelPoint();
            case 10:return    BS[1].getpixelPoint();
            case 11: return   BS[2].getpixelPoint();
            case 12:return    BS[3].getpixelPoint();
            case 13:return    BS[4].getpixelPoint();
            case 14:return    BS[5].getpixelPoint();
            case 15:return    BS[6].getpixelPoint();
            case 16:return    BS[7].getpixelPoint();
            
        }
        return null;
    }
    public void changePixel(int newPixelX,int newPixelY,int i)
    {
        choosenOne=i;
        switch(choosenOne)
        {
            case 1:BC1.setPixels(newPixelX,newPixelY);break;
            case 2:BC2.setPixels(newPixelX,newPixelY);break;
            case 3:BH1.setPixels(newPixelX,newPixelY);break;
            case 4:BH2.setPixels(newPixelX,newPixelY);break;
            case 5:BE1.setPixels(newPixelX,newPixelY);break;
            case 6:BE2.setPixels(newPixelX,newPixelY);break;
            case 7:BQ.setPixels(newPixelX,newPixelY);break;
            case 8:BK.setPixels(newPixelX,newPixelY);break;
            case 9:BS[0].setPixels(newPixelX,newPixelY);break;
            case 10:BS[1].setPixels(newPixelX,newPixelY);break;
            case 11:BS[2].setPixels(newPixelX,newPixelY);break;
            case 12:BS[3].setPixels(newPixelX,newPixelY);break;
            case 13:BS[4].setPixels(newPixelX,newPixelY);break;
            case 14:BS[5].setPixels(newPixelX,newPixelY);break;
            case 15:BS[6].setPixels(newPixelX,newPixelY);break;
            case 16:BS[7].setPixels(newPixelX,newPixelY);break;
            
        }
    }
    public boolean Killedpiec(int i)
    {
        Point out=new Point(20,20);
        switch(i)
        {
            case 1:BC1.setPoint(out);return true;
            case 2:BC2.setPoint(out);return true;
            case 3:BH1.setPoint(out);return true;
            case 4:BH2.setPoint(out);return true;
            case 5:BE1.setPoint(out);return true;
            case 6:BE2.setPoint(out);return true;
            case 7:BQ.setPoint(out);return true;
            case 8 :return true;// the King "Can't be moved out"
            case 9:BS[0].setPoint(out);return true;
            case 10:BS[1].setPoint(out);return true;
            case 11:BS[2].setPoint(out);return true;
            case 12:BS[3].setPoint(out);return true;
            case 13:BS[4].setPoint(out);return true;
            case 14:BS[5].setPoint(out);return true;
            case 15:BS[6].setPoint(out);return true;
            case 16:BS[7].setPoint(out);return true;
        }
        return false;
    }
    
    public boolean checkthemove(Point newP,int i)
    {
        choosenOne=i;
        switch(choosenOne)
        {
            
            case 1:return BC1.Canmove(newP.x,newP.y);
            case 2:return   BC2.Canmove(newP.x,newP.y);
            case 3:return   BH1.Canmove(newP.x,newP.y);
            case 4:return   BH2.Canmove(newP.x,newP.y);
            case 5:return   BE1.Canmove(newP.x,newP.y);
            case 6: return  BE2.Canmove(newP.x,newP.y);
            case 7:return   BQ.Canmove(newP.x,newP.y);
            case 8:return   BK.Canmove(newP.x,newP.y);
            case 9:return    BS[0].Canmove(newP.x,newP.y,Color);
            case 10:return    BS[1].Canmove(newP.x,newP.y,Color);
            case 11: return   BS[2].Canmove(newP.x,newP.y,Color);
            case 12:return    BS[3].Canmove(newP.x,newP.y,Color);
            case 13:return    BS[4].Canmove(newP.x,newP.y,Color);
            case 14:return    BS[5].Canmove(newP.x,newP.y,Color);
            case 15:return    BS[6].Canmove(newP.x,newP.y,Color);
            case 16:return    BS[7].Canmove(newP.x,newP.y,Color);
        }
        return false;
    }
    public boolean setSeentoSiliders(int i,Point P)
    {
        
        switch(i)
        {
            case 9: return     BS[0].setSeenbychecking(P,"black");
            case 10: return    BS[1].setSeenbychecking(P,"black");
            case 11: return     BS[2].setSeenbychecking(P,"black");
            case 12: return     BS[3].setSeenbychecking(P,"black");
            case 13: return     BS[4].setSeenbychecking(P,"black");
            case 14: return     BS[5].setSeenbychecking(P,"black");
            case 15: return    BS[6].setSeenbychecking(P,"black");
            case 16: return     BS[7].setSeenbychecking(P,"black");
        }
        return false;
        
    }
    public boolean returnsoliderSeen(int i)
    {
        switch(i)
        {
            
            case 9: return     BS[0].returnMyseen();
            case 10: return    BS[1].returnMyseen();
            case 11: return     BS[2].returnMyseen();
            case 12: return     BS[3].returnMyseen();
            case 13: return     BS[4].returnMyseen();
            case 14: return     BS[5].returnMyseen();
            case 15: return    BS[6].returnMyseen();
            case 16: return     BS[7].returnMyseen();
        }
        return false;
    }
    public boolean checktheWay(Point newP,Point postionFromOthers,int i)
    {
        switch(i)
        {
            case 1:return BC1.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 2:return   BC2.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 5:return   BE1.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 6: return  BE2.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 7:return   BQ.PieceInMYway(newP.x,newP.y,postionFromOthers);
            case 9:return    BS[0].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 10:return   BS[1].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 11: return   BS[2].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 12:return    BS[3].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 13:return    BS[4].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 14:return    BS[5].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 15:return   BS[6].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);
            case 16:
            {  return   BS[7].PieceInMYway(newP.x,newP.y,postionFromOthers,Color);}
            
            
        }
        return false;
    }
    public boolean checKing(Point p1,Point p2,int i)
    {
        switch(i)
        {
            
            case 1:return  BC1.checkKing(p1.x,p1.y,p2);
            case 2:return BC2.checkKing(p1.x,p1.y,p2);
            case 3:return  BH1.Canmove(p1.x,p1.y);
            case 4:return  BH2.Canmove(p1.x,p1.y);
            case 5:return   BE1.checkKing(p1.x,p1.y,p2);
            case 6:return   BE2.checkKing(p1.x,p1.y,p2);
            case 7:return   BQ.checkKing(p1.x,p1.y,p2);
            
            case 9:return    BS[0].Canmove(p1.x,p1.y,Color);
            case 10:return   BS[1].Canmove(p1.x,p1.y,Color);
            case 11:return   BS[2].Canmove(p1.x,p1.y,Color);
            case 12:return    BS[3].Canmove(p1.x,p1.y,Color);
            case 13:return    BS[4].Canmove(p1.x,p1.y,Color);
            case 14: return  BS[5].Canmove(p1.x,p1.y,Color);
            case 15:return   BS[6].Canmove(p1.x,p1.y,Color);
            case 16:return   BS[7].Canmove(p1.x,p1.y,Color);
        }
        return false;
    }
    public int returnChosen()
    {
        return choosenOne;
    }
    public void SetInhand(int i )
    {
        inHand=i;
    }
    public int GetInhand()
    {
        return inHand;
    }
    public boolean CanMove(int x,int y)
    {
        return true;
    }
    public void checkKing(boolean newkingcheck)
    {
        kingischeck=newkingcheck;
    }
    public boolean returncheckKing()
    {
        return kingischeck;
    }
    public boolean  see_king_Check(player1 White)
    {
        
        Point My_King_Postion=BK.returnPostion();
        boolean flag=false;
        
        
        //////////    Start Checking the King ////////////
        for(int i=17;i<33;i++)
        {
            if(i<25)
            {
                if(White.checkthemove(My_King_Postion,i))
                {
                    
                    flag=true;
                    for(int j=1;j<33;j++)
                    {
                        
                        if(j<17)
                        {
                            
                            if(White.checktheWay(My_King_Postion,returnPostion(j),i))
                            {
                                //Means there is somting in the Way so can't move'
                                
                                flag=false;
                                
                                
                            }
                        }
                        
                        else
                        {
                            
                            if(j!=8)
                                if(White.checktheWay(My_King_Postion,White.returnPostion(j),i))
                                {
                                
                                flag=false;
                                //Means there is somting in the Way so can't move'
                                
                                
                                }
                            
                        }
                        
                    }
                    
                    if(flag)
                    {
                        
                        break;
                    }
                    
                }
            }
            else
            {
                // For soliders
                if(White.setSeentoSiliders(i,My_King_Postion))
                {
                    
                    break;
                    
                }
            }
            //   if(stillIn_Check){ return true;}//Here Means White king is in check !!!
            if(i==32)
            {
                
                return false; }
        }
        
        return true;
    }
    public boolean Check_Mate_GameOver(player1 Enemy)
    {
        
        
        
        
        if(! KingGenerate_moves(Enemy))
        {
            
            inHand=-1;
            System.out.println("I Killed King  2");
            
            return false;
        }
        else if(!CastleGenerate_moves(Enemy,BC1))
        {
            
            inHand=-1;
            System.out.println("I Killed Castle 2");
            
            return false;
        }
        else if(!CastleGenerate_moves(Enemy,BC2))
        {
            
            inHand=-1;
            System.out.println("I Killed Castle 2");
            return false;
        }
        else if(!ElephentGenerate_moves(Enemy,BE1))
        {
            
            inHand=-1;
            System.out.println("I Killed ELephent2");
            return false;
        }
        else if(!ElephentGenerate_moves(Enemy,BE2))
        {
            
            inHand=-1;
            System.out.println("I Killed ELephent2");
            
            return false;
        }
        else if(!HosreGenerate_moves(Enemy,BH1))
        {
            
            inHand=-1;
            System.out.println("I Killed Horse 2");
            return false;
        }
        else if(!HosreGenerate_moves(Enemy,BH2))
        {
            
            inHand=-1;
            System.out.println("I Killed Horse 2");
            return false;
        }
        
        else if(!QueenGenerate_moves(Enemy))
        {
            
            inHand=-1;
            System.out.println("I Killed Queen 2");
            return false;
        }
        
        for(int i=0;i<=7;i++)
        {
            inHand=9+i;
            if(!SoliderGenerate_moves(Enemy,BS[i]))
            {
                
                inHand=-1;
                
                System.out.println("I Killed Solider 2");
                
                return false;
            }
        }
        
        inHand=-1;
        return true;
        
        
        
        
    }
    
    
    
    public boolean Pice_already_there(Point newP)
    {
        Point samePostion;
        for(int i=1;i<=16;i++)
        {
            if(GetInhand()!=i)// There is no need to check the inHand pice
            {
                
                
                //Check if there is White Pices in the new Point
                //If so we Can't move (Same Color)!!
                
                
                
                samePostion=returnPostion(i);
                if(newP.x==samePostion.x&&newP.y==samePostion.y)
                {
                    
                    return false;
                    
                }
            }
        }
        
        
        return true;
    }
    public boolean Pice_already_there_from_enemy(Point newP,player1 enemy)
    {
        Point samePostion;
        for(int i=17;i<=32;i++)
        {
            
            
            
            //Check if there is White Pices in the new Point
            //If so we Can't move (Same Color)!!
            
            
            
            samePostion=enemy.returnPostion(i);
            if(newP.x==samePostion.x&&newP.y==samePostion.y)
            {
                
                return false;
                
            }
            
        }
        
        
        return true;
    }
    public int Get_Pice_already_there_from_enemy(Point newP,player1 enemy)
    {
        Point samePostion;
        for(int i=17;i<=32;i++)
        {
            samePostion=enemy.returnPostion(i);
            if(newP.x==samePostion.x&&newP.y==samePostion.y)
            {
                return i;
            }
        }
        return -1;
    }
    public boolean KingGenerate_moves(player1 enemy)
    {
        boolean somthing_killed=false;
        Point Oldp=new Point();
        
        Point PlaceCheck=new Point();
        inHand=8;
        
        int x=BK.returnX();
        int y=BK.returnY();
        
        Oldp.x=x;
        Oldp.y=y;
        
        if(x+1<=8)
        {
            
            
            BK.setX(x+1);
            BK.setY(y);
            PlaceCheck.x=x+1;
            PlaceCheck.y=y;
            
            
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there(PlaceCheck))
                if(!see_king_Check(enemy))
                {
                
                
                BK.setPoint(Oldp);
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                return false;
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y+1<=8)
        {
            
            
            BK.setX(x);
            BK.setY(y+1);
            PlaceCheck.x=x;
            PlaceCheck.y=y+1;
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                
                BK.setPoint(Oldp);
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                return false;
                
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        if(y-1>0)
        {
            
            BK.setX(x);
            BK.setY(y-1);
            
            PlaceCheck.x=x;
            PlaceCheck.y=y-1;
            
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                
                BK.setPoint(Oldp);
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
                return false;
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(x-1>0)
        {
            
            BK.setX(x-1);
            BK.setY(y);
            
            PlaceCheck.x=x-1;
            PlaceCheck.y=y;
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                BK.setPoint(Oldp);
                return false;
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y-1>0&&x-1>0)
        {
            
            BK.setX(x-1);
            BK.setY(y-1);
            
            PlaceCheck.x=x-1;
            PlaceCheck.y=y-1;
            
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                BK.setPoint(Oldp);
                return false;
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y+1<=8&&x+1<=8)
        {
            
            BK.setX(x+1);
            BK.setY(y+1);
            
            PlaceCheck.x=x+1;
            PlaceCheck.y=y+1;
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                BK.setPoint(Oldp);
                return false;
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y-1>0&&x+1<=8)
        {
            
            BK.setX(x+1);
            BK.setY(y-1);
            
            PlaceCheck.x=x+1;
            PlaceCheck.y=y-1;
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                BK.setPoint(Oldp);
                return false;
                }
            
        }
        BK.setPoint(Oldp);
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        if(y+1<=8&&x-1>0)
        {
            
            
            BK.setX(x-1);
            BK.setY(y+1);
            
            PlaceCheck.x=x-1;
            PlaceCheck.y=y+1;
            if(kill_to_protect_king(enemy,returnPostion(inHand)))
            {
                somthing_killed=true;
            }
            if(Pice_already_there( PlaceCheck))
                if(!see_king_Check(enemy))
                {
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                BK.setPoint(Oldp);
                return false;
                }
            
        }
        
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        
        BK.setPoint(Oldp);
        return true;
        
        
        
        
    }
    
    public boolean CastleGenerate_moves(player1 enemy,Castle BC)
    {
        boolean somthing_killed=false;
        Point Oldp1=new Point();
        
        Point PlaceCheck=new Point();
        int x1=BC.returnX();
        int y1=BC.returnY();
        
        if(BC==BC1)
        {
            inHand=1;
        }
        else
        {
            inHand=2;
        }
        /////////////////////
        
        Oldp1.x=x1;
        Oldp1.y=y1;
        
        PlaceCheck.y=y1;
        
        
        
        if(x1!=20)
        {
            for(int i=1;i<=8;i++)
            {
                
                BC.setX(i);
                PlaceCheck.x=i;
                
                
                if(Check_The_Way_to_Postion(enemy,Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy))
                        {
                        BC.setX(Oldp1.x);
                        BC.setY(Oldp1.y);
                        
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            BC.setX(Oldp1.x);
            PlaceCheck.x=Oldp1.x;
            
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            for(int i=1;i<=8;i++)
            {
                BC.setY(i);
                PlaceCheck.y=i;
                if(Check_The_Way_to_Postion(enemy,Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck) )
                        if(!see_king_Check(enemy))
                        {
                        BC.setX(Oldp1.x);
                        BC.setY(Oldp1.y);
                        
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            BC.setY(Oldp1.y);
        }
        //////////////////////////////////////////
        ///////////////////////////
        
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        BC.setX(Oldp1.x);
        BC.setY(Oldp1.y);
        return true;
    }
    
    public  boolean ElephentGenerate_moves(player1 enemy,Elephent BE)
    {
        boolean somthing_killed=false;
        Point Oldp1=new Point();
        Point PlaceCheck=new Point();
        
        Oldp1=BE.returnPostion();
        
        if(BE==BE1)
        {
            inHand=5;
        }
        else
        {
            inHand=6;
        }
        
        if(Oldp1.x!=20)
        {
            for(int x= Oldp1.x,y= Oldp1.y;x>=1&&y<=8;x--,y++)
            {
                
                BE.setX(x);
                BE.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy))
                        {
                        
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        BE.setPoint(Oldp1);
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            for(int x=Oldp1.x,y=Oldp1.y;y>=1&&x<=8;x++,y--)
            {
                
                BE.setX(x);
                BE.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy))
                        {
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        BE.setPoint(Oldp1);
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            BE.setPoint(Oldp1);
            
        }
        
        BE.setPoint(Oldp1);
        
        
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        return true;
    }
    
    public boolean HosreGenerate_moves(player1 enemy,Horse BH)
    {
        Point oldp1=new Point();
        boolean somthing_killed=false;
        oldp1=BH.returnPostion();
        
        Point PlaceCheck=new Point();
        
        if(BH==BH1)
        {
            inHand=3;
        }
        else
        {
            inHand=4;
        }
        
        
        int x=oldp1.x;
        int y=oldp1.y;
        
        if(x!=20)
        {
            
            if(x+1<=8&&y+1<=8)
            {
                BH.setX(x+1);
                BH.setY(y+2);
                PlaceCheck.x=x+1;
                PlaceCheck.y=y+2;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    BH.setPoint(oldp1);
                    
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    
                    return false;
                    }
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x+1<=8&&y-2>=1)
            {
                BH.setX(x+1);
                BH.setY(y-2);
                PlaceCheck.x=x+1;
                PlaceCheck.y=y-2;
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x+2<=8&&y+1<=8)
            {
                BH.setX(x+2);
                BH.setY(y+1);
                PlaceCheck.x=x+2;
                PlaceCheck.y=y+1;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            if(x+2<=8&&y-1>=1)
            {
                BH.setX(x+2);
                BH.setY(y-1);
                PlaceCheck.x=x+2;
                PlaceCheck.y=y-1;
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-1>=1&&y+2<=8)
            {
                BH.setX(x-1);
                BH.setY(y+2);
                PlaceCheck.x=x-1;
                PlaceCheck.y=y+2;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-1>=1&&y-2>=1)
            {
                BH.setX(x-1);
                BH.setY(y-2);
                PlaceCheck.x=x-1;
                PlaceCheck.y=y-2;
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-2>=1&&y+1<=8)
            {
                BH.setX(x-2);
                BH.setY(y+1);
                PlaceCheck.x=x-2;
                PlaceCheck.y=y+1;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            if(x-2>=1&&y-1>=1)
            {
                BH.setX(x-2);
                BH.setY(y-1);
                PlaceCheck.x=x-2;
                PlaceCheck.y=y-1;
                
                if(kill_to_protect_king(enemy,returnPostion(inHand)))
                {
                    somthing_killed=true;
                }
                
                if(Pice_already_there(PlaceCheck) )
                    if(!see_king_Check(enemy))
                    {
                    if(somthing_killed)
                    {
                        enemy.changePostion(other,ate_to_protect);
                        somthing_killed=false;
                    }
                    BH.setPoint(oldp1);
                    return false;
                    }
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
        }
        BH.setPoint(oldp1);
        
        
        return true;
    }
    
    public boolean QueenGenerate_moves(player1 enemy)
    {
        boolean somthing_killed=false;
        
        Point Oldp1=new Point();
        
        Oldp1=BQ.returnPostion();
        
        Point PlaceCheck=new Point();
        
        inHand=7;
        
        if(Oldp1.x!=20)
        {
            for(int x= Oldp1.x,y= Oldp1.y;x>=1&&y<=8;x--,y++)
            {
                
                BQ.setX(x);
                BQ.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy))
                        {
                        BQ.setPoint(Oldp1);
                        
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            for(int x=Oldp1.x,y=Oldp1.y;y>=1&&x<=8;x++,y--)
            {
                
                BQ.setX(x);
                BQ.setY(y);
                PlaceCheck.x=x;
                PlaceCheck.y=y;
                if(Check_The_Way_to_Postion(enemy, Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck))
                        if(!see_king_Check(enemy))
                        {
                        BQ.setPoint(Oldp1);
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            BQ.setPoint(Oldp1);
            PlaceCheck.x=Oldp1.x;
            PlaceCheck.y=Oldp1.y;
            
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            
            for(int i=1;i<=8;i++)
            {
                BQ.setX(i);
                
                PlaceCheck.x=i;
                if(Check_The_Way_to_Postion(enemy, Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck) )
                        if(!see_king_Check(enemy))
                        {
                        BQ.setX(Oldp1.x);
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            BQ.setX(Oldp1.x);
            PlaceCheck.x=Oldp1.x;
            
            if(somthing_killed)
            {
                enemy.changePostion(other,ate_to_protect);
                somthing_killed=false;
            }
            
            
            for(int i=1;i<=8;i++)
            {
                BQ.setY(i);
                PlaceCheck.y=i;
                if(Check_The_Way_to_Postion(enemy, Oldp1))
                {
                    if(kill_to_protect_king(enemy,returnPostion(inHand)))
                    {
                        somthing_killed=true;
                    }
                    if(Pice_already_there(PlaceCheck) )
                        if(!see_king_Check(enemy))
                        {
                        BQ.setY(Oldp1.y);
                        if(somthing_killed)
                        {
                            enemy.changePostion(other,ate_to_protect);
                            somthing_killed=false;
                        }
                        return false;
                        }
                }
                
                if(somthing_killed)
                {
                    enemy.changePostion(other,ate_to_protect);
                    somthing_killed=false;
                }
                
            }
            BQ.setY(Oldp1.y);
        }
        
        
        if(somthing_killed)
        {
            enemy.changePostion(other,ate_to_protect);
            somthing_killed=false;
        }
        
        
        return true;
        
    }
    public boolean SoliderGenerate_moves(player1 enemy, Solider Sold)
    {
        Point Oldp1=new Point();
        Oldp1=Sold.returnPostion();
        Point PlaceCheck=new Point();
        PlaceCheck.x=Oldp1.x;
        PlaceCheck.y=Oldp1.y;
        
        
        
        if(Oldp1.x!=20)
        {
            if(Sold.Canmove(Oldp1.x,Oldp1.y+2,Color)&&Oldp1.y+2>=1)
            {
                Sold.setY(Oldp1.y+2);
                
                PlaceCheck.y=Oldp1.y+2;
                
                if(Pice_already_there(PlaceCheck))
                    if(Pice_already_there_from_enemy(PlaceCheck,enemy))
                    {
                    if(!see_king_Check(enemy))
                    {
                        
                        Sold.setPoint(Oldp1);
                        return false;
                    }
                    }
                
            }
            if(Sold.Canmove(Oldp1.x,Oldp1.y+1,Color)&&Oldp1.y+1>=1)
            {
                Sold.setY(Oldp1.y+1);
                PlaceCheck.y=Oldp1.y+1;
                
                
                
                if(Pice_already_there(PlaceCheck))
                    if(Pice_already_there_from_enemy(PlaceCheck,enemy))
                    {
                    if(!see_king_Check(enemy))
                    {
                        Sold.setPoint(Oldp1);
                        
                        return false;
                    }
                    }
            }
            
            if(Pice_already_there_from_enemy(new Point(Oldp1.x-1,Oldp1.y+1),enemy))
                if(kill_to_protect_king(enemy,new Point(Oldp1.x-1,Oldp1.y+1)))
                {
                
                if(!see_king_Check(enemy))
                {
                    enemy.changePostion(other,ate_to_protect);
                    Sold.setPoint(Oldp1);
                    
                    return false;
                }
                enemy.changePostion(other,ate_to_protect);
                }
            
            if(!Pice_already_there_from_enemy(new Point(Oldp1.x+1,Oldp1.y+1),enemy))
                if(kill_to_protect_king(enemy,new Point(Oldp1.x+1,Oldp1.y+1)))
                {
                if(!see_king_Check(enemy))
                {
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
    
    
    public boolean Check_The_Way_to_Postion(player1 enemy,Point newP)
    {
        boolean flag=false;
        
        
        for(int i=1;i<=32;i++)
        {
            if(inHand!=i)// check if there is peices in the WAY
            {
                if(i<17)
                    flag=checktheWay(newP,enemy.returnPostion(i),inHand);//Means there is somting in the Way so can't move
                else
                {
                    flag=checktheWay(newP,returnPostion(i),inHand);
                }
                
                if(flag==true)
                {return false;}//Means  there is a Pice in the Way
            }
        }
        return true;
        
        
    }
    public boolean kill_to_protect_king(player1 enemy,Point newP)
    {
        
        for(int i=17;i<=32;i++)
        {
            
            other=enemy.returnPostion(i);
            if(other.x==newP.x&&other.y==newP.y)
            {
                ate_to_protect=i;
                
                
                
                enemy.Killedpiec(i);
                return true;
            }
        }
        
        return false;
    }
    
    public boolean hasMoreElements()
    {
        return false;
    }
    
    public Object nextElement()
    {
        
        return new Object();
    }
    public String Tell_me_About_last_move()
    {
        switch(inHand)
        {
            case 1:return BC1.Tell_me();
            case 2:return BC2.Tell_me();
            case 3:return BH1.Tell_me();
            case 4:return BH2.Tell_me();
            case 5:return BE1.Tell_me();
            case 6:return BE2.Tell_me();
            case 7:return BQ.Tell_me();
            case 8:return BK.Tell_me();
            case 9:return  BS[0].Tell_me();
            case 10:return  BS[1].Tell_me();
            case 11:return  BS[2].Tell_me();
            case 12:return  BS[3].Tell_me();
            case 13:return  BS[4].Tell_me();
            case 14:return  BS[5].Tell_me();
            case 15:return  BS[6].Tell_me();
            case 16:return  BS[7].Tell_me();
        }
        return null;
    }
    
}
