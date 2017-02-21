

package MainFrame.ChessMenuBar.ChessBar_Menus;

import javax.swing.JMenu;

import MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.Tool_MenuItems.Options;

public class Tools_Menu extends JMenu
{
    
    /** Creates a new instance of Tools_Menu */
    public Tools_Menu()
    {
        setText("Tools");
        add(GameOptions);
    }
    
    private final Options GameOptions=new Options();
}
