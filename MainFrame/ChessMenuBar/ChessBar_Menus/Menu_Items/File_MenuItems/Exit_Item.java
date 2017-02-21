

package MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.File_MenuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;


public class Exit_Item extends JMenuItem
{
    
    /** Creates a new instance of Exit_Item */
    public Exit_Item()
    {
        setText("Exit");
        addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        
    }
    
}
