
package MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.File_MenuItems;

import MainFrame.ChessFrame.MainFrame;
import MainFrame.ChessMenuBar.ChessBar_Menus.Menu_Items.File_MenuItems.newGame_Dialoge.NewGameDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class New_Item extends JMenu {

    /**
     * Creates a new instance of New_Item
     */
    public New_Item(MainFrame ff) {
        Ndial = new NewGameDialog(ff);
        setText("New Game");

        OnePlayer.setEnabled(false);
        TwoPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Ndial.setVisible(true);
            }
        });
        add(OnePlayer);
        add(TwoPlayer);
    }

    public String getIpAddress() {
        return Ndial.GetIpAddress();
    }

    public String getPortNumber() {
        return Ndial.GetPortnumber();
    }

    private final NewGameDialog Ndial;
    private final JMenuItem OnePlayer = new JMenuItem(" One Player");
    private final JMenuItem TwoPlayer = new JMenuItem(" Two Player");


}


