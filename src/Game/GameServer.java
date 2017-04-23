package Game;

import Game.Message;
import Game.Player;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tjcup on 4/21/2017.
 */
public class GameServer {
    private Player whitePlayer;
    private Player blackPlayer;
    private Socket whiteSocket;
    private Socket blackSocket;
    private boolean whiteConnected;
    private boolean blackConnected;
    private Message message;
    private ObjectOutputStream whiteOut;
    private ObjectInputStream whiteIn;
    private ObjectOutputStream blackOut;
    private ObjectInputStream blackIn;

    public static void main(String[] args){
        if(args.length>0){
            new GameServer(Integer.parseInt(args[0]));
        }
        else
            new GameServer(5000);
    }
    public GameServer(int portNum){
        whiteConnected = false;
        blackConnected = false;
        whitePlayer = new Player(true);
        blackPlayer = new Player(false);
        try{
            ServerSocket serverSocket = new ServerSocket(portNum);
            whiteSocket = serverSocket.accept();
            whiteConnected = true;
            blackSocket = serverSocket.accept();
            blackConnected = true;
            System.out.println("All Connected");
            whiteIn = new ObjectInputStream(whiteSocket.getInputStream());
            whiteOut =  new ObjectOutputStream(whiteSocket.getOutputStream());
            blackIn =  new ObjectInputStream(blackSocket.getInputStream());
            blackOut = new ObjectOutputStream(blackSocket.getOutputStream());
            whiteOut.writeObject(new Message(true,null,"","",0));
            blackOut.writeObject(new Message(false,null,"","",0));
            blackSocket.setSoTimeout(500);
            whiteSocket.setSoTimeout(500);

        }
        catch (Throwable e) {

        }
        while(whiteConnected && blackConnected) {
            try {
                message = (Message) whiteIn.readObject();
                System.out.println("White sent: " + message.newMessage);
                blackOut.writeObject(message);
            } catch (Exception e) {
            }
            try {
                message = (Message) blackIn.readObject();
                System.out.println("Black sent: " + message.newMessage);
                whiteOut.writeObject(message);
            } catch (Exception e) {
            }

        }
    }


}
