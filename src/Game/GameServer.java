package Game;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by tjcup on 4/21/2017.
 */
public class GameServer {
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
        boolean whiteConnected = false;
        boolean blackConnected = false;
        Player whitePlayer = new Player(true);
        Player blackPlayer = new Player(false);
        try{
            ServerSocket serverSocket = new ServerSocket(portNum);
            Socket whiteSocket = serverSocket.accept();
            whiteConnected = true;
            Socket blackSocket = serverSocket.accept();
            blackConnected = true;
            System.out.println("All Connected");

            whiteOut =  new ObjectOutputStream(whiteSocket.getOutputStream());
            whiteIn = new ObjectInputStream(whiteSocket.getInputStream());

            blackOut = new ObjectOutputStream(blackSocket.getOutputStream());
            blackIn =  new ObjectInputStream(blackSocket.getInputStream());
            whiteOut.writeObject(new Message(true,null,null,null,0));
            blackOut.writeObject(new Message(false,null,null,null,0));
            blackSocket.setSoTimeout(500);
            whiteSocket.setSoTimeout(500);
            boolean turn = true;

        }
        catch (Throwable e) {

        }
        while(whiteConnected && blackConnected) {
            Message whiteMessage;
            Message blackMessage;

            try {
                whiteMessage = (Message) whiteIn.readObject();
                System.out.println("White sent: " + whiteMessage.newMessage);
                blackOut.writeObject(whiteMessage);
                blackOut.reset();
            } catch (Exception e) {
            }

            try {
                blackMessage = (Message) blackIn.readObject();
                System.out.println("Black sent: " + blackMessage.newMessage);
                whiteOut.writeObject(blackMessage);
                whiteOut.reset();
            } catch (Exception e) {
            }


        }
    }


}
