package Game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by tjcup on 4/22/2017.
 */
public class Client implements Runnable{
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public LinkedList<Message> messages;
    public boolean isConnected;
    public boolean isWhite;

    public Client(String host, int port) {
        messages = new LinkedList<>();
        try {
            Socket client = new Socket(host, port);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            isConnected = true;
        } catch (IOException e) {
            System.out.println("No Server Found");
            isConnected = false;
        }
        if (isConnected) {
            try {
                Message message = (Message) in.readObject();
                isWhite = message.isWhite;
                System.out.println(isWhite);
            } catch (Exception e) {
            }
        }
    }
    public void sendChat(String chatMessage) {
        if (chatMessage != null) {
            System.out.println("Message Sent: " + chatMessage.trim());
            try {
                out.writeObject(new Message(isWhite, null, chatMessage, null, null));
                out.reset();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Try starting the server first!!!");
            }
        }
    }
    public void sendMove(Move move){
        try {
            out.writeObject(new Message(isWhite, move, null, null, null));
            out.reset();
            System.out.println((isWhite ? "white":"black") + " sent the server: " + move.oldPoint + " to " + move.newPoint);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public void sendTime(String time){
        try {
            out.writeObject(new Message(isWhite, null, null, "Game In Progress", time));
            out.reset();
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

        public void run(){
            while(true) {
                try {
                    messages.add((Message) in.readObject());
                    Thread.sleep(100);
                } catch (IOException | InterruptedException | ClassNotFoundException e) {
                    //e.printStackTrace();
                }
            }

        }

}
