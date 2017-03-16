
package MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.File_MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import MainFrame.ChessFrame.MainFrame;
import MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.File_MenuItems.newGame_Dialoge.NewGameDialoge;

public class New_Item extends JMenu
{
    
    /** Creates a new instance of New_Item */
    public New_Item(MainFrame ff)
    {
        Ndial=new NewGameDialoge(ff);
        setText("New Game");
        
        OnePlayer.setEnabled(false);
        TwoPlayer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                Ndial.setVisible(true);
                
                
                
                
                
                
                
                
            }
        } );
        add(OnePlayer);
        add(TwoPlayer);
    }
    
    public String getIpAddress()
    {
        return  Ndial.GetIpAddress();
    }
    public String getportNumber()
    {
        return Ndial.GetPortnumber();
    }
    
    private final NewGameDialoge Ndial;
    private final JMenuItem OnePlayer=new JMenuItem(" One Player");
    private final JMenuItem TwoPlayer=new JMenuItem(" Two Player");
    
    
}


