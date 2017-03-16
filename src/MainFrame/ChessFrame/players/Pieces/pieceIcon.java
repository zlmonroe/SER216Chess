
package MainFrame.ChessFrame.players.Pieces;
import java.awt.Image;
import java.awt.Toolkit;

public class pieceIcon {
    private Toolkit kit=Toolkit.getDefaultToolkit();
    private Image image;
    
    
    public pieceIcon(String NameIcon) //throws IOException
    {
        
        image=kit.getImage(NameIcon);
    }
    
    public Image returnPieceIcon() {
        return image;
    }
    
    
   
}
