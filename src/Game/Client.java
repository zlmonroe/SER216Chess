package Game;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by tjcup on 4/22/2017.
 */
public class Client implements Runnable{
    private ObjectInputStream in;
    private ObjectOutputStream out;
    public Message message;
    private boolean isWhite;

    public Client() {

    }
    public void startClient(String host, int port){
        try {
            Socket client = new Socket(host, port);
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
            //client.setSoTimeout(500);
        } catch (IOException e) {
            System.out.println("No Server Found");
        }
        try {
            message = (Message)in.readObject();
            isWhite = message.isWhite;
            System.out.println(isWhite);
        } catch (Exception e) {
        }
    }
    public void send(String chatMessage) {
        if (chatMessage != null) {
            System.out.println("Message Sent: " + chatMessage.trim());
            try {
                out.writeObject(new Message(true, null, chatMessage, "", System.currentTimeMillis()));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Try starting the server first!!!");
            }
        }
    }
        public void run(){
            while(true) {
                try {
                    message = (Message) in.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }

}
